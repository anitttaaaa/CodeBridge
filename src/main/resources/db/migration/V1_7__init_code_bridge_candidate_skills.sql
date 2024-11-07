CREATE TABLE candidate_skills (
    candidate_id                    INT             NOT NULL,
    candidate_skills                VARCHAR(255)    NOT NULL,

    FOREIGN KEY (candidate_id)
        REFERENCES candidate(candidate_id)
            ON DELETE CASCADE
);