CREATE TABLE IF NOT EXISTS auth_identity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    provider VARCHAR(30) NOT NULL,
    provider_user_id VARCHAR(128),
    email VARCHAR(255),
    password_hash VARCHAR(255),
    is_primary BOOLEAN NOT NULL DEFAULT FALSE,
    verified_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_auth_identity_provider CHECK (provider IN ('wechat_miniapp', 'email')),
    CONSTRAINT chk_auth_identity_wechat CHECK (
        provider <> 'wechat_miniapp' OR provider_user_id IS NOT NULL
    ),
    CONSTRAINT chk_auth_identity_email CHECK (
        provider <> 'email' OR (email IS NOT NULL AND password_hash IS NOT NULL)
    )
);

COMMENT ON TABLE auth_identity IS 'CN: 用户身份绑定表，兼容微信小程序与邮箱登录；EN: Identity binding table supporting WeChat Mini Program and email login; JP: WeChat ミニアプリとメールログインに対応する認証識別子テーブル';
COMMENT ON COLUMN auth_identity.id IS 'CN: 身份记录主键 UUID；EN: Identity record primary key UUID; JP: 認証識別子レコードの主キー UUID';
COMMENT ON COLUMN auth_identity.user_id IS 'CN: 关联用户主表 ID；EN: Foreign key to users.id; JP: users.id への外部キー';
COMMENT ON COLUMN auth_identity.provider IS 'CN: 登录来源，如 wechat_miniapp 或 email；EN: Login provider such as wechat_miniapp or email; JP: wechat_miniapp や email などのログイン提供元';
COMMENT ON COLUMN auth_identity.provider_user_id IS 'CN: 第三方平台用户唯一标识，如 openid；EN: Unique provider-side user id such as openid; JP: openid などの外部プラットフォーム側ユーザー識別子';
COMMENT ON COLUMN auth_identity.email IS 'CN: 邮箱登录账号；EN: Email login account; JP: メールログイン用アカウント';
COMMENT ON COLUMN auth_identity.password_hash IS 'CN: 邮箱登录密码哈希；EN: Password hash for email login; JP: メールログイン用パスワードハッシュ';
COMMENT ON COLUMN auth_identity.is_primary IS 'CN: 是否为主身份；EN: Whether this is the primary identity; JP: 主認証識別子かどうか';
COMMENT ON COLUMN auth_identity.verified_at IS 'CN: 身份验证完成时间；EN: Verification completion timestamp; JP: 認証完了日時';
COMMENT ON COLUMN auth_identity.created_at IS 'CN: 创建时间；EN: Creation timestamp; JP: 作成日時';
COMMENT ON COLUMN auth_identity.updated_at IS 'CN: 更新时间；EN: Last update timestamp; JP: 更新日時';

CREATE UNIQUE INDEX IF NOT EXISTS uk_auth_identity_provider_user
    ON auth_identity (provider, provider_user_id)
    WHERE provider_user_id IS NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS uk_auth_identity_email
    ON auth_identity (provider, email)
    WHERE email IS NOT NULL;

CREATE INDEX IF NOT EXISTS idx_auth_identity_user_id ON auth_identity (user_id);
