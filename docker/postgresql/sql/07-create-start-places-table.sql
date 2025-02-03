CREATE TABLE IF NOT EXISTS start_places (
    id                  SERIAL    PRIMARY KEY,
    place_id            TEXT      NOT NULL,
    departure_datetime  TIMESTAMP NOT NULL,
    destination_list_id INT       NOT NULL,
    FOREIGN KEY (destination_list_id) REFERENCES destination_lists (id)
);
