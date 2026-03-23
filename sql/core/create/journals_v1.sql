CREATE TABLE IF NOT EXISTS journals (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    title VARCHAR(200),
    content TEXT NOT NULL,
    original_weekday SMALLINT NOT NULL,
    emotion_score NUMERIC(5,2),
    insight_keywords JSONB NOT NULL DEFAULT '[]'::jsonb,
    visibility VARCHAR(20) NOT NULL DEFAULT 'PRIVATE',
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_journals_weekday CHECK (original_weekday BETWEEN 1 AND 7),
    CONSTRAINT chk_journals_visibility CHECK (visibility IN ('PRIVATE', 'FRIENDS', 'PUBLIC'))
);

COMMENT ON TABLE journals IS 'CN: 日记主表，存储用户日记与推荐相关元数据；EN: Journal master table storing diary content and recommendation metadata; JP: 日記本文と推薦用メタデータを保持する日記メインテーブル';
COMMENT ON COLUMN journals.id IS 'CN: 日记主键 UUID；EN: Journal primary key UUID; JP: 日記主キー UUID';
COMMENT ON COLUMN journals.user_id IS 'CN: 用户 ID，来自 resonance_profile 库，不建立跨库外键；EN: User id from resonance_profile without a cross-database foreign key; JP: resonance_profile 由来のユーザー ID。クロスデータベース外部キーは作成しません';
COMMENT ON COLUMN journals.title IS 'CN: 日记标题；EN: Journal title; JP: 日記タイトル';
COMMENT ON COLUMN journals.content IS 'CN: 日记正文；EN: Journal content body; JP: 日記本文';
COMMENT ON COLUMN journals.original_weekday IS 'CN: 原始写作星期几，1 到 7；EN: Original weekday of writing, from 1 to 7; JP: 作成時の曜日、1 から 7';
COMMENT ON COLUMN journals.emotion_score IS 'CN: AI 语义分析输出的情感分值；EN: Emotion score produced by AI semantic analysis; JP: AI 意味解析で算出した感情スコア';
COMMENT ON COLUMN journals.insight_keywords IS 'CN: AI 提取的关键词列表；EN: Keyword list extracted by AI; JP: AI が抽出したキーワード一覧';
COMMENT ON COLUMN journals.visibility IS 'CN: 可见性级别；EN: Visibility level; JP: 公開範囲';
COMMENT ON COLUMN journals.is_deleted IS 'CN: 逻辑删除标记；EN: Logical deletion flag; JP: 論理削除フラグ';
COMMENT ON COLUMN journals.created_at IS 'CN: 创建时间；EN: Creation timestamp; JP: 作成日時';
COMMENT ON COLUMN journals.updated_at IS 'CN: 更新时间；EN: Last update timestamp; JP: 更新日時';

CREATE INDEX IF NOT EXISTS idx_journals_user_weekday ON journals (user_id, original_weekday);
CREATE INDEX IF NOT EXISTS idx_journals_created_at ON journals (created_at DESC);
CREATE INDEX IF NOT EXISTS idx_journals_keywords ON journals USING GIN (insight_keywords);
