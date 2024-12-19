CREATE TABLE IF NOT EXISTS plans
(
    id          SERIAL PRIMARY KEY,
    uuid        UUID UNIQUE NOT NULL DEFAULT GEN_RANDOM_UUID(),
    title       VARCHAR(64) NOT NULL DEFAULT 'タイトルを入力',
    user_id     INT         NOT NULL REFERENCES users (id),
    is_editable BOOLEAN     NOT NULL DEFAULT TRUE,
    is_public   BOOLEAN     NOT NULL DEFAULT FALSE
);
