CREATE TABLE IF NOT EXISTS route_infos (
    id                  SERIAL PRIMARY KEY,
    travel_mode         INT    NOT NULL,
    travel_time_minutes INT    NOT NULL,
    destination_id      INT    NOT NULL REFERENCES destinations(id)
);
