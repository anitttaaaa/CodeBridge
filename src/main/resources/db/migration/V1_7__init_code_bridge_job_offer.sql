CREATE TABLE job_offer
(
    job_offer_id            SERIAL      NOT NULL,
    title                   VARCHAR(32) NOT NULL,
    description             VARCHAR(128) NOT NULL,
    employer_id             INT         NOT NULL,
    PRIMARY KEY (job_offer_id),
        CONSTRAINT fk_job_offer_employer
            FOREIGN KEY (employer_id)
                REFERENCES employer (employer_id)
);
--    job_offer_code          VARCHAR(32) NOT NULL,
--    posted_date             TIMESTAMP WITH TIME ZONE,
--    expiry_date             TIMESTAMP WITH TIME ZONE,
--    salary_id               INT,
--    location_id             INT,
--    UNIQUE (job_offer_code)

--    CONSTRAINT fk_job_offer_salary
--            FOREIGN KEY (salary_id)
--                REFERENCES salary (salary_id),
--
--    CONSTRAINT fk_job_offer_job_location
--            FOREIGN KEY (location_id)
--                REFERENCES job_location (location_id),
--
