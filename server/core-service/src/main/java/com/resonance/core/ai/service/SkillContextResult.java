package com.resonance.core.ai.service;

import java.util.List;

/**
 * CN: SkillContextResult 表示一次 skill + keywords/headings 检索的命中结果。
 * EN: SkillContextResult represents one skill plus keywords/headings retrieval result.
 * JP: SkillContextResult は skill と keywords/headings による 1 回の検索結果を表します。
 */
public record SkillContextResult(
        SkillSearchRequest request,
        List<SkillContextChunk> chunks,
        boolean noHit) {
}
