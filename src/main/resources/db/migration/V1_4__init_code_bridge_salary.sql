CREATE TABLE salary
(
    salary_id             SERIAL      NOT NULL,
    min_value             NUMERIC(19, 2) NOT NULL,
    max_value             NUMERIC(19, 2) NOT NULL,
    PRIMARY KEY (salary_id),
);