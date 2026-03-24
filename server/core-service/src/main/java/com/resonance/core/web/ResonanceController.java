package com.resonance.core.web;

import com.resonance.core.service.JournalApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core/resonance")
/**
 * CN: 空间共振控制器当前只保留一个最小推荐入口，先把“能返回结果”做扎实，再扩复杂策略。
 * EN: The resonance controller keeps a single minimal recommendation entry for now. Make “return a usable result” solid first, then extend policy complexity.
 * JP: 空間共鳴コントローラーは現時点では最小限の推薦入口だけを持ちます。まず「結果を返せる」ことを固め、その後で戦略を複雑化します。
 */
public class ResonanceController {

    private final JournalApplicationService journalApplicationService;

    public ResonanceController(JournalApplicationService journalApplicationService) {
        this.journalApplicationService = journalApplicationService;
    }

    @PostMapping("/recommend")
    /**
     * CN: 这个接口先接受 mock spaceId / hashedBssid，保证前端在真机 Wi-Fi 接入前也能跑业务闭环。
     * EN: This endpoint accepts a mock spaceId / hashedBssid first so the frontend can complete a business loop before real-device Wi-Fi integration is ready.
     * JP: この API はまず mock spaceId / hashedBssid を受け入れ、実機 Wi-Fi 連携前でもフロントが業務ループを回せるようにします。
     */
    public RecommendationResponse recommend(@Valid @RequestBody RecommendJournalRequest request) {
        return journalApplicationService.recommend(request);
    }
}