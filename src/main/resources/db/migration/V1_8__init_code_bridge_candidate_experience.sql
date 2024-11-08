CREATE TABLE candidate_experience
(
    candidate_experience_id             SERIAL              NOT NULL,
    company_name                         VARCHAR(32)         NOT NULL,
    candidate_position                  VARCHAR             NOT NULL,
    description                         VARCHAR(128)        NOT NULL,
    from_date                           DATE                NOT NULL,
    to_date                             DATE,
    candidate_id                        INT                 NOT NULL,

    PRIMARY KEY (candidate_experience_id),

        CONSTRAINT fk_candidate_experience_candidate
            FOREIGN KEY (candidate_id)
                REFERENCES candidate (candidate_id)
                );