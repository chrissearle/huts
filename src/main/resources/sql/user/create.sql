INSERT INTO local_user (username, roles, name, created_at, updated_at, created_by, last_modified_by)
VALUES (:username,
        :roles,
        :name,
        now(),
        now(),
        :createdBy,
        :createdBy)
