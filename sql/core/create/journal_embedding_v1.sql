CREATE TABLE IF NOT EXISTS journal_embedding (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    journal_id UUID NOT NULL UNIQUE REFERENCES journals (id) ON DELETE CASCADE,
    model_name VARCHAR(100) NOT NULL,
    embedding VECTOR(1536),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE journal_embedding IS 'CN: 日记向量表，用于 AI 语义检索；EN: Journal embedding table used for AI semantic retrieval; JP: AI セマンティック検索に利用する日記埋め込みテーブル';
COMMENT ON COLUMN journal_embedding.id IS 'CN: 向量记录主键 UUID；EN: Embedding record primary key UUID; JP: 埋め込みレコード主キー UUID';
COMMENT ON COLUMN journal_embedding.journal_id IS 'CN: 对应日记 ID；EN: Related journal id; JP: 対応する日記 ID';
COMMENT ON COLUMN journal_embedding.model_name IS 'CN: 生成向量所使用的模型名称；EN: Model name used to generate the embedding; JP: 埋め込み生成に使用したモデル名';
COMMENT ON COLUMN journal_embedding.embedding IS 'CN: 向量数据本体；EN: Vector payload; JP: ベクトル本体';
COMMENT ON COLUMN journal_embedding.created_at IS 'CN: 创建时间；EN: Creation timestamp; JP: 作成日時';
