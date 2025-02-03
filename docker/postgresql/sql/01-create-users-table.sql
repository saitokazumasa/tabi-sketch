CREATE TABLE IF NOT EXISTS users (
    id             SERIAL       PRIMARY KEY,
    email          VARCHAR(254) NOT NULL UNIQUE,
    password       VARCHAR(128) NOT NULL,
    email_verified BOOLEAN      NOT NULL DEFAULT FALSE
);

/* mock user */
INSERT INTO users (id, email, password, email_verified)
    VALUES (
        1,
        'example@example.com',
        '$2a$10$FFbAunp0hfeWTCune.XqwO/P/61fqWlbruV/8wqzrhM3Pw0VuXxpa',
        true
    );
