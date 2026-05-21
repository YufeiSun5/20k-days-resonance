package com.resonance.core.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resonance.core.ai.AiProperties;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.springframework.stereotype.Service;

@Service
/**
 * CN: AgentLoopService 负责多轮 skill 检索调度；DeepSeek 只决定下一步查什么或是否回答。
 * EN: AgentLoopService owns the multi-round skill retrieval loop; DeepSeek only decides what to search next or whether to answer.
 * JP: AgentLoopService は多段 skill 検索ループを担います。DeepSeek は次に何を調べるか、または回答するかだけを決めます。
 */
public class AgentLoopService {

    private final AiProperties properties;
    private final SkillIndexService skillIndexService;
    private final SkillRouterService skillRouterService;
    private final SkillDependencyService skillDependencyService;
    private final DeepseekClient deepseekClient;
    private final ObjectMapper objectMapper;
    private final QuestionProfileService questionProfileService;

    public AgentLoopService(
            AiProperties properties,
            SkillIndexService skillIndexService,
            SkillRouterService skillRouterService,
            SkillDependencyService skillDependencyService,
            DeepseekClient deepseekClient,
            ObjectMapper objectMapper,
            QuestionProfileService questionProfileService) {
        this.properties = properties;
        this.skillIndexService = skillIndexService;
        this.skillRouterService = skillRouterService;
        this.skillDependencyService = skillDependencyService;
        this.deepseekClient = deepseekClient;
        this.objectMapper = objectMapper;
        this.questionProfileService = questionProfileService;
    }

    public AgentAnswerResult streamAnswer(
            String question,
            List<String> requestedSkillNames,
            List<DeepseekMessage> history,
            Consumer<AgentEvent> events,
            Consumer<String> deltas) {
        AgentContext context = new AgentContext(question, questionProfileService.profile(question));
        try {
            runSearchLoop(context, requestedSkillNames, events);
            context.answerMeta(questionProfileService.withEvidence(context.answerMeta(), context.collectedChunks(), false));
            emit(events, "answer_meta", context.answerMeta());
            String answer = streamFinalAnswer(context, history, deltas);
            return result(context, answer, false);
        } catch (Exception error) {
            return fallback(question, requestedSkillNames, history, events, deltas, error);
        }
    }

    private void runSearchLoop(AgentContext context, List<String> requestedSkillNames, Consumer<AgentEvent> events) {
        List<String> availableSkills = skillIndexService.listSkillNames();
        if (availableSkills.isEmpty()) {
            context.stop("no_skills");
            return;
        }

        for (int i = 0; i < properties.getAgent().getMaxRounds(); i++) {
            context.nextRound();
            AgentDecision decision = decide(context, availableSkills, requestedSkillNames);
            if (decision.isFinalAnswer()) {
                if (decision.answerMeta() != null) {
                    context.answerMeta(mergeMeta(context.answerMeta(), decision.answerMeta()));
                }
                context.stop("final_answer");
                return;
            }
            if (!decision.isSearch()) {
                context.stop("unknown_action");
                return;
            }

            List<SkillSearchRequest> requests = normalizeRequests(context.question(), decision.requests(), availableSkills, requestedSkillNames);
            if (requests.isEmpty()) {
                requests = fallbackRequests(context.question(), requestedSkillNames, availableSkills);
            }
            if (requests.isEmpty()) {
                context.incrementNoNewContextCount();
                context.searchRounds().add(new SearchRoundRecord(context.round(), List.of(), List.of()));
                emit(events, "agent_round", Map.of("round", context.round(), "requests", List.of()));
                emit(events, "agent_hits", Map.of("round", context.round(), "hits", List.of()));
                continue;
            }

            emit(events, "agent_round", Map.of("round", context.round(), "requests", requests));
            List<SkillContextChunk> hits = executeSearchRound(context, requests);
            context.searchRounds().add(new SearchRoundRecord(context.round(), requests, hits));
            emit(events, "agent_hits", Map.of("round", context.round(), "hits", hits));

            if (hits.isEmpty()) {
                context.incrementNoNewContextCount();
            } else {
                context.resetNoNewContextCount();
            }
            if (context.noNewContextCount() >= properties.getAgent().getNoNewContextLimit()) {
                context.stop("force_final_no_new_context");
                return;
            }
        }
        context.stop("force_final_max_rounds");
    }

