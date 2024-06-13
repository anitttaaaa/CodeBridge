CREATE TABLE job_offer
(
    job_offer_id            SERIAL      NOT NULL,
    job_offer_code          VARCHAR(32) NOT NULL,
    title                   VARCHAR(32) NOT NULL,
    description             VARCHAR(64) NOT NULL,
    posted_date             TIMESTAMP WITH TIME ZONE,
    expiry_date             TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (job_offer_id),
    UNIQUE (job_offer_code),

    CONSTRAINT fk_job_offer_salary
            FOREIGN KEY (salary_id)
                REFERENCES salary (salary_id),

    CONSTRAINT fk_job_offer_location
            FOREIGN KEY (location_id)
                REFERENCES location (location_id),

        CONSTRAINT fk_job_offer_employer
            FOREIGN KEY (employer_id)
                REFERENCES employer (employer_id)
);