CREATE TABLE IF NOT EXISTS wifi_space (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    hashed_bssid CHAR(64) NOT NULL,
    ssid_name VARCHAR(255),
    first_seen_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_seen_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE wifi_space IS 'CN: Wi-Fi 空间表，记录哈希化的 BSSID 空间；EN: Wi-Fi space table storing hashed BSSID-based spaces; JP: ハッシュ化した BSSID ベースの空間を記録する Wi-Fi 空間テーブル';
COMMENT ON COLUMN wifi_space.id IS 'CN: 空间主键 UUID；EN: Space primary key UUID; JP: 空間主キー UUID';
COMMENT ON COLUMN wifi_space.hashed_bssid IS 'CN: 经过哈希处理的 BSSID；EN: Hashed BSSID value; JP: ハッシュ化された BSSID';
COMMENT ON COLUMN wifi_space.ssid_name IS 'CN: 可选的 SSID 名称；EN: Optional SSID name; JP: 任意の SSID 名';
COMMENT ON COLUMN wifi_space.first_seen_at IS 'CN: 首次出现时间；EN: First seen timestamp; JP: 初回観測日時';
COMMENT ON COLUMN wifi_space.last_seen_at IS 'CN: 最近出现时间；EN: Last seen timestamp; JP: 最終観測日時';

CREATE UNIQUE INDEX IF NOT EXISTS uk_wifi_space_hashed_bssid ON wifi_space (hashed_bssid);
