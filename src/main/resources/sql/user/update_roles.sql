UPDATE
    local_user
SET roles            = :roles,
    updated_at       = now(),
    last_modified_by = :updatedBy
WHERE username = :username