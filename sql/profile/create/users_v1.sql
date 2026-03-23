CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    display_name VARCHAR(100) NOT NULL,
    avatar_url TEXT,
    birth_date DATE,
    locale VARCHAR(20) NOT NULL DEFAULT 'zh-CN',
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE users IS 'CN: 用户主表，表示系统中的统一用户主体；EN: Master user table representing the unified user entity; JP: システム内の統一ユーザー主体を表すマスターテーブル';
COMMENT ON COLUMN users.id IS 'CN: 用户主键 UUID；EN: User primary key UUID; JP: ユーザー主キー UUID';
COMMENT ON COLUMN users.display_name IS 'CN: 用户显示昵称；EN: User display name; JP: ユーザーの表示名';
COMMENT ON COLUMN users.avatar_url IS 'CN: 用户头像地址；EN: User avatar URL; JP: ユーザーのアバター URL';
COMMENT ON COLUMN users.birth_date IS 'CN: 用户出生日期，用于计算人生余额；EN: Birth date used for life balance calculation; JP: 人生残量計算に使う生年月日';
COMMENT ON COLUMN users.locale IS 'CN: 用户语言偏好；EN: User locale preference; JP: ユーザーの言語設定';
COMMENT ON COLUMN users.status IS 'CN: 用户状态，如 ACTIVE 或 DISABLED；EN: User status such as ACTIVE or DISABLED; JP: ACTIVE や DISABLED などのユーザー状態';
COMMENT ON COLUMN users.created_at IS 'CN: 创建时间；EN: Creation timestamp; JP: 作成日時';
COMMENT ON COLUMN users.updated_at IS 'CN: 更新时间；EN: Last update timestamp; JP: 更新日時';

CREATE INDEX IF NOT EXISTS idx_users_status ON users (status);
