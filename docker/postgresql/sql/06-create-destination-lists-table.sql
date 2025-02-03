CREATE TABLE IF NOT EXISTS destination_lists (
    id                                   SERIAL     PRIMARY KEY,
    travel_day                                  INT        NOT NULL,
    availabel_transportation_list_binary VARCHAR(4) NOT NULL DEFAULT '1111',
    travel_plan_id                       INT        NOT NULL,
    FOREIGN KEY (travel_plan_id) REFERENCES travel_plans (id)
);
