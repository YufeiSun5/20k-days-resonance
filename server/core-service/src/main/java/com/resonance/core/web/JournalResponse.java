package com.resonance.core.web;

import com.resonance.core.entity.Journal;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record JournalResponse(
        UUID id,
        UUID userId,
        String title,
        String content,
        short originalWeekday,
        BigDecimal emotionScore,
        List<String> insightKeywords,
        String visibility,
        Instant createdAt) {

    public static JournalResponse from(Journal journal) {
        return new JournalResponse(
                journal.getId(),
                journal.getUserId(),
                journal.getTitle(),
                journal.getContent(),
                journal.getOriginalWeekday(),
                journal.getEmotionScore(),
                journal.getInsightKeywords(),
                journal.getVisibility(),
                journal.getCreatedAt());
    }
}