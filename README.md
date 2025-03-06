# Table of Contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Platform Features](#platform-features)

# General Info
**CodeBridge**  is a full-stack platform that connects developers with employers, streamlining the recruitment process and job offer management. The application includes both a backend and a frontend, allowing employers to filter candidates and enabling developers to apply for job positions.

This project was created to put theoretical knowledge into practice. It serves as a way to consolidate concepts learned in an online course by implementing a fully functional web application, covering both server-side and client-side development.

The platform provides tools to:
- **Employers:** Post job offers, update profiles, filter candidates, view applications, view job offers history, accept or reject candidates.


- **Developers:** Apply for jobs, update profiles, filter job offers, generate CV.


# Technologies

**Backend:** 
- Java - version 17.0.10 LTS
- SpringBoot - version 3.0.7
- Spring Data JPA - version 3.0.7
- Spring Security - version 6.0.3
- Hibernate - version 6.1.7.Final
- Hibernate Validator - version 8.0.0.Final
- Flying Saucer PDF - version 9.11.2

**Build Tool:** 
- Gradle version 8.8

**Database:** 
- PostgreSQL

**Database Migration:** 
- Flyway - version 9.5.1

**Frontend:** 
- HTML, 
- CSS, 
- Thymeleaf - version 3.1.1.RELEASE
- Thymeleaf Extras Spring Security 6 - version 3.1.2.RELEASE
- Thymeleaf Layout Dialect - version 3.3.0

**Containerization:** 
- Docker - version 26.1.1 
- Docker compose

# Setup
The application is containerized with Docker.
To run the application with Docker, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/anitttaaaa/CodeBridge.git

2. **Navigate to the project directory:** After cloning the repository, go to the project folder:
   ```bash
    cd CodeBridge

3. **Build and start the application using Docker Compose:** Make sure you have Docker and Docker Compose installed.
    ```bash
   docker-compose up --build
   
4. **Access the application:** Once the containers are up and running, you can access the application by going to http://localhost:8080/CodeBridge/ in your web browser.


5. **Stop the containers:** When you're done, you can stop the containers with:
   ```bash
    docker-compose down
   
# Platform Features

The platform provides tools to:

**Employers:** Post job offers, update profiles, filter candidates, view applications, view job offer history and accept or reject candidate applications.

**Candidates:** Apply for jobs, update profiles, filter job offers and generate CVs.