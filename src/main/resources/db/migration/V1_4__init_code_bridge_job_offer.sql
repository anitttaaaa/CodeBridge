CREATE TABLE job_offer
(
    job_offer_id            SERIAL      NOT NULL,
    title                   VARCHAR(32) NOT NULL,
    description             VARCHAR(128) NOT NULL,
    employer_id             INT         NOT NULL,
    tech_specialization     VARCHAR         NOT NULL,
    work_type               VARCHAR         NOT NULL,
    city                    VARCHAR         NOT NULL,
    experience              VARCHAR         NOT NULL,
    salary                  VARCHAR         NOT NULL,
    PRIMARY KEY (job_offer_id),
        CONSTRAINT fk_job_offer_employer
            FOREIGN KEY (employer_id)
                REFERENCES employer (employer_id)
                );



CREATE TABLE job_offer_must_have_skills (
    job_offer_id                    INT             NOT NULL,
    must_have_skills                VARCHAR(255)    NOT NULL,

    FOREIGN KEY (job_offer_id)
        REFERENCES job_offer(job_offer_id)
            ON DELETE CASCADE
);



CREATE TABLE job_offer_nice_to_have_skills (
    job_offer_id                        INT             NOT NULL,
    nice_to_have_skills                 VARCHAR(255)    NOT NULL,
    FOREIGN KEY (job_offer_id)
        REFERENCES job_offer(job_offer_id)
            ON DELETE CASCADE
);
