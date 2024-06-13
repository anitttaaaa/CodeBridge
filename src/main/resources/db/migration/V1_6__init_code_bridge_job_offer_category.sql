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

    CONSTRAINT fk_job_offer_category_job_offer
            FOREIGN KEY (job_offer_id)
                REFERENCES job_offer (job_offer_id),

    CONSTRAINT fk_job_offer_category_job_category
            FOREIGN KEY (job_category_id)
                REFERENCES job_category (job_category_id),

);