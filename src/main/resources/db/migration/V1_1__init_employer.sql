CREATE TABLE employer
(
    employer_id             SERIAL      NOT NULL,
    companyName             VARCHAR(32) NOT NULL,
    nip                     VARCHAR(32) NOT NULL,
    PRIMARY KEY (employer_id),
    UNIQUE (nip)
);