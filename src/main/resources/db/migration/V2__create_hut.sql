CREATE TABLE hut (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    last_modified_by VARCHAR(255)
);

CREATE UNIQUE INDEX name_idx ON hut USING btree (name);
