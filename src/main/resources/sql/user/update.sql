UPDATE
    local_user
SET username         = :username,
    name             = :name,
    updated_at       = now(),
    last_modified_by = :updatedBy
WHERE id = :id
