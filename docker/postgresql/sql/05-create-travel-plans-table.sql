CREATE TABLE IF NOT EXISTS travel_plans (
    id                SERIAL      PRIMARY KEY,
    uuid              UUID        NOT NULL UNIQUE DEFAULT GEN_RANDOM_UUID(),
    title             VARCHAR(64) NOT NULL DEFAULT 'タイトルを入力',
    editable          BOOLEAN     NOT NULL DEFAULT TRUE,
    publicly_viewable BOOLEAN     NOT NULL DEFAULT FALSE,
    user_id           INT         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
