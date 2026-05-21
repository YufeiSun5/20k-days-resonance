package com.resonance.core.ai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resonance.core.ai.AiProperties;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class AgentLoopServiceTest {

    @TempDir
    private Path tempDir;

    @Mock
    private DeepseekClient deepseekClient;

    @Test
    void searchDecisionCollectsContextAndStreamsFinalAnswer() throws Exception {
        SkillIndexService skillIndexService = skillIndex("""
                # Guide
                | 场景 | 使用 Skill |
                | 报表 SQL | `kingscada-ch15-reports` |
                """);
        writeSkill("kingscada-ch15-reports", "reports.md", """
                # 报表系统
                ## SQL 查询报表
                报表可以通过数据库查询结果写入单元格。
                """);
        skillIndexService.reload();

        when(deepseekClient.complete(any(), anyInt()))
                .thenReturn(Mono.just("""
                        {"action":"search_skill_context","requests":[{"skill":"kingscada-ch15-reports","keywords":["SQL","报表"],"headings":["SQL 查询报表"],"reason":"查报表 SQL"}]}
                        """))
                .thenReturn(Mono.just("{\"action\":\"final_answer\",\"answer\":\"ready\"}"));
        when(deepseekClient.streamChat(any())).thenReturn(Flux.just("最终答案"));

        AgentLoopService service = new AgentLoopService(
                properties(),
                skillIndexService,
                new SkillRouterService(),
                new SkillDependencyService(),
                deepseekClient,
                new ObjectMapper(),
                new QuestionProfileService());
        List<AgentEvent> events = new ArrayList<>();
        StringBuilder deltas = new StringBuilder();

        AgentAnswerResult result = service.streamAnswer(
                "KingSCADA 报表里用 SQL 查询数据库并导出",
                List.of(),
                List.of(),
                events::add,
                deltas::append);

        assertEquals("最终答案", result.answer());
        assertFalse(result.sources().isEmpty());
        assertEquals("final_answer", result.stopReason());
        assertEquals("最终答案", deltas.toString());
        assertTrue(events.stream().anyMatch(event -> "agent_round".equals(event.name())));
        assertTrue(events.stream().anyMatch(event -> "agent_hits".equals(event.name())));
        assertTrue(events.stream().anyMatch(event -> "answer_meta".equals(event.name())));
    }

    @Test
    void repeatedSearchDoesNotDuplicateContext() throws Exception {
        SkillIndexService skillIndexService = skillIndex("# Guide");
        writeSkill("kingscada-ch15-reports", "reports.md", """
                # 报表系统
                ## SQL 查询报表
                SQL 查询报表内容。
                """);
        skillIndexService.reload();

        String decision = """
                {"action":"search_skill_context","requests":[{"skill":"kingscada-ch15-reports","keywords":["SQL"],"headings":["SQL 查询报表"],"reason":"same"}]}
                """;
        when(deepseekClient.complete(any(), anyInt()))
                .thenReturn(Mono.just(decision))
                .thenReturn(Mono.just(decision))
                .thenReturn(Mono.just("{\"action\":\"final_answer\",\"answer\":\"ready\"}"));
        when(deepseekClient.streamChat(any())).thenReturn(Flux.just("ok"));

        AgentLoopService service = new AgentLoopService(
                properties(),
                skillIndexService,
                new SkillRouterService(),
                new SkillDependencyService(),
                deepseekClient,
                new ObjectMapper(),
                new QuestionProfileService());

        AgentAnswerResult result = service.streamAnswer("SQL 报表", List.of(), List.of(), event -> {
        }, delta -> {
        });

        assertEquals(
                result.sources().size(),
                result.sources().stream().map(source -> source.path() + "::" + source.heading()).distinct().count());
        assertEquals("final_answer", result.stopReason());
    }

    @Test
    void invalidDecisionFallsBackToSingleRoundSearch() throws Exception {
        SkillIndexService skillIndexService = skillIndex("# Guide");
        writeSkill("kingscada-kh", "kh.md", """
                # KH
                ## DataQuality
                DataQuality = 192 means good quality.
                """);
        skillIndexService.reload();

        when(deepseekClient.complete(any(), anyInt()))
                .thenReturn(Mono.just("not json"))
                .thenReturn(Mono.just("still not json"));
        when(deepseekClient.streamChat(any())).thenReturn(Flux.just("fallback answer"));

        AgentLoopService service = new AgentLoopService(
                properties(),
                skillIndexService,
                new SkillRouterService(),
                new SkillDependencyService(),
                deepseekClient,
                new ObjectMapper(),
                new QuestionProfileService());

        AgentAnswerResult result = service.streamAnswer("DataQuality 192 是什么意思", List.of(), List.of(), event -> {
        }, delta -> {
        });

        assertTrue(result.fallback());
        assertEquals("fallback_single_round", result.stopReason());
        assertEquals("fallback answer", result.answer());
    }

    @Test
    void kingScadaQuestionRejectsKingViewSearchDecision() throws Exception {
        SkillIndexService skillIndexService = skillIndex("""
                # Guide
                ## 产品边界与同名函数规则
                KingSCADA 问题只能查 kingscada-*。组态王 SQLSelect 不能证明 KingSCADA 支持 SQLSelect。
                """);
        writeSkill("kingscada-functions", "functions.md", """
                # KingSCADA 函数速查
                ## 数据库函数
                KingSCADA 数据库访问优先查 KDBGetDataset 和 KDBExecuteStatement。
                """);
        writeSkill("kingview-database", "database.md", """
                # 组态王数据库
                ## SQLSelect
                SQLSelect 是组态王数据库访问函数。
                """);
        skillIndexService.reload();

        when(deepseekClient.complete(any(), anyInt()))
                .thenReturn(Mono.just("""
                        {"action":"search_skill_context","requests":[{"skill":"kingview-database","keywords":["SQLSelect"],"headings":["SQLSelect"],"reason":"wrong product"}]}
                        """))
                .thenReturn(Mono.just("{\"action\":\"final_answer\",\"answer\":\"ready\"}"));
        when(deepseekClient.streamChat(any())).thenReturn(Flux.just("KingSCADA answer"));

        AgentLoopService service = new AgentLoopService(
                properties(),
                skillIndexService,
                new SkillRouterService(),
                new SkillDependencyService(),
                deepseekClient,
                new ObjectMapper(),
                new QuestionProfileService());

        AgentAnswerResult result = service.streamAnswer(
                "KingSCADA 里 SQLSelect 怎么用",
                List.of(),
                List.of(),
                event -> {
                },
                delta -> {
                });

        assertEquals("KingSCADA answer", result.answer());
        assertTrue(result.usedSkills().stream().allMatch(skill -> skill.startsWith("kingscada-")));
        assertFalse(result.usedSkills().contains("kingview-database"));
    }

    private AiProperties properties() {
        AiProperties properties = new AiProperties();
        properties.setSkillsRoot(tempDir.toString());
        properties.getAgent().setContextCharBudget(12000);
        return properties;
    }

    private SkillIndexService skillIndex(String guide) throws Exception {
        Files.writeString(tempDir.resolve("skills_index.md"), guide);
        return new SkillIndexService(properties());
    }

    private void writeSkill(String skillName, String referenceName, String referenceContent) throws Exception {
        Path skillDir = tempDir.resolve(skillName);
        Files.createDirectories(skillDir.resolve("references"));
        Files.writeString(skillDir.resolve("SKILL.md"), "# " + skillName + "\n");
        Files.writeString(skillDir.resolve("references").resolve(referenceName), referenceContent);
    }
}
