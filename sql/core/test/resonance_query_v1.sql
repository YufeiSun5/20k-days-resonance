SELECT j.id,
       j.title,
       j.original_weekday,
       j.created_at
FROM journals j
WHERE j.user_id = :user_id
  AND j.original_weekday = :weekday
  AND j.is_deleted = FALSE
ORDER BY j.created_at DESC
LIMIT 10;
