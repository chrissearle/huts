SELECT b.id         AS booking_id,
       b.name       AS booking_name,
       b.dates      AS booking_dates,
       h.id         AS hut_id,
       h.name       AS hut_name,
       h.short_name AS hut_short_name
FROM booking b,
     hut h
WHERE b.hut_id = h.id
ORDER BY h.id, b.dates

