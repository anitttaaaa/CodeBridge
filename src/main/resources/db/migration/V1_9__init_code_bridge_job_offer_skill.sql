CREATE TABLE job_offer_skill
(
    job_offer_id               INT,
    skill_id                   INT,

    CONSTRAINT fk_job_offer_skill_job_offer
            FOREIGN KEY (job_offer_id)
                REFERENCES job_offer (job_offer_id),

    CONSTRAINT fk_job_offer_skill_skill
            FOREIGN KEY (skill_id)
                REFERENCES skill (skill_id)

);