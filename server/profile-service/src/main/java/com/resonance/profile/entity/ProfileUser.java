package com.resonance.profile.entity;

import com.resonance.profile.domain.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
/**
 * CN: 用户主实体，承载账号侧最小可联调信息，后续所有 profile 能力都围绕它扩展。
 * EN: Core user entity for the profile domain. It holds the minimum account data for integration and is the anchor for future profile capabilities.
 * JP: profile ドメインの中核ユーザー実体です。最小限の連携用アカウント情報を保持し、以後の profile 機能はこれを中心に拡張します。
 */
public class ProfileUser {

    @Id
    /**
     * CN: 跨服务引用用户时统一用这个 UUID，不在 core 库里建跨库外键。
     * EN: This UUID is the stable cross-service user reference. Core stores the value directly without a cross-database foreign key.
     * JP: サービス間でユーザーを参照するときはこの UUID を使います。core 側ではクロスデータベース外部キーを作りません。
     */
    private UUID id;

    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "birth_date")
    /**
     * CN: 出生日期是人生余额算法的直接输入，不是装饰字段。
     * EN: Birth date is a direct input to the life-balance calculation, not decorative profile data.
     * JP: 生年月日は人生残量計算の直接入力であり、飾りのプロフィール項目ではありません。
     */
    private LocalDate birthDate;

    @Column(nullable = false, length = 20)
    private String locale = "zh-CN";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status = UserStatus.ACTIVE;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}