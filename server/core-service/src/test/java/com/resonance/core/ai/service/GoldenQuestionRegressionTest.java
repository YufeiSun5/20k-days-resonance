package com.resonance.core.ai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class GoldenQuestionRegressionTest {

    private final QuestionProfileService profileService = new QuestionProfileService();
    private final SkillRouterService router = new SkillRouterService();
    private final List<String> available = List.of(
            "kingscada-functions",
            "kingscada-kh",
            "kingscada-dataset",
            "kingscada-ch05-controls",
            "kingscada-ch10-animation",
            "kingscada-ch18-database",
            "kingscada-ch22-client-web",
            "kingscada-ch30-portal",
            "kingscada-appendix-faq",
            "kingview-database",
            "kingview-scripting");

    @Test
    void goldenQuestionsKeepStableProfileAndRouting() throws Exception {
        for (GoldenQuestion golden : loadGoldenQuestions()) {
            AnswerMeta meta = profileService.profile(golden.question());
            List<String> routed = router.route(golden.question(), available, available);

            assertEquals(golden.answerType(), meta.answerType(), golden.question());
            assertEquals(golden.productScope(), meta.productScope(), golden.question());
            for (String expectedSkill : golden.expectedSkills()) {
                assertTrue(routed.contains(expectedSkill), golden.question() + " should route " + expectedSkill + " but got " + routed);
            }
        }
    }

    private List<GoldenQuestion> loadGoldenQuestions() throws Exception {
        try (InputStream input = getClass().getResourceAsStream("/ai-golden-questions.json")) {
            return new ObjectMapper().readValue(input, new TypeReference<>() {
            });
        }
    }

    private record GoldenQuestion(
            String question,
            String answerType,
            String productScope,
            List<String> expectedSkills) {
    }
}
