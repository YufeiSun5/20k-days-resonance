package com.resonance.core.ai.service;

import java.util.List;

/**
 * CN: SearchRoundRecord 记录一轮 Agent 检索请求和命中，供下一轮决策和前端 trace 使用。
 * EN: SearchRoundRecord records one agent retrieval round for the next decision and the frontend trace.
 * JP: SearchRoundRecord は 1 ラウンドの検索要求と命中を記録し、次の判断とフロント trace に使います。
 */
public record SearchRoundRecord(
        int round,
        List<SkillSearchRequest> requests,
        List<SkillContextChunk> hits) {
}
