package com.resonance.core.ai.service;

import java.util.List;

/**
 * CN: AnswerMeta 是一次回答的轻量画像，只走 SSE 给前端展示，不落库。
 * EN: AnswerMeta is lightweight answer profiling for SSE display only and is not persisted.
 * JP: AnswerMeta は回答の軽量プロファイルで、SSE 表示専用であり DB には保存しません。
 */
public record AnswerMeta(
        String answerType,
        String productScope,
        String evidenceLevel,
        List<String> assumptions,
        List<String> clarifyingQuestions) {

    public AnswerMeta {
        assumptions = assumptions == null ? List.of() : List.copyOf(assumptions);
        clarifyingQuestions = clarifyingQuestions == null ? List.of() : List.copyOf(clarifyingQuestions);
    }
}
