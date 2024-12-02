DROP TABLE IF EXISTS employer CASCADE;
DROP TABLE IF EXISTS candidate CASCADE;
DROP TABLE IF EXISTS candidate_experience CASCADE;
DROP TABLE IF EXISTS candidate_project CASCADE;
DROP TABLE IF EXISTS candidate_education CASCADE;
DROP TABLE IF EXISTS candidate_course CASCADE;
DROP TABLE IF EXISTS salary CASCADE;
DROP TABLE IF EXISTS job_offer CASCADE;
DROP TABLE IF EXISTS job_location CASCADE;
DROP TABLE IF EXISTS job_offer_category CASCADE;
DROP TABLE IF EXISTS job_category CASCADE;
DROP TABLE IF EXISTS code_bridge_user CASCADE;
DROP TABLE IF EXISTS code_bridge_role CASCADE;
DROP TABLE IF EXISTS code_bridge_user_role CASCADE;
DROP TABLE IF EXISTS job_offer_must_have_skills CASCADE;
DROP TABLE IF EXISTS job_offer_nice_to_have_skills CASCADE;
DROP TABLE IF EXISTS candidate_skills CASCADE;
DROP TABLE IF EXISTS job_offer_skill CASCADE;
DROP TABLE IF EXISTS flyway_schema_history CASCADE;

SELECT * FROM job_offer;
SELECT * FROM candidate_experience;
SELECT * FROM job_offer_must_have_skills;
SELECT * FROM candidate_skills;
SELECT * FROM job_offer_nice_to_have_skills;

