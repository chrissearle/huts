CREATE TABLE local_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100),
    name VARCHAR(100),
    roles VARCHAR(100) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    last_modified_by VARCHAR(255)
);

CREATE UNIQUE INDEX username_idx ON local_user USING btree (username);
