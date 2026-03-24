package com.resonance.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "wifi_space")
/**
 * CN: Wi-Fi 空间实体只保留后端真正需要的稳定标识，不把原始 BSSID 明文塞进数据库。
 * EN: The Wi-Fi space entity stores only the stable backend-facing identifier. Raw BSSID is intentionally not persisted in plain text.
 * JP: Wi-Fi 空間エンティティには、バックエンドが本当に必要とする安定識別子だけを保持し、生の BSSID は平文保存しません。
 */
public class WifiSpace {

    @Id
    private UUID id;

    @Column(name = "hashed_bssid", nullable = false, length = 64)
    /**
     * CN: hashedBssid 是空间共振的核心索引键，前后端都围绕它对齐空间语义。
     * EN: hashedBssid is the core lookup key for spatial resonance. Both frontend and backend align space semantics around it.
     * JP: hashedBssid は空間共鳴の中心的な検索キーであり、フロントとバックエンドはこれを基準に空間意味を揃えます。
     */
    private String hashedBssid;

    @Column(name = "ssid_name")
    private String ssidName;

    @Column(name = "first_seen_at", nullable = false)
    private Instant firstSeenAt;

    @Column(name = "last_seen_at", nullable = false)
    private Instant lastSeenAt;

    @PrePersist
    void prePersist() {
        Instant now = Instant.now();
        if (id == null) {
            id = UUID.randomUUID();
        }
        firstSeenAt = now;
        lastSeenAt = now;
    }

    @PreUpdate
    void preUpdate() {
        lastSeenAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHashedBssid() {
        return hashedBssid;
    }

    public void setHashedBssid(String hashedBssid) {
        this.hashedBssid = hashedBssid;
    }

    public String getSsidName() {
        return ssidName;
    }

    public void setSsidName(String ssidName) {
        this.ssidName = ssidName;
    }
}