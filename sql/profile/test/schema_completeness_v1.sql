SELECT current_database() AS current_database,
       current_user AS current_user;

WITH expected_extensions AS (
    SELECT * FROM (VALUES ('pgcrypto')) AS t(extension_name)
), actual_extensions AS (
    SELECT extname AS extension_name FROM pg_extension
)
SELECT e.extension_name AS missing_extension
FROM expected_extensions e
LEFT JOIN actual_extensions a ON a.extension_name = e.extension_name
WHERE a.extension_name IS NULL;

WITH expected_tables AS (
    SELECT * FROM (VALUES ('users'), ('auth_identity')) AS t(table_name)
), actual_tables AS (
    SELECT table_name
    FROM information_schema.tables
    WHERE table_schema = 'public'
)
SELECT e.table_name AS missing_table
FROM expected_tables e
LEFT JOIN actual_tables a ON a.table_name = e.table_name
WHERE a.table_name IS NULL;

WITH expected_indexes AS (
    SELECT *
    FROM (VALUES
        ('idx_users_status'),
        ('uk_auth_identity_provider_user'),
        ('uk_auth_identity_email'),
        ('idx_auth_identity_user_id')
    ) AS t(index_name)
), actual_indexes AS (
    SELECT indexname AS index_name
    FROM pg_indexes
    WHERE schemaname = 'public'
)
SELECT e.index_name AS missing_index
FROM expected_indexes e
LEFT JOIN actual_indexes a ON a.index_name = e.index_name
WHERE a.index_name IS NULL;
