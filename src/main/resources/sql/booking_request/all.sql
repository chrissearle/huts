SELECT br.id        AS booking_request_id,
       br.name      AS booking_request_name,
       br.dates     AS booking_request_dates,
       h.id         AS hut_id,
       h.name       AS hut_name,
       h.short_name AS hut_short_name
FROM booking_request br,
     hut h
WHERE br.hut_id = h.id
ORDER BY h.id, br.dates

