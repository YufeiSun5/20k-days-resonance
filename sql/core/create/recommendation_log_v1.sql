CREATE TABLE IF NOT EXISTS recommendation_log (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    recommendation_type VARCHAR(30) NOT NULL,
    source_space_id UUID REFERENCES wifi_space (id) ON DELETE SET NULL,
    source_weekday SMALLINT,
    recommended_journal_id UUID REFERENCES journals (id) ON DELETE SET NULL,
    clicked_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_recommendation_log_type CHECK (
        recommendation_type IN ('SPACE_RESONANCE', 'FALLBACK_AI', 'RANDOM', 'WEEKDAY_RECALL')
    ),
    CONSTRAINT chk_recommendation_log_weekday CHECK (
        source_weekday IS NULL OR source_weekday BETWEEN 1 AND 7
    )
);

COMMENT ON TABLE recommendation_log IS 'CN: 推荐日志表，用于记录推荐结果与点击反馈；EN: Recommendation log table recording recommendation outputs and click feedback; JP: 推薦結果とクリック反応を記録する推薦ログテーブル';
COMMENT ON COLUMN recommendation_log.id IS 'CN: 推荐日志主键 UUID；EN: Recommendation log primary key UUID; JP: 推薦ログ主キー UUID';
COMMENT ON COLUMN recommendation_log.user_id IS 'CN: 用户 ID，来自 resonance_profile 库，不建立跨库外键；EN: User id from resonance_profile without a cross-database foreign key; JP: resonance_profile 由来のユーザー ID。クロスデータベース外部キーは作成しません';
COMMENT ON COLUMN recommendation_log.recommendation_type IS 'CN: 推荐类型；EN: Recommendation type; JP: 推薦タイプ';
COMMENT ON COLUMN recommendation_log.source_space_id IS 'CN: 触发推荐的空间 ID；EN: Source space id that triggered the recommendation; JP: 推薦トリガーとなった空間 ID';
COMMENT ON COLUMN recommendation_log.source_weekday IS 'CN: 触发推荐的星期几；EN: Source weekday used for recommendation; JP: 推薦に利用した曜日';
COMMENT ON COLUMN recommendation_log.recommended_journal_id IS 'CN: 被推荐的日记 ID；EN: Recommended journal id; JP: 推薦された日記 ID';
COMMENT ON COLUMN recommendation_log.clicked_at IS 'CN: 用户点击推荐的时间；EN: Timestamp when the recommendation was clicked; JP: 推薦がクリックされた日時';
COMMENT ON COLUMN recommendation_log.created_at IS 'CN: 推荐生成时间；EN: Recommendation creation timestamp; JP: 推薦生成日時';

CREATE INDEX IF NOT EXISTS idx_recommendation_log_user_time ON recommendation_log (user_id, created_at DESC);
