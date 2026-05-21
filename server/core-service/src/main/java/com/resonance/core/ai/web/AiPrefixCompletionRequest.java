package com.resonance.core.ai.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record AiPrefixCompletionRequest(
        @NotNull UUID userId,
        UUID conversationId,
        @NotBlank String prefix,
        List<String> skillNames) {
}
