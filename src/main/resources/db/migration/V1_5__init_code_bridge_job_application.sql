CREATE TABLE job_application
(
    application_id          SERIAL         NOT NULL,
    job_offer_id            INT         NOT NULL,
    employer_id             INT         NOT NULL,
    candidate_id            INT         NOT NULL,
    application_status                  VARCHAR     NOT NULL,
    PRIMARY KEY (application_id),
        CONSTRAINT fk_job_application_employer
            FOREIGN KEY (employer_id)
                REFERENCES employer (employer_id),
        CONSTRAINT fk_job_application_job_offer
            FOREIGN KEY (job_offer_id)
                REFERENCES job_offer (job_offer_id),
        CONSTRAINT fk_job_application_candidate
            FOREIGN KEY (candidate_id)
                REFERENCES candidate (candidate_id)
                );

