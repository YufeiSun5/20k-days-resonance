package com.resonance.core.ai.service;

import com.resonance.core.ai.AiProperties;
import com.resonance.core.ai.entity.AiConversation;
import com.resonance.core.ai.entity.AiMessage;
import com.resonance.core.ai.repository.AiConversationRepository;
import com.resonance.core.ai.repository.AiMessageRepository;
import com.resonance.core.ai.web.AiChatStreamRequest;
import com.resonance.core.ai.web.AiConversationResponse;
import com.resonance.core.ai.web.AiMessageResponse;
import com.resonance.core.ai.web.AiPrefixCompletionRequest;
import com.resonance.core.ai.web.AiPrefixCompletionResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
/**
 * CN: AI 会话服务负责业务事实：用户隔离、历史拼接、skill 召回、并发限制和消息落库。
 * EN: The AI conversation service owns business facts: user isolation, history assembly, skill recall, concurrency limits, and message persistence.
 * JP: AI 会話サービスは業務上の事実を扱います。ユーザー分離、履歴連結、skill 検索、並行制限、メッセージ保存です。
 */
public class AiConversationService {

    private static final long SSE_TIMEOUT_MS = 120_000L;

    private final AiProperties properties;
    private final AiConversationRepository conversationRepository;
    private final AiMessageRepository messageRepository;
    private final SkillIndexService skillIndexService;
    private final SkillRouterService skillRouterService;
    private final SkillDependencyService skillDependencyService;
    private final AgentLoopService agentLoopService;
    private final DeepseekClient deepseekClient;
    private final StringRedisTemplate redisTemplate;

    public AiConversationService(
            AiProperties properties,
            AiConversationRepository conversationRepository,
            AiMessageRepository messageRepository,
            SkillIndexService skillIndexService,
            SkillRouterService skillRouterService,
            SkillDependencyService skillDependencyService,
            AgentLoopService agentLoopService,
            DeepseekClient deepseekClient,
            StringRedisTemplate redisTemplate) {
        this.properties = properties;
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.skillIndexService = skillIndexService;
        this.skillRouterService = skillRouterService;
        this.skillDependencyService = skillDependencyService;
        this.agentLoopService = agentLoopService;
        this.deepseekClient = deepseekClient;
        this.redisTemplate = redisTemplate;
    }

    public SseEmitter streamChat(AiChatStreamRequest request) {
        SseEmitter emitter = new SseEmitter(SSE_TIMEOUT_MS);
        if (!tryAcquire(request.userId())) {
            sendAndComplete(emitter, "error", Map.of("message", "too many concurrent AI requests for this user"));
            return emitter;
        }

        CompletableFuture.runAsync(() -> {
            try {
                streamChatInternal(request, emitter);
            } catch (Exception error) {
                send(emitter, "error", Map.of("message", error.getMessage() == null ? "AI request failed" : error.getMessage()));
                emitter.complete();
            } finally {
                release(request.userId());
            }
        });
        return emitter;
    }

    @Transactional(readOnly = true)
    public List<AiConversationResponse> listConversations(UUID userId) {
        return conversationRepository.findTop20ByUserIdOrderByUpdatedAtDesc(userId).stream()
                .map(AiConversationResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AiMessageResponse> listMessages(UUID conversationId, UUID userId) {
        ensureConversation(conversationId, userId);
        return messageRepository.findByConversationIdAndUserIdOrderByCreatedAtAsc(conversationId, userId).stream()
                .map(AiMessageResponse::from)
                .toList();
    }

    public AiPrefixCompletionResponse completePrefix(AiPrefixCompletionRequest request) {
        AiConversation conversation = resolveConversation(request.userId(), request.conversationId(), request.prefix());
        List<String> routedSkills = routeSkills(request.prefix(), request.skillNames());
        List<SkillSource> sources = skillIndexService.search(request.prefix(), routedSkills, 8);
        List<DeepseekMessage> messages = new ArrayList<>();
        messages.add(new DeepseekMessage("system", systemPrompt(skillIndexService.buildContext(sources))));
        messages.addAll(recentHistory(conversation.getId(), request.userId()));
        messages.add(new DeepseekMessage("user", "Complete this user input with one short helpful continuation. Prefix: " + request.prefix()));
        String completion = deepseekClient.complete(messages, 80).block(Duration.ofSeconds(45));
        return new AiPrefixCompletionResponse(conversation.getId(), completion == null ? "" : completion.trim());
    }

    private void streamChatInternal(AiChatStreamRequest request, SseEmitter emitter) {
        AiConversation conversation = resolveConversation(request.userId(), request.conversationId(), request.question());
        List<DeepseekMessage> history = recentHistory(conversation.getId(), request.userId());
        saveMessage(conversation, request.userId(), "user", request.question(), null, "COMPLETED", null);
        send(emitter, "conversation", AiConversationResponse.from(conversation));

        try {
            AgentAnswerResult result = agentLoopService.streamAnswer(
                    request.question(),
                    request.skillNames(),
                    history,
                    event -> send(emitter, event.name(), event.data()),
                    delta -> send(emitter, "delta", Map.of("text", delta)));
            if (!result.fallback()) {
                send(emitter, "sources", result.sources());
                send(emitter, "agent_done", Map.of(
                        "usedSkills", result.usedSkills(),
                        "roundCount", result.roundCount(),
                        "stopReason", result.stopReason()));
            }

            String content = result.answer() == null ? "" : result.answer().trim();
            if (content.isBlank()) {
                throw new IllegalStateException("DeepSeek returned empty answer");
            }
            saveMessage(conversation, request.userId(), "assistant", content, deepseekClient.modelName(), "COMPLETED", null);
            send(emitter, "predictions", predictQuestions(request.question(), result.sources()));
            send(emitter, "done", Map.of("conversationId", conversation.getId()));
            emitter.complete();
        } catch (Exception error) {
            saveMessage(conversation, request.userId(), "assistant", "", deepseekClient.modelName(), "FAILED", error.getMessage());
            send(emitter, "error", Map.of("message", error.getMessage() == null ? "DeepSeek stream failed" : error.getMessage()));
            emitter.complete();
        }
    }

    private AiConversation resolveConversation(UUID userId, UUID conversationId, String seedText) {
        if (conversationId != null) {
            return conversationRepository.findByIdAndUserId(conversationId, userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "conversation not found"));
        }
        AiConversation conversation = new AiConversation();
        conversation.setUserId(userId);
        conversation.setTitle(resolveTitle(seedText));
        return conversationRepository.save(conversation);
    }

    private void ensureConversation(UUID conversationId, UUID userId) {
        conversationRepository.findByIdAndUserId(conversationId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "conversation not found"));
    }

