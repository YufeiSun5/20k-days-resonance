package com.resonance.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "journals")
/**
 * CN: 日记实体先承担最小推荐闭环所需字段，后续再扩展标签、情绪分析和更多可见性策略。
 * EN: The journal entity currently carries only the fields needed for the minimum recommendation loop. Tags, richer AI metadata, and more visibility rules can be layered later.
 * JP: 日記エンティティは現時点では最小限の推薦ループに必要な項目だけを持ちます。タグや詳細な AI メタデータ、可視性ルールは後で段階的に拡張します。
 */
public class Journal {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    /**
     * CN: 这里只保存 profile 侧用户 UUID，不引入跨库外键耦合。
     * EN: Only the profile-side user UUID is stored here. No cross-database foreign key coupling is introduced.
     * JP: ここでは profile 側のユーザー UUID だけを保持し、クロスデータベース外部キー結合は持ち込みません。
     */
    private UUID userId;

    @Column(length = 200)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "original_weekday", nullable = false)
    /**
     * CN: originalWeekday 是当前推荐退化链路的关键字段，先保证它稳定可用，再谈更复杂的向量召回。
     * EN: originalWeekday is the key field for the current recommendation fallback chain. Keep it stable first; vector retrieval can come later.
     * JP: originalWeekday は現在の推薦フォールバック経路の要です。まずこれを安定させ、その後でベクトル検索を載せます。
     */
    private short originalWeekday;

    @Column(name = "emotion_score", precision = 5, scale = 2)
    private BigDecimal emotionScore;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "insight_keywords", nullable = false, columnDefinition = "jsonb")
    private List<String> insightKeywords = new ArrayList<>();

    @Column(nullable = false, length = 20)
    private String visibility = "PRIVATE";

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        Instant now = Instant.now();
        if (id == null) {
            id = UUID.randomUUID();
        }
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public short getOriginalWeekday() {
        return originalWeekday;
    }

    public void setOriginalWeekday(short originalWeekday) {
        this.originalWeekday = originalWeekday;
    }

    public BigDecimal getEmotionScore() {
        return emotionScore;
    }

    public void setEmotionScore(BigDecimal emotionScore) {
        this.emotionScore = emotionScore;
    }

    public List<String> getInsightKeywords() {
        return insightKeywords;
    }

    public void setInsightKeywords(List<String> insightKeywords) {
        this.insightKeywords = insightKeywords;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}