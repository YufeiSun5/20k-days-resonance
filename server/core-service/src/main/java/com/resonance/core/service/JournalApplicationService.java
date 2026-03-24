package com.resonance.core.service;

import com.resonance.core.domain.RecommendationType;
import com.resonance.core.entity.Journal;
import com.resonance.core.entity.RecommendationLog;
import com.resonance.core.entity.WifiSpace;
import com.resonance.core.repository.JournalRepository;
import com.resonance.core.repository.RecommendationLogRepository;
import com.resonance.core.repository.WifiSpaceRepository;
import com.resonance.core.web.CreateJournalRequest;
import com.resonance.core.web.JournalResponse;
import com.resonance.core.web.RecommendJournalRequest;
import com.resonance.core.web.RecommendationResponse;
import java.util.Locale;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/**
 * CN: 核心日记应用服务先只处理三件事：写日记、看最近日记、按空间/星期几给出最小推荐结果。
 * EN: The journal application service currently owns three jobs only: create journals, read recent journals, and produce a minimal recommendation result by space/weekday.
 * JP: この日記アプリケーションサービスは現時点で三つだけを扱います。日記作成、最近の日記取得、そして空間/曜日に基づく最小推薦結果の生成です。
 */
public class JournalApplicationService {

    private final JournalRepository journalRepository;
    private final WifiSpaceRepository wifiSpaceRepository;
    private final RecommendationLogRepository recommendationLogRepository;

    public JournalApplicationService(
            JournalRepository journalRepository,
            WifiSpaceRepository wifiSpaceRepository,
            RecommendationLogRepository recommendationLogRepository) {
        this.journalRepository = journalRepository;
        this.wifiSpaceRepository = wifiSpaceRepository;
        this.recommendationLogRepository = recommendationLogRepository;
    }

    @Transactional
    /**
     * CN: 创建日记先只落最小字段，保证推荐链路有稳定输入，不在第一版里把模型做胖。
     * EN: Journal creation stores only the minimum fields first so the recommendation flow has stable input. The first iteration deliberately avoids a bloated model.
     * JP: 日記作成ではまず最小限の項目だけを保存し、推薦経路に安定した入力を与えます。初版でモデルを肥大化させません。
     */
    public JournalResponse createJournal(CreateJournalRequest request) {
        Journal journal = new Journal();
        journal.setUserId(request.userId());
        journal.setTitle(request.title());
        journal.setContent(request.content());
        journal.setOriginalWeekday(request.originalWeekday());
        journal.setVisibility("PRIVATE");
        return JournalResponse.from(journalRepository.save(journal));
    }

    @Transactional(readOnly = true)
    /**
     * CN: 最近日记查询主要给前端做联调和兜底展示，不在这里混入复杂筛选。
     * EN: Recent journal lookup mainly serves frontend integration and fallback display. Complex filtering is intentionally kept out for now.
     * JP: 最近の日記取得は主にフロント連携とフォールバック表示のためのものです。複雑な絞り込みはまだ持ち込みません。
     */
    public java.util.List<JournalResponse> getRecentJournals(java.util.UUID userId) {
        return journalRepository.findTop20ByUserIdAndDeletedFalseOrderByCreatedAtDesc(userId)
                .stream()
                .map(JournalResponse::from)
                .toList();
    }

