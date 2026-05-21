package com.resonance.core.ai.web;

import com.resonance.core.ai.entity.AiConversation;
import java.time.Instant;
import java.util.UUID;

public record AiConversationResponse(
        UUID id,
        UUID userId,
        String title,
        Instant createdAt,
        Instant updatedAt) {

    public static AiConversationResponse from(AiConversation conversation) {
        return new AiConversationResponse(
                conversation.getId(),
                conversation.getUserId(),
                conversation.getTitle(),
                conversation.getCreatedAt(),
                conversation.getUpdatedAt());
    }
}
