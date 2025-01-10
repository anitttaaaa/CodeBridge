CREATE TABLE job_application
(
    application_id     SERIAL         NOT NULL,
    job_offer_id            INT         NOT NULL,
    employer_id             INT         NOT NULL,
    candidate_id            INT         NOT NULL,
    application_status                  VARCHAR     NOT NULL,
    PRIMARY KEY (job_offer_id),
        CONSTRAINT fk_job_offer_employer
            FOREIGN KEY (employer_id)
                REFERENCES employer (employer_id),
        CONSTRAINT fk_job_offer_candidate
            FOREIGN KEY (candidate_id)
                REFERENCES candidate (candidate_id)
                );

