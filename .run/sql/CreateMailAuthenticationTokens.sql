CREATE TABLE IF NOT EXISTS mail_authentication_tokens
(
    id         SERIAL PRIMARY KEY,
    token      TEXT      NOT NULL,
    user_id    INT       NOT NULL REFERENCES users (id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
