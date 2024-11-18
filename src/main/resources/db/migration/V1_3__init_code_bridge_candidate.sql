  CREATE TABLE candidate
(
    candidate_id            SERIAL      NOT NULL,
    name                    VARCHAR(32) NOT NULL,
    surname                 VARCHAR(32) NOT NULL,
    email                   VARCHAR(32) NOT NULL,
    phone                   VARCHAR(32) NOT NULL,
    linked_in               VARCHAR(32),
    git_hub                 VARCHAR(32),
    tech_specialization     VARCHAR(32),
    about_me                VARCHAR(512),
    hobby                   VARCHAR(512),
    profile_photo           BYTEA,
    PRIMARY KEY (candidate_id),
    UNIQUE (email)
);

CREATE TABLE candidate_skills (
    candidate_id                    INT             NOT NULL,
    candidate_skills                VARCHAR(255)    NOT NULL,

    FOREIGN KEY (candidate_id)
        REFERENCES candidate(candidate_id)
            ON DELETE CASCADE
);


CREATE TABLE candidate_experience
(
    candidate_experience_id             SERIAL              NOT NULL,
    company_name                        VARCHAR(32)         NOT NULL,
    candidate_position                  VARCHAR             NOT NULL,
    description                         VARCHAR(128)        NOT NULL,
    from_date                           DATE                NOT NULL,
    to_date                             DATE,
    candidate_id                        INT                 NOT NULL,

    PRIMARY KEY (candidate_experience_id),
        CONSTRAINT fk_candidate_experience_candidate
            FOREIGN KEY (candidate_id)
                REFERENCES candidate (candidate_id)
                )