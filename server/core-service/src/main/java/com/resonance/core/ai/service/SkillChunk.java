package com.resonance.core.ai.service;

record SkillChunk(
        int index,
        String skillName,
        String path,
        String heading,
        String content) {

    SkillSource toSource(java.util.Set<String> terms) {
        String excerpt = excerptAroundTerms(terms);
        return new SkillSource(skillName, path, heading, excerpt.trim());
    }

    SkillChunk withIndex(int newIndex) {
        return new SkillChunk(newIndex, skillName, path, heading, content);
    }

    SkillContextChunk toContextChunk(java.util.Set<String> terms, int score) {
        return new SkillContextChunk(skillName, path, heading, excerptAroundTerms(terms).trim(), score, "");
    }

    private String excerptAroundTerms(java.util.Set<String> terms) {
        if (content.length() <= 2400) {
            return content;
        }

        String lowered = content.toLowerCase(java.util.Locale.ROOT);
        int hit = -1;
        if (terms != null) {
            for (String term : terms) {
                if (term == null || term.length() < 3) {
                    continue;
                }
                int index = lowered.indexOf(term.toLowerCase(java.util.Locale.ROOT));
                if (index >= 0 && (hit < 0 || index < hit)) {
                    hit = index;
                }
            }
        }
        if (hit < 0) {
            return content.substring(0, 2400);
        }

        int start = Math.max(0, hit - 700);
        int end = Math.min(content.length(), start + 2400);
        if (end - start < 2400) {
            start = Math.max(0, end - 2400);
        }
        String prefix = start > 0 ? "...\n" : "";
        String suffix = end < content.length() ? "\n..." : "";
        return prefix + content.substring(start, end) + suffix;
    }
}
