  CREATE TABLE candidate
(
    candidate_id    SERIAL      NOT NULL,
    name            VARCHAR(32) NOT NULL,
    surname         VARCHAR(32) NOT NULL,
    email           VARCHAR(32) NOT NULL,
    phone           VARCHAR(32) NOT NULL,
    PRIMARY KEY (candidate_id),
    UNIQUE (email)
);