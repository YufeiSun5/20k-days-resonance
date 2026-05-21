package com.resonance.core.ai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class QuestionProfileServiceTest {

    private final QuestionProfileService service = new QuestionProfileService();

    @Test
    void scadaWebFailureProfilesAsKingScadaTroubleshooting() {
        AnswerMeta meta = service.profile("Scada网页发布失败");

        assertEquals("troubleshooting", meta.answerType());
        assertEquals("KingSCADA", meta.productScope());
        assertFalse(meta.clarifyingQuestions().isEmpty());
    }

    @Test
    void rawByTimeCalculatedProfilesAsHistorianDiff() {
        AnswerMeta meta = service.profile("RawByTime 和 Calculated 怎么选？");

        assertEquals("product_diff", meta.answerType());
        assertEquals("KingHistorian", meta.productScope());
    }

    @Test
    void relationDatabaseScriptProfilesAsCodeRecipe() {
        AnswerMeta meta = service.profile("帮我写一个查询关系库脚本");

        assertEquals("code_recipe", meta.answerType());
        assertEquals("KingSCADA", meta.productScope());
        assertFalse(meta.assumptions().isEmpty());
    }
}
