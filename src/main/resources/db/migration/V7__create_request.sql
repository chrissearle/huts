CREATE TABLE booking_request (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    hut_id INT NOT NULL,
    dates daterange NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    last_modified_by VARCHAR(255)
);
