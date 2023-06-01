SELECT lu.id     AS lu_id,
       lu.username,
       lu.name   AS lu_name,
       lu.roles
FROM local_user lu
WHERE lu.username = :username