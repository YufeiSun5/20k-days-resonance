package com.resonance.core.web;

import com.resonance.core.service.JournalApplicationService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core/journals")
/**
 * CN: 日记控制器先提供联调必需接口，不急着把复杂筛选、分页和权限模型一次堆满。
 * EN: The journal controller exposes only the endpoints needed for integration first. Complex filtering, pagination, and permission rules are intentionally deferred.
 * JP: 日記コントローラーはまず連携に必要な API だけを提供します。複雑な絞り込み、ページング、権限モデルは意図的に後回しです。
 */
public class JournalController {

    private final JournalApplicationService journalApplicationService;

    public JournalController(JournalApplicationService journalApplicationService) {
        this.journalApplicationService = journalApplicationService;
    }

    @PostMapping
    /**
     * CN: 给前端一个稳定的最小写入入口，保证推荐链路能尽快吃到真实日记数据。
     * EN: Provide a stable minimal write entry so the recommendation flow can start consuming real journal data quickly.
     * JP: 推薦経路が早く実データを食べられるよう、フロントに安定した最小書き込み入口を提供します。
     */
    public JournalResponse createJournal(@Valid @RequestBody CreateJournalRequest request) {
        return journalApplicationService.createJournal(request);
    }

    @GetMapping("/users/{userId}")
    /**
     * CN: 最近日记接口优先服务联调、回显和兜底推荐检查。
     * EN: The recent journals endpoint mainly serves integration, echo display, and fallback recommendation verification.
     * JP: 最近の日記 API は主に連携確認、表示確認、フォールバック推薦の検証に使います。
     */
    public List<JournalResponse> getRecentJournals(@PathVariable UUID userId) {
        return journalApplicationService.getRecentJournals(userId);
    }
}