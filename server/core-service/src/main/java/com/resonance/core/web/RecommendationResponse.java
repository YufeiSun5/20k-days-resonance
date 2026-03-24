package com.resonance.core.web;

import java.util.UUID;

public record RecommendationResponse(
        boolean found,
        String recommendationType,
        String message,
        String hashedBssid,
        UUID spaceId,
        Short sourceWeekday,
        JournalResponse journal) {
}