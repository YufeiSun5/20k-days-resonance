CREATE EXTENSION IF NOT EXISTS pgcrypto;

COMMENT ON EXTENSION pgcrypto IS 'CN: 提供 UUID 与加密辅助函数；EN: Provides UUID and cryptographic helper functions; JP: UUID 生成と暗号補助関数を提供します';

CREATE EXTENSION IF NOT EXISTS vector;

COMMENT ON EXTENSION vector IS 'CN: 提供向量相似度检索能力；EN: Provides vector similarity search capabilities; JP: ベクトル類似検索機能を提供します';
