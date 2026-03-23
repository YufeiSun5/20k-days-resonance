SELECT e.user_id,
       e.space_id,
       j.original_weekday,
       COUNT(*) AS view_count,
       COALESCE(SUM(e.duration_ms), 0) AS total_duration_ms
FROM journal_view_event e
JOIN journals j ON j.id = e.journal_id
WHERE e.event_at >= NOW() - INTERVAL '30 days'
GROUP BY e.user_id, e.space_id, j.original_weekday
ORDER BY view_count DESC, total_duration_ms DESC;