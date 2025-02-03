CREATE TABLE IF NOT EXISTS password_reset_tokens (
    uuid       UUID      NOT NULL UNIQUE DEFAULT GEN_RANDOM_UUID(),
    user_id    INT       NOT NULL,
    created_at TIMESTAMP NOT NULL        DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
