package com.resonance.profile.entity;

import com.resonance.profile.domain.AuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "auth_identity")
/**
 * CN: 身份绑定实体，负责把统一用户主体和微信小程序 / 邮箱等登录来源连接起来。
 * EN: Identity binding entity that connects the unified user record with login providers such as WeChat Mini Program and email.
 * JP: 統一ユーザー主体を WeChat ミニアプリやメールなどのログイン提供元と結び付ける認証識別子エンティティです。
 */
public class AuthIdentity {

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private ProfileUser user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    /**
     * CN: provider 决定校验规则和后续登录流转，不能当普通枚举摆设。
     * EN: provider drives validation rules and downstream login flow. It is not a cosmetic enum.
     * JP: provider は検証規則と後続のログインフローを決めるため、飾りの enum ではありません。
     */
    private AuthProvider provider;

    @Column(name = "provider_user_id", length = 128)
    private String providerUserId;

    @Column(length = 255)
    private String email;

    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Column(name = "is_primary", nullable = false)
    private boolean primaryIdentity = true;

    @Column(name = "verified_at")
    private Instant verifiedAt;

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
        if (verifiedAt == null) {
            verifiedAt = now;
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

    public ProfileUser getUser() {
        return user;
    }

    public void setUser(ProfileUser user) {
        this.user = user;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isPrimaryIdentity() {
        return primaryIdentity;
    }

    public void setPrimaryIdentity(boolean primaryIdentity) {
        this.primaryIdentity = primaryIdentity;
    }

    public Instant getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Instant verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}