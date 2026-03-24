package com.resonance.core.repository;

import com.resonance.core.entity.RecommendationLog;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationLogRepository extends JpaRepository<RecommendationLog, UUID> {
}