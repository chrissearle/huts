SELECT username,
       roles,
       name
FROM local_user
WHERE username = :username