    private void saveMessage(
            AiConversation conversation,
            UUID userId,
            String role,
            String content,
            String modelName,
            String status,
            String errorMessage) {
        AiMessage message = new AiMessage();
        message.setConversationId(conversation.getId());
        message.setUserId(userId);
        message.setRole(role);
        message.setContent(content == null ? "" : content);
        message.setModelName(modelName);
        message.setStatus(status);
        message.setErrorMessage(errorMessage);
        messageRepository.save(message);
        conversation.touch();
        conversationRepository.save(conversation);
    }

    private List<DeepseekMessage> recentHistory(UUID conversationId, UUID userId) {
        List<AiMessage> history = messageRepository.findByConversationIdAndUserIdOrderByCreatedAtDesc(
                conversationId,
                userId,
                PageRequest.of(0, Math.max(1, properties.getMaxHistoryMessages())));
        Collections.reverse(history);
        return history.stream()
                .filter(message -> "COMPLETED".equals(message.getStatus()))
                .filter(message -> "user".equals(message.getRole()) || "assistant".equals(message.getRole()))
                .map(message -> new DeepseekMessage(message.getRole(), message.getContent()))
                .toList();
    }

    private String systemPrompt(String skillContext) {
        return """
                You are the KingSCADA / KingHistorian / KingView skill assistant inside 20,000 Days.
                Answer based on the provided skill excerpts first.
                If the user names a specific skill, treat that skill as the requested source scope.
                If the excerpts are insufficient, say what is missing instead of inventing details.
                Do not claim that KingView documents are absent when a kingview-* skill source is present.
                Keep answers direct, concrete, and implementation-oriented.

                Skill excerpts:
                """ + skillContext;
    }

    private List<String> routeSkills(String query, List<String> requestedSkillNames) {
        List<String> availableSkillNames = skillIndexService.listSkillNames();
        List<String> routedSkillNames = skillRouterService.route(query, requestedSkillNames, availableSkillNames);
        return skillDependencyService.expand(query, routedSkillNames, availableSkillNames);
    }

    private List<String> predictQuestions(String question, List<SkillSource> sources) {
        List<String> predictions = new ArrayList<>();
        if (!sources.isEmpty()) {
            predictions.add("给我一个可直接复制的完整例子");
            predictions.add("这个写法有哪些常见坑");
        }
        if (question.contains("SQL") || question.contains("sql") || question.contains("查询")) {
            predictions.add("帮我改成按小时聚合查询");
        } else {
            predictions.add("相关函数参数怎么填");
        }
        return predictions.stream().limit(3).toList();
    }

    private String resolveTitle(String seedText) {
        String text = seedText == null || seedText.isBlank() ? "New AI conversation" : seedText.trim().replaceAll("\\s+", " ");
        return text.length() <= 60 ? text : text.substring(0, 60);
    }

    private boolean tryAcquire(UUID userId) {
        String key = inflightKey(userId);
        Long value = redisTemplate.opsForValue().increment(key);
        redisTemplate.expire(key, Duration.ofMinutes(3));
        if (value == null) {
            return false;
        }
        if (value <= properties.getMaxConcurrentPerUser()) {
            return true;
        }
        release(userId);
        return false;
    }

    private void release(UUID userId) {
        String key = inflightKey(userId);
        Long value = redisTemplate.opsForValue().decrement(key);
        if (value != null && value <= 0) {
            redisTemplate.delete(key);
        }
    }

    private String inflightKey(UUID userId) {
        return "ai:inflight:" + userId;
    }

    private void sendAndComplete(SseEmitter emitter, String event, Object data) {
        send(emitter, event, data);
        emitter.complete();
    }

    private void send(SseEmitter emitter, String event, Object data) {
        try {
            emitter.send(SseEmitter.event().name(event).data(data));
        } catch (IOException ignored) {
            emitter.complete();
        }
    }
}
