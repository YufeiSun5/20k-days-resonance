package com.resonance.core.ai.service;

import java.util.List;

record AgentDecision(
        String action,
        List<SkillSearchRequest> requests,
        String answer,
        AnswerMeta answerMeta) {

    boolean isSearch() {
        return "search_skill_context".equals(action);
    }

    boolean isFinalAnswer() {
        return "final_answer".equals(action);
    }
}
