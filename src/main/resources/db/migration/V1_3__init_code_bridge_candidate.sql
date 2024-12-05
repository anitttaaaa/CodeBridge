  CREATE TABLE candidate
(
    candidate_id            SERIAL      NOT NULL,
    name                    VARCHAR(32) NOT NULL,
    surname                 VARCHAR(32) NOT NULL,
    email                   VARCHAR(32) NOT NULL,
    phone                   VARCHAR(32) NOT NULL,
    status                  VARCHAR(32),
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
    candidate_position                  VARCHAR             NOT NULL,
    company_name                        VARCHAR(32)         NOT NULL,
    description                         VARCHAR(128)        NOT NULL,
    from_date                           DATE                NOT NULL,
    to_date                             DATE,
    candidate_id                        INT                 NOT NULL,

    PRIMARY KEY (candidate_experience_id),
        CONSTRAINT fk_candidate_experience_candidate
            FOREIGN KEY (candidate_id)
                REFERENCES candidate (candidate_id)
                );

CREATE TABLE candidate_project
(
    candidate_project_id               SERIAL                  NOT NULL,
    project_title                      VARCHAR                 NOT NULL,
    technologies                       VARCHAR                 NOT NULL,
    description                        VARCHAR                 NOT NULL,
    from_date                          DATE,
    to_date                            DATE,
    project_link                       VARCHAR                 NOT NULL,
    candidate_id                       INT                     NOT NULL,

    PRIMARY KEY (candidate_project_id),
        CONSTRAINT fk_candidate_project_candidate
            FOREIGN KEY (candidate_id)
                REFERENCES candidate (candidate_id)


);


CREATE TABLE candidate_education
(
    candidate_education_id            SERIAL                  NOT NULL,
    institution                       VARCHAR                 NOT NULL,
    degree                            VARCHAR                 NOT NULL,
    field_of_study                    VARCHAR                 NOT NULL,
    from_date                         DATE,
    to_date                           DATE,
    candidate_id                      INT                     NOT NULL,

    PRIMARY KEY (candidate_education_id),
        CONSTRAINT fk_candidate_education_candidate
            FOREIGN KEY (candidate_id)
                REFERENCES candidate (candidate_id)


);

CREATE TABLE candidate_course
(
    candidate_course_id               SERIAL                  NOT NULL,
    institution                       VARCHAR                 NOT NULL,
    course_title                      VARCHAR                 NOT NULL,
    technologies                      VARCHAR                 NOT NULL,
    description                       VARCHAR                 NOT NULL,
    from_date                         DATE,
    to_date                           DATE,
    candidate_id                      INT                     NOT NULL,

    PRIMARY KEY (candidate_course_id),
        CONSTRAINT fk_candidate_course_candidate
            FOREIGN KEY (candidate_id)
                REFERENCES candidate (candidate_id)


);