    @Transactional
    /**
     * CN: 推荐流程先走简单可解释路径：先按 weekday 命中，再退化到最近一条，最后明确返回没有数据。
     * EN: The recommendation flow is intentionally simple and explainable: hit by weekday first, degrade to the latest journal next, and finally return an explicit no-data result.
     * JP: 推薦フローは意図的に単純で説明可能な形にしています。まず weekday で命中し、次に最新日記へフォールバックし、それでも無ければ明示的にデータ無しを返します。
     */
    public RecommendationResponse recommend(RecommendJournalRequest request) {
        WifiSpace space = resolveSpace(request);
        Optional<Journal> candidate = Optional.empty();
        RecommendationType type = RecommendationType.RANDOM;

        if (request.weekday() != null) {
            // CN: 这里先用 weekday 做最便宜、最可解释的召回，后面再叠 AI / embedding。
            // EN: Weekday is the cheapest and most explainable recall key for now. AI / embeddings can be layered on later.
            // JP: ここではまず weekday を使って、最も安く説明可能な検索を行います。AI / embedding は後で重ねます。
            candidate = journalRepository.findFirstByUserIdAndOriginalWeekdayAndDeletedFalseOrderByCreatedAtDesc(
                    request.userId(), request.weekday().shortValue());
            type = request.hashedBssid() != null && !request.hashedBssid().isBlank()
                    ? RecommendationType.SPACE_RESONANCE
                    : RecommendationType.WEEKDAY_RECALL;
        }

        if (candidate.isEmpty()) {
            // CN: 没有命中空间/星期几时，退化逻辑必须稳定，不能把前端丢进随机空状态。
            // EN: When space/weekday lookup misses, the fallback must stay stable instead of throwing the frontend into an arbitrary empty state.
            // JP: 空間/曜日検索が外れたときのフォールバックは安定していなければならず、フロントを不定な空状態に落としてはいけません。
            candidate = journalRepository.findFirstByUserIdAndDeletedFalseOrderByCreatedAtDesc(request.userId());
            type = RecommendationType.RANDOM;
        }

        if (candidate.isPresent()) {
            Journal journal = candidate.get();
            logRecommendation(request, space, journal, type);
            return new RecommendationResponse(
                    true,
                    type.name(),
                    "recommendation generated",
                    normalizedHashedBssid(request.hashedBssid()),
                    space != null ? space.getId() : null,
                    request.weekday(),
                    JournalResponse.from(journal));
        }

        return new RecommendationResponse(
                false,
                type.name(),
                "no journal found for this user yet",
                normalizedHashedBssid(request.hashedBssid()),
                space != null ? space.getId() : null,
                request.weekday(),
                null);
    }

    private WifiSpace resolveSpace(RecommendJournalRequest request) {
        // CN: 空间解析先只做幂等落库，别在这个阶段把 BSSID 规则写复杂。
        // EN: Space resolution is kept idempotent and narrow for now. This is not the place to overbuild BSSID rules yet.
        // JP: 空間解決は現時点では冪等な保存だけに留めます。ここで BSSID ルールを過度に複雑化しません。
        String hashedBssid = normalizedHashedBssid(request.hashedBssid());
        if (hashedBssid == null) {
            return null;
        }
        return wifiSpaceRepository.findByHashedBssid(hashedBssid)
                .orElseGet(() -> {
                    WifiSpace wifiSpace = new WifiSpace();
                    wifiSpace.setHashedBssid(hashedBssid);
                    wifiSpace.setSsidName(request.ssidName());
                    return wifiSpaceRepository.save(wifiSpace);
                });
    }

    private void logRecommendation(
            RecommendJournalRequest request,
            WifiSpace space,
            Journal journal,
            RecommendationType type) {
        // CN: 先把推荐事实记下来，后面点击率、反馈学习、排序优化才能有依据。
        // EN: Record the recommendation fact first. CTR, feedback learning, and ranking optimization need this trail later.
        // JP: まず推薦という事実を記録します。後続の CTR、フィードバック学習、順位最適化はこの履歴が前提です。
        RecommendationLog log = new RecommendationLog();
        log.setUserId(request.userId());
        log.setRecommendationType(type);
        log.setSourceSpaceId(space != null ? space.getId() : null);
        log.setSourceWeekday(request.weekday());
        log.setRecommendedJournalId(journal.getId());
        recommendationLogRepository.save(log);
    }

    private String normalizedHashedBssid(String hashedBssid) {
        // CN: 前后端都可能传出大小写和空白差异，先统一规整，避免同一个空间被拆成多份。
        // EN: Frontend and backend can differ on case and whitespace, so normalize first to avoid splitting one physical space into multiple records.
        // JP: フロントとバックエンドで大文字小文字や空白がぶれるので、先に正規化して同じ空間が複数レコードに割れないようにします。
        if (hashedBssid == null || hashedBssid.isBlank()) {
            return null;
        }
        return hashedBssid.trim().toLowerCase(Locale.ROOT);
    }
}