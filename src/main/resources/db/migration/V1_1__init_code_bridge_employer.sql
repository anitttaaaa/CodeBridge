CREATE TABLE employer
(
    employer_id             SERIAL      NOT NULL,
    company_name             VARCHAR(32) NOT NULL,
    email                   VARCHAR(32) NOT NULL,
    nip                     VARCHAR(32) NOT NULL,
    PRIMARY KEY (employer_id),
    UNIQUE (email)
);