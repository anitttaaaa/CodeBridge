CREATE TABLE location
(
    location_id           SERIAL      NOT NULL,
    type                  VARCHAR(32)    NOT NULL,
    city                  VARCHAR(32)    NOT NULL,
    PRIMARY KEY (location_id)
);