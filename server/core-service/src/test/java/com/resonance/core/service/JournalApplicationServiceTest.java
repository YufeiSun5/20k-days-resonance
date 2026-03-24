package com.resonance.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.resonance.core.domain.RecommendationType;
import com.resonance.core.entity.Journal;
import com.resonance.core.entity.RecommendationLog;
import com.resonance.core.entity.WifiSpace;
import com.resonance.core.repository.JournalRepository;
import com.resonance.core.repository.RecommendationLogRepository;
import com.resonance.core.repository.WifiSpaceRepository;
import com.resonance.core.web.CreateJournalRequest;
import com.resonance.core.web.JournalResponse;
import com.resonance.core.web.RecommendJournalRequest;
import com.resonance.core.web.RecommendationResponse;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JournalApplicationServiceTest {

    @Mock
    private JournalRepository journalRepository;

    @Mock
    private WifiSpaceRepository wifiSpaceRepository;

    @Mock
    private RecommendationLogRepository recommendationLogRepository;

    @InjectMocks
    private JournalApplicationService journalApplicationService;

    @Test
    void createJournalPersistsMinimumFields() {
        when(journalRepository.save(any(Journal.class))).thenAnswer(invocation -> {
            Journal journal = invocation.getArgument(0);
            journal.setId(UUID.randomUUID());
            return journal;
        });

        UUID userId = UUID.randomUUID();
        JournalResponse response = journalApplicationService.createJournal(
                new CreateJournalRequest(userId, "礼拜一", "今天先把最小闭环跑通。", (short) 1));

        assertEquals(userId, response.userId());
        assertEquals("礼拜一", response.title());
        assertEquals(1, response.originalWeekday());
    }

    @Test
    void recommendReturnsWeekdayHitAndLogsSpaceResonance() {
        UUID userId = UUID.randomUUID();
        UUID journalId = UUID.randomUUID();
        UUID spaceId = UUID.randomUUID();

        Journal journal = new Journal();
        journal.setId(journalId);
        journal.setUserId(userId);
        journal.setTitle("礼拜一");
        journal.setContent("weekday hit");
        journal.setOriginalWeekday((short) 1);

        when(wifiSpaceRepository.findByHashedBssid("demo-space-home")).thenReturn(Optional.empty());
        when(wifiSpaceRepository.save(any(WifiSpace.class))).thenAnswer(invocation -> {
            WifiSpace wifiSpace = invocation.getArgument(0);
            wifiSpace.setId(spaceId);
            return wifiSpace;
        });
        when(journalRepository.findFirstByUserIdAndOriginalWeekdayAndDeletedFalseOrderByCreatedAtDesc(userId, (short) 1))
                .thenReturn(Optional.of(journal));
        when(recommendationLogRepository.save(any(RecommendationLog.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RecommendationResponse response = journalApplicationService.recommend(
                new RecommendJournalRequest(userId, "demo-space-home", "Home WiFi", (short) 1));

        assertTrue(response.found());
        assertEquals("SPACE_RESONANCE", response.recommendationType());
        assertEquals(spaceId, response.spaceId());
        assertNotNull(response.journal());

        ArgumentCaptor<RecommendationLog> logCaptor = ArgumentCaptor.forClass(RecommendationLog.class);
        verify(recommendationLogRepository).save(logCaptor.capture());
        assertEquals(RecommendationType.SPACE_RESONANCE, logCaptor.getValue().getRecommendationType());
        assertEquals(journalId, logCaptor.getValue().getRecommendedJournalId());
        assertEquals(spaceId, logCaptor.getValue().getSourceSpaceId());
    }

    @Test
    void recommendFallsBackToLatestJournalWhenWeekdayMisses() {
        UUID userId = UUID.randomUUID();
        Journal latestJournal = new Journal();
        latestJournal.setId(UUID.randomUUID());
        latestJournal.setUserId(userId);
        latestJournal.setTitle("最近一条");
        latestJournal.setContent("fallback");
        latestJournal.setOriginalWeekday((short) 3);

        when(journalRepository.findFirstByUserIdAndOriginalWeekdayAndDeletedFalseOrderByCreatedAtDesc(userId, (short) 1))
                .thenReturn(Optional.empty());
        when(journalRepository.findFirstByUserIdAndDeletedFalseOrderByCreatedAtDesc(userId))
                .thenReturn(Optional.of(latestJournal));
        when(recommendationLogRepository.save(any(RecommendationLog.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RecommendationResponse response = journalApplicationService.recommend(
                new RecommendJournalRequest(userId, null, null, (short) 1));

        assertTrue(response.found());
        assertEquals("RANDOM", response.recommendationType());
        assertNull(response.spaceId());
        assertEquals(latestJournal.getId(), response.journal().id());
    }

    @Test
    void recommendReturnsEmptyResultWhenUserHasNoJournal() {
        UUID userId = UUID.randomUUID();

        when(journalRepository.findFirstByUserIdAndOriginalWeekdayAndDeletedFalseOrderByCreatedAtDesc(eq(userId), anyShort()))
                .thenReturn(Optional.empty());
        when(journalRepository.findFirstByUserIdAndDeletedFalseOrderByCreatedAtDesc(userId))
                .thenReturn(Optional.empty());

        RecommendationResponse response = journalApplicationService.recommend(
                new RecommendJournalRequest(userId, null, null, (short) 1));

        assertFalse(response.found());
        assertNull(response.journal());
    }
}