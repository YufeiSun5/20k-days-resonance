package com.resonance.core.ai.service;

/**
 * CN: SkillContextChunk 是 Java 从本地 skill 文件中抽出的最小可引用上下文。
 * EN: SkillContextChunk is the smallest citeable context Java extracts from local skill files.
 * JP: SkillContextChunk は Java がローカル skill ファイルから抽出する最小引用可能文脈です。
 */
public record SkillContextChunk(
        String skillName,
        String path,
        String heading,
        String excerpt,
        int score,
        String lines) {

    String key() {
        return skillName + "::" + path + "::" + heading + "::" + excerpt.hashCode();
    }

    SkillSource toSource() {
        return new SkillSource(skillName, path, heading, excerpt);
    }
}
