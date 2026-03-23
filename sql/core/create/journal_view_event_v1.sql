CREATE TABLE IF NOT EXISTS journal_view_event (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    journal_id UUID NOT NULL REFERENCES journals (id) ON DELETE CASCADE,
    space_id UUID REFERENCES wifi_space (id) ON DELETE SET NULL,
    event_type VARCHAR(30) NOT NULL DEFAULT 'VIEW',
    duration_ms INTEGER,
    event_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_journal_view_event_type CHECK (
        event_type IN ('VIEW', 'DETAIL_OPEN', 'FAVORITE', 'SHARE')
    )
);

COMMENT ON TABLE journal_view_event IS 'CN: 日记浏览事件表，用于隐式协同过滤与空间共振分析；EN: Journal view event table for implicit collaborative filtering and space resonance analysis; JP: 暗黙的協調フィルタリングと空間共鳴分析のための日記閲覧イベントテーブル';
COMMENT ON COLUMN journal_view_event.id IS 'CN: 事件主键 UUID；EN: Event primary key UUID; JP: イベント主キー UUID';
COMMENT ON COLUMN journal_view_event.user_id IS 'CN: 用户 ID，来自 resonance_profile 库，不建立跨库外键；EN: User id from resonance_profile without a cross-database foreign key; JP: resonance_profile 由来のユーザー ID。クロスデータベース外部キーは作成しません';
COMMENT ON COLUMN journal_view_event.journal_id IS 'CN: 被查看的日记 ID；EN: Viewed journal id; JP: 閲覧された日記 ID';
COMMENT ON COLUMN journal_view_event.space_id IS 'CN: 发生行为的空间 ID；EN: Space id where the event happened; JP: イベント発生空間 ID';
COMMENT ON COLUMN journal_view_event.event_type IS 'CN: 行为类型，如 VIEW 或 FAVORITE；EN: Event type such as VIEW or FAVORITE; JP: VIEW や FAVORITE などのイベント種別';
COMMENT ON COLUMN journal_view_event.duration_ms IS 'CN: 停留时长，单位毫秒；EN: Dwell time in milliseconds; JP: 滞在時間（ミリ秒）';
COMMENT ON COLUMN journal_view_event.event_at IS 'CN: 事件发生时间；EN: Event timestamp; JP: イベント発生日時';

CREATE INDEX IF NOT EXISTS idx_journal_view_event_user_time ON journal_view_event (user_id, event_at DESC);
CREATE INDEX IF NOT EXISTS idx_journal_view_event_space_time ON journal_view_event (space_id, event_at DESC);
CREATE INDEX IF NOT EXISTS idx_journal_view_event_journal_id ON journal_view_event (journal_id);
