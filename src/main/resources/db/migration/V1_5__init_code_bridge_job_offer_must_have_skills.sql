CREATE TABLE job_offer_must_have_skills (
    job_offer_must_have_skills_id   SERIAL          NOT NULL,
    job_offer_id                    INT             NOT NULL,
    must_have_skills                VARCHAR(255)    NOT NULL,
    PRIMARY KEY (job_offer_must_have_skills_id),
    FOREIGN KEY (job_offer_id)
        REFERENCES job_offer(job_offer_id)
            ON DELETE CASCADE
);