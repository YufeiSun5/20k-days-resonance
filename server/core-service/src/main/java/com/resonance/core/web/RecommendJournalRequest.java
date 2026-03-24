package com.resonance.core.web;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RecommendJournalRequest(
        @NotNull UUID userId,
        String hashedBssid,
        String ssidName,
        @Min(1) @Max(7) Short weekday) {
}