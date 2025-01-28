INSERT INTO job_offer (job_offer_title, description, employer_id, tech_specialization, work_type, city, experience, salary)
VALUES
    ('Java Developer', 'Develop and maintain Java applications.', 1, 'BACKEND', 'REMOTE', 'WARSZAWA', 'MID', 'RANGE_5000_8000'),
    ('Frontend Developer', 'Build and optimize frontend applications.', 2, 'FRONTEND', 'ONSITE', 'KRAKÓW', 'JUNIOR', 'RANGE_3000_5000'),
    ('Backend Developer', 'Design and develop backend services in Java and Kotlin.', 1, 'BACKEND', 'REMOTE', 'WARSZAWA', 'MID', 'RANGE_5000_8000'),
    ('Fullstack Developer', 'Build and maintain both frontend and backend parts of the application.', 2, 'FULLSTACK', 'HYBRID', 'KRAKÓW', 'SENIOR', 'RANGE_8000_12000'),
    ('Python Developer', 'Write and optimize Python code for data processing and machine learning.', 3, 'BACKEND', 'ONSITE', 'WROCŁAW', 'JUNIOR', 'RANGE_3000_5000'),
    ('Frontend Developer', 'Create engaging user interfaces using React and TypeScript.', 4, 'FRONTEND', 'REMOTE', 'POZNAŃ', 'MID', 'RANGE_5000_8000'),
    ('DevOps Engineer', 'Configure and maintain cloud infrastructure with CI/CD pipelines.', 1, 'BACKEND', 'REMOTE', 'WARSZAWA', 'SENIOR', 'RANGE_8000_12000'),
    ('QA Engineer', 'Test and ensure quality for Java-based applications and services.', 1, 'BACKEND', 'HYBRID', 'WARSZAWA', 'MID', 'RANGE_5000_8000'),
    ('Cloud Engineer', 'Work with cloud technologies to build scalable systems.', 2, 'BACKEND', 'ONSITE', 'KRAKÓW', 'MID', 'RANGE_5000_8000'),
    ('Business Analyst', 'Analyze business requirements and communicate with development teams.', 2, 'FRONTEND', 'REMOTE', 'KRAKÓW', 'JUNIOR', 'RANGE_3000_5000'),
    ('iOS Developer', 'Develop and maintain iOS applications.', 3, 'BACKEND', 'REMOTE', 'WROCŁAW', 'SENIOR', 'RANGE_8000_12000'),
    ('Android Developer', 'Build Android applications with Kotlin and Java.', 3, 'BACKEND', 'HYBRID', 'WROCŁAW', 'MID', 'RANGE_5000_8000'),
    ('Game Developer', 'Develop mobile and desktop games using Unity.', 4, 'FULLSTACK', 'ONSITE', 'POZNAŃ', 'MID', 'RANGE_5000_8000'),
    ('UI/UX Designer', 'Design user interfaces and experiences for web applications.', 4, 'FRONTEND', 'HYBRID', 'POZNAŃ', 'JUNIOR', 'RANGE_3000_5000');


INSERT INTO job_offer_must_have_skills (job_offer_id, must_have_skills)
VALUES
    (1, 'Java, Spring Boot, SQL'),
    (2, 'HTML, CSS, React'),
    (3, 'Python, SQL'),
    (4, 'React, TypeScript, CSS'),
    (5, 'JavaScript, React'),
    (6, 'Java, Kotlin, Spring Boot'),
    (7, 'Docker'),
    (8, 'Java, JUnit, SQL'),
    (9, 'Python, Linux'),
    (10, 'SQL'),
    (11, 'Java, Kotlin, Microservices'),
    (12, 'Kotlin, Java'),
    (13, 'Microservices, Docker, GIT, REST API'),
    (14, 'HTML, CSS, React');

INSERT INTO job_offer_nice_to_have_skills (job_offer_id, nice_to_have_skills)
VALUES
    (1, 'Docker'),
    (2, 'Java Script'),
    (3, 'Docker'),
    (4, 'Git'),
    (5, 'TypeScript'),
    (6, 'Microservices, Kafka'),
    (7, 'GIT'),
    (8, 'Git'),
    (9, 'Docker'),
    (10, 'Docker'),
    (11, 'GIT, REST API'),
    (12, 'Git, REST API'),
    (13, 'Kafka, Java'),
    (14, 'Visual Studio Code, Angular, TypeScript');


