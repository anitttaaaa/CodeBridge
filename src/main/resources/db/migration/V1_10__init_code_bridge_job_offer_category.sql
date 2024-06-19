CREATE TABLE job_offer_category
(
    job_offer_id               INT,
    job_category_id            INT,

    CONSTRAINT fk_job_offer_category_job_offer
            FOREIGN KEY (job_offer_id)
                REFERENCES job_offer (job_offer_id),

    CONSTRAINT fk_job_offer_category_job_category
            FOREIGN KEY (job_category_id)
                REFERENCES job_category (job_category_id)

);