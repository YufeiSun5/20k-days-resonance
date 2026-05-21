CREATE TABLE IF NOT EXISTS ai_conversation (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    title VARCHAR(200) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE ai_conversation IS 'CN: AI 对话会话表，按用户保存 DeepSeek 问答上下文；EN: AI conversation table storing DeepSeek chat context by user; JP: ユーザーごとに DeepSeek 対話コンテキストを保存する AI 会話テーブル';
COMMENT ON COLUMN ai_conversation.id IS 'CN: 会话主键 UUID；EN: Conversation primary key UUID; JP: 会話主キー UUID';
COMMENT ON COLUMN ai_conversation.user_id IS 'CN: 用户 ID，来自 profile 服务，不建立跨库外键；EN: User id from profile service without a cross-database foreign key; JP: profile サービス由来のユーザー ID。クロス DB 外部キーは作成しません';
COMMENT ON COLUMN ai_conversation.title IS 'CN: 会话标题，默认从首个问题截取；EN: Conversation title, usually derived from the first question; JP: 会話タイトル。通常は最初の質問から生成します';
COMMENT ON COLUMN ai_conversation.created_at IS 'CN: 创建时间；EN: Creation timestamp; JP: 作成日時';
COMMENT ON COLUMN ai_conversation.updated_at IS 'CN: 更新时间；EN: Last update timestamp; JP: 更新日時';

CREATE INDEX IF NOT EXISTS idx_ai_conversation_user_time ON ai_conversation (user_id, updated_at DESC);

CREATE TABLE IF NOT EXISTS ai_message (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id UUID NOT NULL REFERENCES ai_conversation (id) ON DELETE CASCADE,
    user_id UUID NOT NULL,
    role VARCHAR(20) NOT NULL,
    content TEXT NOT NULL,
    model_name VARCHAR(100),
    status VARCHAR(20) NOT NULL DEFAULT 'COMPLETED',
    error_message TEXT,
    prompt_tokens INTEGER,
    completion_tokens INTEGER,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_ai_message_role CHECK (role IN ('system', 'user', 'assistant')),
    CONSTRAINT chk_ai_message_status CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED'))
);

COMMENT ON TABLE ai_message IS 'CN: AI 对话消息表，保存用户问题与模型回答；EN: AI message table storing user questions and model answers; JP: ユーザー質問とモデル回答を保存する AI メッセージテーブル';
COMMENT ON COLUMN ai_message.id IS 'CN: 消息主键 UUID；EN: Message primary key UUID; JP: メッセージ主キー UUID';
COMMENT ON COLUMN ai_message.conversation_id IS 'CN: 所属会话 ID；EN: Owning conversation id; JP: 所属会話 ID';
COMMENT ON COLUMN ai_message.user_id IS 'CN: 用户 ID，用于隔离会话读取；EN: User id used to isolate conversation reads; JP: 会話読み取りを分離するためのユーザー ID';
COMMENT ON COLUMN ai_message.role IS 'CN: 消息角色，user 或 assistant；EN: Message role, user or assistant; JP: メッセージロール。user または assistant';
COMMENT ON COLUMN ai_message.content IS 'CN: 消息正文；EN: Message content; JP: メッセージ本文';
COMMENT ON COLUMN ai_message.model_name IS 'CN: 生成该消息的模型名称；EN: Model name that generated this message; JP: このメッセージを生成したモデル名';
COMMENT ON COLUMN ai_message.status IS 'CN: 消息状态，用于记录失败的模型请求；EN: Message status used to track failed model requests; JP: 失敗したモデル要求を記録するためのメッセージ状態';
COMMENT ON COLUMN ai_message.error_message IS 'CN: 失败原因；EN: Failure reason; JP: 失敗理由';
COMMENT ON COLUMN ai_message.prompt_tokens IS 'CN: 输入 token 数；EN: Prompt token count; JP: 入力 token 数';
COMMENT ON COLUMN ai_message.completion_tokens IS 'CN: 输出 token 数；EN: Completion token count; JP: 出力 token 数';
COMMENT ON COLUMN ai_message.created_at IS 'CN: 创建时间；EN: Creation timestamp; JP: 作成日時';

CREATE INDEX IF NOT EXISTS idx_ai_message_conversation_time ON ai_message (conversation_id, created_at ASC);
CREATE INDEX IF NOT EXISTS idx_ai_message_user_time ON ai_message (user_id, created_at DESC);
