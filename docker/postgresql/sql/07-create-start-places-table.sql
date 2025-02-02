CREATE TABLE IF NOT EXISTS start_places (
    id                  SERIAL PRIMARY KEY,
    place_id            TEXT   NOT NULL,
    departure_time      TIME   NOT NULL,
    destination_list_id INT    NOT NULL REFERENCES destination_lists(id)
);
