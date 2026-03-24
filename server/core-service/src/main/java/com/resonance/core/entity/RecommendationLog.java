package com.resonance.core.entity;

import com.resonance.core.domain.RecommendationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "recommendation_log")
/**
 * CN: 推荐日志实体先把“给了谁、为什么给、给了什么”记下来，后续点击反馈和排序优化都依赖这层记录。
 * EN: The recommendation log first captures who received what and why. Later click feedback and ranking improvements build on top of this record.
 * JP: 推薦ログエンティティはまず「誰に、なぜ、何を出したか」を記録します。後続のクリック反応や順位最適化はこの記録を土台にします。
 */
public class RecommendationLog {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "recommendation_type", nullable = false, length = 30)
    private RecommendationType recommendationType;

    @Column(name = "source_space_id")
    private UUID sourceSpaceId;

    @Column(name = "source_weekday")
    private Short sourceWeekday;

    @Column(name = "recommended_journal_id")
    private UUID recommendedJournalId;

    @Column(name = "clicked_at")
    private Instant clickedAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        createdAt = Instant.now();
    }

    public UUID getUserId() {
        return userId;
    }

    public RecommendationType getRecommendationType() {
        return recommendationType;
    }

    public UUID getSourceSpaceId() {
        return sourceSpaceId;
    }

    public Short getSourceWeekday() {
        return sourceWeekday;
    }

    public UUID getRecommendedJournalId() {
        return recommendedJournalId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setRecommendationType(RecommendationType recommendationType) {
        this.recommendationType = recommendationType;
    }

    public void setSourceSpaceId(UUID sourceSpaceId) {
        this.sourceSpaceId = sourceSpaceId;
    }

    public void setSourceWeekday(Short sourceWeekday) {
        this.sourceWeekday = sourceWeekday;
    }

    public void setRecommendedJournalId(UUID recommendedJournalId) {
        this.recommendedJournalId = recommendedJournalId;
    }
}