    private List<SkillContextChunk> executeSearchRound(AgentContext context, List<SkillSearchRequest> requests) {
        List<SkillContextChunk> newHits = new ArrayList<>();
        Set<String> existingChunkKeys = new LinkedHashSet<>();
        context.collectedChunks().forEach(chunk -> existingChunkKeys.add(chunk.key()));

        for (SkillSearchRequest request : requests) {
            String searchKey = searchKey(request);
            if (!context.searchedKeys().add(searchKey)) {
                continue;
            }
            SkillContextResult result = skillIndexService.searchContext(request, context.question(), 3);
            for (SkillContextChunk chunk : result.chunks()) {
                if (!existingChunkKeys.add(chunk.key())) {
                    continue;
                }
                if (contextSize(context.collectedChunks()) + chunk.excerpt().length() > properties.getAgent().getContextCharBudget()) {
                    continue;
                }
                context.collectedChunks().add(chunk);
                newHits.add(chunk);
            }
        }
        return newHits;
    }

    private AgentDecision decide(AgentContext context, List<String> availableSkills, List<String> requestedSkillNames) {
        List<DeepseekMessage> messages = List.of(
                new DeepseekMessage("system", decisionSystemPrompt()),
                new DeepseekMessage("user", decisionUserPrompt(context, availableSkills, requestedSkillNames)));
        String raw = deepseekClient.complete(messages, 1800).block(Duration.ofSeconds(45));
        try {
            return parseDecision(raw);
        } catch (Exception error) {
            String repaired = deepseekClient.complete(List.of(
                    new DeepseekMessage("system", "Return one valid JSON object only. No markdown."),
                    new DeepseekMessage("user", "Repair this invalid Agent JSON:\n" + raw)), 1200).block(Duration.ofSeconds(30));
            return parseDecision(repaired);
        }
    }

