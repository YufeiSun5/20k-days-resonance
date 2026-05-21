package com.resonance.core.ai.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.resonance.core.ai.AiProperties;
import com.resonance.core.ai.repository.AiConversationRepository;
import com.resonance.core.ai.repository.AiMessageRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class AiConversationServiceTest {

    @Mock
    private AiConversationRepository conversationRepository;

    @Mock
    private AiMessageRepository messageRepository;

    @Mock
    private SkillIndexService skillIndexService;

    @Mock
    private SkillRouterService skillRouterService;

    @Mock
    private SkillDependencyService skillDependencyService;

    @Mock
    private AgentLoopService agentLoopService;

    @Mock
    private DeepseekClient deepseekClient;

    @Mock
    private StringRedisTemplate redisTemplate;

    @Test
    void listMessagesRejectsConversationOutsideUserScope() {
        UUID conversationId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        when(conversationRepository.findByIdAndUserId(conversationId, userId)).thenReturn(Optional.empty());

        AiConversationService service = new AiConversationService(
                new AiProperties(),
                conversationRepository,
                messageRepository,
                skillIndexService,
                skillRouterService,
                skillDependencyService,
                agentLoopService,
                deepseekClient,
                redisTemplate);

        assertThrows(ResponseStatusException.class, () -> service.listMessages(conversationId, userId));
    }
}
