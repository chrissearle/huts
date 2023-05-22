UPDATE local_user
SET password         = :password,
    updated_at       = now(),
    last_modified_by = :updatedBy
WHERE username = :username