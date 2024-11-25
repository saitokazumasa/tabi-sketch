# SQLドキュメント

## DB設定コマンド

```postgresql
# DATABASE "tabisketch" を作成
CREATE DATABASE tabisketch;

# DATABASE "tabisketch" を削除
DROP DATABASE 'tabisketch';

# DATABASE を "tabisketch" に変更
\c tabisketch;
```

## 各TABLE作成コマンド

```postgresql
CREATE TABLE users
(
    id               SERIAL PRIMARY KEY,
    mail             VARCHAR(254) UNIQUE NOT NULL,
    password         VARCHAR(128)        NOT NULL,
    is_mail_verified BOOLEAN             NOT NULL DEFAULT FALSE
);

CREATE TABLE password_reset_tokens
(
    id         SERIAL PRIMARY KEY,
    token      TEXT      NOT NULL,
    user_id    INT       NOT NULL REFERENCES users (id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mail_authentication_tokens
(
    id         SERIAL PRIMARY KEY,
    token      TEXT      NOT NULL,
    user_id    INT       NOT NULL REFERENCES users (id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE plans
(
    id          SERIAL PRIMARY KEY,
    uuid        UUID UNIQUE NOT NULL DEFAULT GEN_RANDOM_UUID(),
    title       VARCHAR(64) NOT NULL DEFAULT 'タイトルを入力',
    user_id     INT         NOT NULL REFERENCES users (id),
    is_editable BOOLEAN     NOT NULL DEFAULT TRUE,
    is_public   BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE days
(
    id                         SERIAL PRIMARY KEY,
    day                        INT     NOT NULL,
    plan_id                    INT     NOT NULL,
    walk_threshold             INT,
    prefer_transportation_list INT     NOT NULL,
    is_use_toll_road           BOOLEAN NOT NULL,
    is_use_ferry               BOOLEAN NOT NULL,
    FOREIGN KEY (plan_id) REFERENCES plans (id)
);

CREATE TABLE google_places
(
    id        SERIAL PRIMARY KEY,
    place_id  TEXT UNIQUE      NOT NULL,
    name      VARCHAR(64)      NOT NULL,
    latitude  DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
);

CREATE TABLE google_place_types
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE google_type_associations
(
    place_type_id INT NOT NULL,
    place_id      INT NOT NULL,
    PRIMARY KEY (place_type_id, place_id),
    FOREIGN KEY (place_type_id) REFERENCES google_place_types (id),
    FOREIGN KEY (place_id) REFERENCES google_places (id)
);

# TODO: Google の交通手段を入れる
CREATE TYPE transpotations AS ENUM ('', '', '');

CREATE TABLE places
(
    id                 SERIAL PRIMARY KEY,
    google_place_id    INT  NOT NULL,
    day_id             INT  NOT NULL,
    start_time         TIME NOT NULL,
    end_time           TIME NOT NULL,
    budget             INT,
    desired_start_time TIME,
    desired_end_time   TIME,
    to_poly_line       TEXT,
    to_travel_time     INT,
    to_transportation  transpotations,
    FOREIGN KEY (google_place_id) REFERENCES google_places (id),
    FOREIGN KEY (day_id) REFERENCES days (id)
);

CREATE TABLE packings
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(64) NOT NULL,
    plan_id INT         NOT NULL,
    FOREIGN KEY (plan_id) REFERENCES plans (id)
);
```

## 各TABLE削除コマンド

```postgresql
DROP TABLE users;
DROP TABLE password_reset_tokens;
DROP TABLE mail_authentication_tokens;
DROP TABLE plans;
DROP TABLE days;
DROP TABLE google_places;
DROP TABLE google_place_types;
DROP TABLE google_type_associations;
DROP TYPE transpotations;
DROP TABLE places;
DROP TABLE packings;
```

## 各TABLEサンプルデータ

```postgresql
INSERT INTO users (mail, password)
VALUES ('sample@example.com', 'password');
```