INSERT INTO booking_request (name, dates, hut_id, created_at, updated_at, created_by, last_modified_by)
VALUES (:name,
        :dates,
        :hut_id,
        now(),
        now(),
        'system',
        'system')
