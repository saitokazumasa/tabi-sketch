CREATE TABLE IF NOT EXISTS destinations (
    id                    SERIAL  PRIMARY KEY,
    place_id              TEXT    NOT NULL,
    visit_order           INT     NOT NULL,
    specified_start_time  TIME,
    start_time            TIME    NOT NULL,
    duration_minutes      INT     NOT NULL,
    budget                INT     NOT NULL,
    destination_list_id   INT     NOT NULL,
    FOREIGN KEY (destination_list_id) REFERENCES destination_lists (id)
);
