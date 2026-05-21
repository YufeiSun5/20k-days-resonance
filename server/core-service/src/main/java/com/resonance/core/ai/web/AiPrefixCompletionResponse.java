package com.resonance.core.ai.web;

import java.util.UUID;

public record AiPrefixCompletionResponse(
        UUID conversationId,
        String completion) {
}
