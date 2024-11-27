CREATE TABLE IF NOT EXISTS days
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
