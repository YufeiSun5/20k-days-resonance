package com.resonance.core.ai.web;

import com.resonance.core.ai.entity.AiMessage;
import java.time.Instant;
import java.util.UUID;

public record AiMessageResponse(
        UUID id,
        UUID conversationId,
        UUID userId,
        String role,
        String content,
        String modelName,
        String status,
        String errorMessage,
        Instant createdAt) {

    public static AiMessageResponse from(AiMessage message) {
        return new AiMessageResponse(
                message.getId(),
                message.getConversationId(),
                message.getUserId(),
                message.getRole(),
                message.getContent(),
                message.getModelName(),
                message.getStatus(),
                message.getErrorMessage(),
                message.getCreatedAt());
    }
}
