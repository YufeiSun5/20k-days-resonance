package com.resonance.core.ai.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record AiChatStreamRequest(
        @NotNull UUID userId,
        UUID conversationId,
        @NotBlank String question,
        List<String> skillNames) {
}
