SELECT current_database() AS current_database,
       current_user AS current_user;

WITH expected_extensions AS (
    SELECT * FROM (VALUES ('pgcrypto'), ('vector')) AS t(extension_name)
), actual_extensions AS (
    SELECT extname AS extension_name FROM pg_extension
)
SELECT e.extension_name AS missing_extension
FROM expected_extensions e
LEFT JOIN actual_extensions a ON a.extension_name = e.extension_name
WHERE a.extension_name IS NULL;

WITH expected_tables AS (
    SELECT *
    FROM (VALUES
        ('wifi_space'),
        ('journals'),
        ('journal_view_event'),
        ('recommendation_log'),
        ('journal_embedding')
    ) AS t(table_name)
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
        ('uk_wifi_space_hashed_bssid'),
        ('idx_journals_user_weekday'),
        ('idx_journals_created_at'),
        ('idx_journals_keywords'),
        ('idx_journal_view_event_user_time'),
        ('idx_journal_view_event_space_time'),
        ('idx_journal_view_event_journal_id'),
        ('idx_recommendation_log_user_time')
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
