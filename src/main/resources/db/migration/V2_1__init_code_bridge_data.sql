insert into employer (company_name, email, nip)
values
('CodeSolutions', 'info@codesolutions.com', '1234563218'),
('TechInnovators', 'info@techinnovators.net', '9876543210'),
('DevMasters', 'info@devmasters.pl', '2345678901'),
('NextGenCoders', 'info@nextgencoders.com', '8765432109');

insert into candidate (name, surname, email, phone, status, linked_in, git_hub, tech_specialization, about_me, hobby)
values
('Anna', 'Nowak', 'annanowak@gmail.pl', '600123456', 'Actively looking for a job', 'https://linkedin.com/in/annanowakk', 'https://github.com/annanowakk', 'BACKEND', 'Experienced backend developer passionate about building efficient and scalable systems. I have a deep understanding of database management, API development, and microservices. Currently, I am actively seeking opportunities where I can apply my knowledge in backend technologies like Java, Node.js, and Python.', 'In my free time, I enjoy hiking in the mountains, reading sci-fi novels, and experimenting with new coding languages on personal projects.'),
('Adam', 'Kowalski', 'adamkowalski@gmail.pl', '601234867', 'Currently employed', 'https://linkedin.com/in/adamkowalskii', 'https://github.com/adamkowalskii', 'FRONTEND', 'I am a creative frontend developer with a passion for creating user-friendly and visually appealing interfaces. With strong skills in HTML, CSS, JavaScript, and React, I focus on delivering seamless user experiences. Currently employed, but always open to discussing future opportunities.', 'Outside of coding, I love experimenting with graphic design, photography, and building my own web apps as side projects.'),
('Katarzyna', 'Matejko', 'katarzynamatejko@gmail.pl', '602345678', 'Not currently looking for a job', 'https://linkedin.com/in/katarzynamatejkoo', 'https://github.com/katarzynamatejkoo', 'FULLSTACK', 'A versatile full-stack developer with experience in both frontend and backend technologies. I enjoy tackling complex problems and building both user-facing applications and robust server-side solutions. I specialize in JavaScript, React, Node.js, and MongoDB, and I always look for ways to improve performance and scalability.', 'When I’m not coding, you’ll find me exploring new cooking recipes, practicing yoga, and working on DIY projects around the house.');

insert into candidate_skills (candidate_id, candidate_skills)
values
(1, 'Java, Python, Microservices, REST API'),
(2, 'HTML, CSS, JavaScript, React'),
(3, 'JavaScript, React, Microservices, HTML, CSS, Java, Python');

insert into candidate_course (candidate_id, institution, course_title, technologies, description, from_date, to_date)
values
(1, 'University of Technology', 'Advanced Java Development', 'Java, Microservices, Spring', 'An advanced course focused on building scalable backend systems using Java, Spring Boot, and microservices architecture.', '2022-09-01', '2023-05-30'),
(2, 'Tech University', 'Modern Frontend Frameworks', 'HTML, CSS, JavaScript, React', 'A course that covers the latest techniques in frontend development using React, JavaScript, and responsive web design.', '2021-10-01', '2022-06-30'),
(3, 'FullStack Academy', 'Full-Stack Web Development', 'JavaScript, React, Microservices, HTML, CSS, Java, Python', 'A comprehensive course on full-stack development, combining frontend, backend, and database technologies.', '2020-03-01', '2021-12-15');


insert into candidate_education (candidate_id, institution, degree, field_of_study, from_date, to_date)
values
(1, 'Warsaw University of Technology', 'Master’s in Computer Science', 'Software Engineering', '2016-10-01', '2021-06-30'),
(2, 'University of Wroclaw', 'Bachelor’s in Computer Science', 'Web Development', '2017-10-01', '2021-06-30'),
(3, 'AGH University of Science and Technology', 'Master’s in Computer Science', 'Full-Stack Web Development', '2015-10-01', '2020-06-30');



insert into candidate_experience (candidate_id, company_name, candidate_position, description, from_date, to_date)
values
(1, 'Tech Solutions', 'Backend Developer', 'Developed scalable microservices using Java and Spring Boot. Focused on building and maintaining RESTful APIs and optimizing database queries for performance.', '2021-07-01', '2023-01-31'),
(2, 'WebTech Studio', 'Frontend Developer', 'Designed and implemented responsive web pages and user interfaces using HTML, CSS, and React. Worked closely with backend developers to integrate APIs into the frontend.', '2020-09-01', '2022-12-31'),
(3, 'CodeLab Technologies', 'Full-Stack Developer', 'Developed full-stack web applications with JavaScript, React, Node.js, and MongoDB. Managed both frontend and backend development, focusing on performance and scalability.', '2018-05-01', '2022-07-31');


insert into candidate_project (candidate_id, project_title, technologies, description, from_date, to_date, project_link)
values
(1, 'E-commerce Backend System', 'Java, Spring Boot, Microservices, PostgreSQL', 'Developed a backend system for an e-commerce platform, integrating multiple microservices for payment processing, product management, and order fulfillment.', '2022-03-01', '2022-09-30', 'https://github.com/annanowakk/ecommerce-backend'),
(2, 'Personal Portfolio Website', 'HTML, CSS, JavaScript, React', 'Created a personal portfolio website to showcase frontend development skills, including interactive UI elements and a blog section.', '2021-05-01', '2021-08-31', 'https://github.com/adamkowalskii/portfolio'),
(3, 'Task Management Web App', 'JavaScript, React, Node.js, MongoDB', 'Built a task management web app allowing users to create, update, and track tasks in real-time with authentication and user roles.', '2020-01-01', '2020-12-31', 'https://github.com/katarzynamatejkoo/task-manager');