    private AgentDecision parseDecision(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("empty agent decision");
        }
        String json = extractJson(raw);
        try {
            JsonNode root = objectMapper.readTree(json);
            String action = root.path("action").asText("");
            String answer = root.path("answer").asText("");
            List<SkillSearchRequest> requests = new ArrayList<>();
            if (root.path("requests").isArray()) {
                for (JsonNode node : root.path("requests")) {
                    requests.add(new SkillSearchRequest(
                            node.path("skill").asText(""),
                            strings(node.path("keywords")),
                            strings(node.path("headings")),
                            node.path("reason").asText("")));
                }
            }
            AnswerMeta answerMeta = parseAnswerMeta(root);
            return new AgentDecision(action, requests, answer, answerMeta);
        } catch (Exception error) {
            throw new IllegalArgumentException("invalid agent decision json", error);
        }
    }

    private AnswerMeta parseAnswerMeta(JsonNode root) {
        String answerType = root.path("answerType").asText("");
        String productScope = root.path("productScope").asText("");
        String evidenceLevel = root.path("evidenceLevel").asText("");
        List<String> assumptions = strings(root.path("assumptions"));
        List<String> clarifyingQuestions = strings(root.path("clarifyingQuestions"));
        if (answerType.isBlank() && productScope.isBlank() && evidenceLevel.isBlank() && assumptions.isEmpty() && clarifyingQuestions.isEmpty()) {
            return null;
        }
        return new AnswerMeta(answerType, productScope, evidenceLevel, assumptions, clarifyingQuestions);
    }

    private AnswerMeta mergeMeta(AnswerMeta base, AnswerMeta override) {
        if (base == null) {
            return override;
        }
        if (override == null) {
            return base;
        }
        return new AnswerMeta(
                override.answerType().isBlank() ? base.answerType() : override.answerType(),
                override.productScope().isBlank() ? base.productScope() : override.productScope(),
                override.evidenceLevel().isBlank() ? base.evidenceLevel() : override.evidenceLevel(),
                override.assumptions().isEmpty() ? base.assumptions() : override.assumptions(),
                override.clarifyingQuestions().isEmpty() ? base.clarifyingQuestions() : override.clarifyingQuestions());
    }

    private String streamFinalAnswer(AgentContext context, List<DeepseekMessage> history, Consumer<String> deltas) {
        List<DeepseekMessage> messages = new ArrayList<>();
        messages.add(new DeepseekMessage("system", finalSystemPrompt(buildSkillContext(context.collectedChunks()), context.answerMeta())));
        if (history != null) {
            messages.addAll(history);
        }
        messages.add(new DeepseekMessage("user", context.question()));

        StringBuilder answer = new StringBuilder();
        deepseekClient.streamChat(messages)
                .doOnNext(delta -> {
                    answer.append(delta);
                    deltas.accept(delta);
                })
                .blockLast(Duration.ofSeconds(100));
        String content = answer.toString().trim();
        if (content.isBlank()) {
            throw new IllegalStateException("DeepSeek returned empty final answer");
        }
        if ("running".equals(context.stopReason())) {
            context.stop("final_stream_completed");
        }
        return content;
    }

    private AgentAnswerResult fallback(
            String question,
            List<String> requestedSkillNames,
            List<DeepseekMessage> history,
            Consumer<AgentEvent> events,
            Consumer<String> deltas,
            Exception error) {
        AnswerMeta answerMeta = questionProfileService.withEvidence(questionProfileService.profile(question), List.of(), true);
        List<String> routedSkills = fallbackSkillNames(question, requestedSkillNames, skillIndexService.listSkillNames());
        List<SkillSource> sources = skillIndexService.search(question, routedSkills, 10);
        emit(events, "answer_meta", answerMeta);
        emit(events, "sources", sources);
        emit(events, "agent_done", Map.of(
                "usedSkills", usedSkills(sources),
                "roundCount", 0,
                "stopReason", "fallback_single_round",
                "error", error.getMessage() == null ? "agent failed" : error.getMessage()));

        List<DeepseekMessage> messages = new ArrayList<>();
        messages.add(new DeepseekMessage("system", finalSystemPrompt(skillIndexService.buildContext(sources), answerMeta)));
        if (history != null) {
            messages.addAll(history);
        }
        messages.add(new DeepseekMessage("user", question));

        StringBuilder answer = new StringBuilder();
        deepseekClient.streamChat(messages)
                .doOnNext(delta -> {
                    answer.append(delta);
                    deltas.accept(delta);
                })
                .blockLast(Duration.ofSeconds(100));
        return new AgentAnswerResult(answer.toString().trim(), sources, usedSkills(sources), 0, "fallback_single_round", true, answerMeta);
    }

    private AgentAnswerResult result(AgentContext context, String answer, boolean fallback) {
        List<SkillSource> sources = context.collectedChunks().stream()
                .sorted(Comparator.comparingInt(SkillContextChunk::score).reversed())
                .map(SkillContextChunk::toSource)
                .toList();
        return new AgentAnswerResult(answer, sources, usedSkills(sources), context.round(), context.stopReason(), fallback, context.answerMeta());
    }

    private List<SkillSearchRequest> normalizeRequests(
            String question,
            List<SkillSearchRequest> requests,
            List<String> availableSkills,
            List<String> requestedSkillNames) {
        Set<String> available = lowerSet(availableSkills);
        Set<String> requested = lowerSet(requestedSkillNames);
        Set<String> productScopes = productScopes(question);
        List<SkillSearchRequest> normalized = new ArrayList<>();
        if (requests != null) {
            for (SkillSearchRequest request : requests) {
                if (request.skill() == null) {
                    continue;
                }
                String skill = request.skill().trim().toLowerCase(Locale.ROOT);
                if (!available.contains(skill)) {
                    continue;
                }
                if (!requested.isEmpty() && !requested.contains(skill)) {
                    continue;
                }
                if (!productScopes.isEmpty() && productScopes.stream().noneMatch(scope -> skill.equals(scope) || skill.startsWith(scope))) {
                    continue;
                }
                normalized.add(new SkillSearchRequest(skill, safeList(request.keywords()), safeList(request.headings()), request.reason() == null ? "" : request.reason()));
                if (normalized.size() >= properties.getAgent().getMaxRequestsPerRound()) {
                    break;
                }
            }
        }
        return normalized;
    }

    private List<SkillSearchRequest> fallbackRequests(String question, List<String> requestedSkillNames, List<String> availableSkills) {
        return fallbackSkillNames(question, requestedSkillNames, availableSkills).stream()
                .limit(properties.getAgent().getMaxRequestsPerRound())
                .map(skill -> new SkillSearchRequest(skill, List.of(question), List.of(), "fallback routed skill"))
                .toList();
    }

    private List<String> fallbackSkillNames(String question, List<String> requestedSkillNames, List<String> availableSkills) {
        List<String> routedSkillNames = skillRouterService.route(question, requestedSkillNames, availableSkills);
        return skillDependencyService.expand(question, routedSkillNames, availableSkills);
    }

    private String decisionSystemPrompt() {
        return """
                You are a retrieval planner for a local Skill-RAG system.
                Return JSON only. Do not answer the user directly unless enough context has already been collected.
                Respect product boundaries strictly:
                - If the user asks KingSCADA / King Scada / KS, use only kingscada-* skills.
                - If the user asks KingView / 组态王, use only kingview-* skills.
                - If the user asks KingHistorian / KH / 工业库 KDB-SQL, prefer kingscada-kh and only add related kingscada-* skills when the user asks about KingSCADA integration.
                - Do not use a function from another product to fill a missing function. Search the requested product first; if missing, final answer must say the requested product evidence is missing.
                Valid JSON actions:
                {"action":"search_skill_context","requests":[{"skill":"skill-name","keywords":["term"],"headings":["heading"],"reason":"why"}]}
                {"action":"final_answer","answer":"ready","answerType":"troubleshooting|function_usage|code_recipe|product_diff|concept|unknown","productScope":"KingSCADA|KSP|KingView|KingHistorian|unclear","evidenceLevel":"low|medium|high","assumptions":["short assumption"],"clarifyingQuestions":["short question"]}
                Pick at most 4 requests. Use only available skill names.
                For troubleshooting, search FAQ / deployment / web / control / runtime skills before answering.
                For code recipes, search function signatures and examples before answering.
                """;
    }

    private String decisionUserPrompt(AgentContext context, List<String> availableSkills, List<String> requestedSkillNames) {
        return """
                User question:
                %s

                Initial answer profile:
                %s

                Requested skill scope:
                %s

                Available skills:
                %s

                Guide excerpt:
                %s

                Search memory:
                %s

                Collected skill context:
                %s
                """.formatted(
                context.question(),
                formatMeta(context.answerMeta()),
                requestedSkillNames == null || requestedSkillNames.isEmpty() ? "(none)" : requestedSkillNames,
                availableSkills,
                skillIndexService.guideExcerpt(10000),
                searchMemory(context),
                buildSkillContext(context.collectedChunks()));
    }

    private String finalSystemPrompt(String skillContext, AnswerMeta answerMeta) {
        return """
                You are the KingSCADA / KingHistorian / KingView skill assistant inside 20,000 Days.
                Answer based on the provided skill excerpts first.
                If the excerpts are insufficient, say what is missing instead of inventing details.
                Never mix product families silently. A KingView / 组态王 function is not evidence that KingSCADA supports the same function, and a KingSCADA KDB/DataSet method is not evidence that KingView supports it.
                When the user names a product, keep the answer scoped to that product and explicitly say if the provided excerpts only prove another product.
                Use the answer profile as a contract, not as user-visible debug text.
                - troubleshooting: give the most likely cause, the shortest verification path, then 1-3 key questions. Do not dump a broad checklist first.
                - function_usage: state the product and whether the function is evidenced in that product. Never borrow a same-name function from another product.
                - code_recipe: give a minimal copyable script first. Declare every variable/control name that must be created or replaced.
                - product_diff: compare in a compact table and keep product boundaries explicit.
                - concept: define the term, scope, and one practical example.
                If evidence is low, still provide the smallest useful answer, then ask for missing details.
                Keep answers direct, concrete, and implementation-oriented.

                Answer profile:
                %s

                Skill excerpts:
                """.formatted(formatMeta(answerMeta)) + skillContext;
    }

    private String formatMeta(AnswerMeta answerMeta) {
        if (answerMeta == null) {
            return "answerType=unknown, productScope=unclear, evidenceLevel=low";
        }
        return "answerType=%s, productScope=%s, evidenceLevel=%s, assumptions=%s, clarifyingQuestions=%s".formatted(
                answerMeta.answerType(),
                answerMeta.productScope(),
                answerMeta.evidenceLevel(),
                answerMeta.assumptions(),
                answerMeta.clarifyingQuestions());
    }

    private String buildSkillContext(List<SkillContextChunk> chunks) {
        if (chunks == null || chunks.isEmpty()) {
            return "No matching skill excerpt was found.";
        }
        StringBuilder builder = new StringBuilder();
        int used = 0;
        for (SkillContextChunk chunk : chunks) {
            String block = """
                    <skill_context skill="%s" path="%s" heading="%s" score="%d">
                    %s
                    </skill_context>

                    """.formatted(chunk.skillName(), chunk.path(), chunk.heading(), chunk.score(), chunk.excerpt());
            if (used + block.length() > properties.getAgent().getContextCharBudget()) {
                break;
            }
            builder.append(block);
            used += block.length();
        }
        return builder.toString();
    }

    private String searchMemory(AgentContext context) {
        if (context.searchRounds().isEmpty()) {
            return "(none)";
        }
        StringBuilder builder = new StringBuilder();
        for (SearchRoundRecord record : context.searchRounds()) {
            builder.append("round=").append(record.round())
                    .append(", requests=").append(record.requests())
                    .append(", hitCount=").append(record.hits().size())
                    .append('\n');
        }
        return builder.toString();
    }

    private int contextSize(List<SkillContextChunk> chunks) {
        return chunks.stream().mapToInt(chunk -> chunk.excerpt().length()).sum();
    }

    private String searchKey(SkillSearchRequest request) {
        List<String> keywords = safeList(request.keywords()).stream()
                .map(value -> value.toLowerCase(Locale.ROOT).trim())
                .sorted()
                .toList();
        return request.skill().toLowerCase(Locale.ROOT).trim() + "::" + String.join("|", keywords);
    }

    private List<String> strings(JsonNode node) {
        List<String> result = new ArrayList<>();
        if (node != null && node.isArray()) {
            for (JsonNode item : node) {
                if (item.isTextual() && !item.asText().isBlank()) {
                    result.add(item.asText());
                }
            }
        }
        return result;
    }

    private List<String> safeList(List<String> values) {
        if (values == null) {
            return List.of();
        }
        return values.stream()
                .filter(value -> value != null && !value.isBlank())
                .toList();
    }

    private Set<String> lowerSet(List<String> values) {
        LinkedHashSet<String> result = new LinkedHashSet<>();
        if (values != null) {
            values.stream()
                    .filter(value -> value != null && !value.isBlank())
                    .map(value -> value.trim().toLowerCase(Locale.ROOT))
                    .forEach(result::add);
        }
        return result;
    }

    private Set<String> productScopes(String question) {
        String text = question == null ? "" : question.toLowerCase(Locale.ROOT);
        LinkedHashSet<String> result = new LinkedHashSet<>();
        if (containsAny(text, "组态王", "kingview", "king view")) {
            result.add("kingview-");
        }
        if (containsAny(text, "kingscada", "king scada", "scada", "ksp", "portal")) {
            result.add("kingscada-");
        }
        if (containsStandaloneKs(text)) {
            result.add("kingscada-");
        }
        if (containsAny(text, "kinghistorian", "king historian", "工业库")) {
            result.add("kingscada-kh");
        }
        if (containsStandaloneKh(text)) {
            result.add("kingscada-kh");
        }
        return result;
    }

    private boolean containsAny(String text, String... needles) {
        for (String needle : needles) {
            if (text.contains(needle)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsStandaloneKs(String text) {
        return text.matches(".*(^|[^a-z0-9])ks([^a-z0-9]|$).*");
    }

    private boolean containsStandaloneKh(String text) {
        return text.matches(".*(^|[^a-z0-9])kh([^a-z0-9]|$).*");
    }

    private List<String> usedSkills(List<SkillSource> sources) {
        return sources.stream()
                .map(SkillSource::skillName)
                .distinct()
                .toList();
    }

    private String extractJson(String raw) {
        String cleaned = raw.trim();
        if (cleaned.startsWith("```")) {
            cleaned = cleaned.replaceFirst("^```[a-zA-Z]*\\s*", "");
            cleaned = cleaned.replaceFirst("\\s*```$", "");
        }
        int start = cleaned.indexOf('{');
        int end = cleaned.lastIndexOf('}');
        if (start < 0 || end <= start) {
            throw new IllegalArgumentException("json object not found");
        }
        return cleaned.substring(start, end + 1);
    }

    private void emit(Consumer<AgentEvent> events, String name, Object data) {
        events.accept(new AgentEvent(name, data));
    }
}
