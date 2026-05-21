package com.resonance.core.ai.service;

import java.util.List;

/**
 * CN: SkillSearchRequest 是 DeepSeek 规划给 Java 执行的本地 skill 检索请求。
 * EN: SkillSearchRequest is the local skill retrieval request DeepSeek plans for Java to execute.
 * JP: SkillSearchRequest は DeepSeek が計画し Java が実行するローカル skill 検索要求です。
 */
public record SkillSearchRequest(
        String skill,
        List<String> keywords,
        List<String> headings,
        String reason) {
}
