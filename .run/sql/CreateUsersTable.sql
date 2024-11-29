CREATE TABLE IF NOT EXISTS users
(
    id               SERIAL PRIMARY KEY,
    mail             VARCHAR(254) UNIQUE NOT NULL,
    password         VARCHAR(128)        NOT NULL,
    is_mail_verified BOOLEAN             NOT NULL DEFAULT FALSE
);
