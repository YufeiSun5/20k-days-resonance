package com.resonance.core.web;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateJournalRequest(
        @NotNull UUID userId,
        String title,
        @NotBlank String content,
        @Min(1) @Max(7) short originalWeekday) {
}