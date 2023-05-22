CREATE TABLE local_user (
    id SERIAL PRIMARY KEY,0
    username VARCHAR(100) not null,
    password VARCHAR(100),
    roles VARCHAR(100) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    last_modified_by VARCHAR(255)
);

CREATE UNIQUE INDEX username_idx ON local_user USING btree (username);
