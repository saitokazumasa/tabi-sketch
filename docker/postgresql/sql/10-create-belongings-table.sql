CREATE TABLE IF NOT EXISTS belongings (
    id             SERIAL      PRIMARY KEY,
    label          VARCHAR(64) NOT NULL,
    travel_plan_id INT         NOT NULL,
    FOREIGN KEY (travel_plan_id) REFERENCES travel_plans (id)
);
