SELECT lu.id     AS lu_id,
       lu.username,
       lu.name   AS lu_name,
       lu.roles,
WHERE lu.username = :username