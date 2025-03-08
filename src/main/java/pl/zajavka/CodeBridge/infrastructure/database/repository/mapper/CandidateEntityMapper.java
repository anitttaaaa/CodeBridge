package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.*;
import pl.zajavka.CodeBridge.infrastructure.database.entity.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEntityMapper {

    // Mapowanie Candidate -> CandidateEntity
    default CandidateEntity mapCandidateToEntity(Candidate candidate) {
        if (candidate == null) {
            return null;
        }

        // Tworzenie obiektu CandidateEntity za pomocą konstruktora
        return new CandidateEntity(
                candidate.getCandidateId(),
                candidate.getName(),
                candidate.getSurname(),
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getStatus(),
                candidate.getUserId(),
                candidate.getLinkedIn(),
                candidate.getGitHub(),
                candidate.getTechSpecialization(),
                candidate.getAboutMe(),
                candidate.getHobby(),
                candidate.getCandidateSkills(),
                candidate.getProfilePhoto(),
                mapCandidateExperiencesToEntities(candidate.getCandidateExperiences()),
                mapCandidateProjectsToEntities(candidate.getCandidateProjects()),
                mapCandidateEducationToEntities(candidate.getCandidateEducationStages()),
                mapCandidateCoursesToEntities(candidate.getCandidateCourses()),
                null // Pusta kolekcja dla 'jobApplications', jeśli jest niepotrzebna w tym mapowaniu
        );
    }

    // Mapowanie CandidateExperience -> CandidateExperienceEntity
    default List<CandidateExperienceEntity> mapCandidateExperiencesToEntities(List<CandidateExperience> experiences) {
        if (experiences == null) {
            return null;
        }
        List<CandidateExperienceEntity> entities = new ArrayList<>();
        for (CandidateExperience experience : experiences) {
            entities.add(new CandidateExperienceEntity(
                    experience.getCandidateExperienceId(),
                    experience.getCompanyName(),
                    experience.getCandidatePosition(),
                    experience.getDescription(),
                    experience.getFromDate(),
                    experience.getToDate(),
                    experience.getCandidateId()
            ));
        }
        return entities;
    }

    // Mapowanie CandidateProject -> CandidateProjectEntity
    default List<CandidateProjectEntity> mapCandidateProjectsToEntities(List<CandidateProject> projects) {
        if (projects == null) {
            return null;
        }
        List<CandidateProjectEntity> entities = new ArrayList<>();
        for (CandidateProject project : projects) {
            entities.add(new CandidateProjectEntity(
                    project.getCandidateProjectId(),
                    project.getProjectTitle(),
                    project.getTechnologies(),
                    project.getDescription(),
                    project.getFromDate(),
                    project.getToDate(),
                    project.getProjectLink(),
                    project.getCandidateId()
            ));
        }
        return entities;
    }

    // Mapowanie CandidateEducation -> CandidateEducationEntity
    default List<CandidateEducationEntity> mapCandidateEducationToEntities(List<CandidateEducation> education) {
        if (education == null) {
            return null;
        }
        List<CandidateEducationEntity> entities = new ArrayList<>();
        for (CandidateEducation edu : education) {
            entities.add(new CandidateEducationEntity(
                    edu.getCandidateEducationId(),
                    edu.getInstitution(),
                    edu.getDegree(),
                    edu.getFieldOfStudy(),
                    edu.getFromDate(),
                    edu.getToDate(),
                    edu.getCandidateId()
            ));
        }
        return entities;
    }

    // Mapowanie CandidateCourse -> CandidateCourseEntity
    default List<CandidateCourseEntity> mapCandidateCoursesToEntities(List<CandidateCourse> courses) {
        if (courses == null) {
            return null;
        }
        List<CandidateCourseEntity> entities = new ArrayList<>();
        for (CandidateCourse course : courses) {
            entities.add(new CandidateCourseEntity(
                    course.getCandidateCourseId(),
                    course.getInstitution(),
                    course.getCourseTitle(),
                    course.getDescription(),
                    course.getTechnologies(),
                    course.getFromDate(),
                    course.getToDate(),
                    course.getCandidateId()
            ));
        }
        return entities;
    }

    // Mapowanie CandidateEntity -> Candidate
    default Candidate mapCandidateEntityToDomain(CandidateEntity candidateEntity) {
        if (candidateEntity == null) {
            return null;
        }

        return new Candidate.Builder()
                .candidateId(candidateEntity.getCandidateId())
                .name(candidateEntity.getName())
                .surname(candidateEntity.getSurname())
                .email(candidateEntity.getEmail())
                .phone(candidateEntity.getPhone())
                .status(candidateEntity.getStatus())
                .userId(candidateEntity.getUserId())
                .linkedIn(candidateEntity.getLinkedIn())
                .gitHub(candidateEntity.getGitHub())
                .techSpecialization(candidateEntity.getTechSpecialization())
                .aboutMe(candidateEntity.getAboutMe())
                .hobby(candidateEntity.getHobby())
                .candidateSkills(candidateEntity.getCandidateSkills())
                .candidateExperiences(mapCandidateExperienceEntitiesToDomain(candidateEntity.getCandidateExperiences()))
                .candidateProjects(mapCandidateProjectEntitiesToDomain(candidateEntity.getCandidateProjects()))
                .candidateEducationStages(mapCandidateEducationEntitiesToDomain(candidateEntity.getCandidateEducationStages()))
                .candidateCourses(mapCandidateCourseEntitiesToDomain(candidateEntity.getCandidateCourses()))
                .profilePhoto(candidateEntity.getProfilePhoto())
                .build();
    }


    default List<CandidateExperience> mapCandidateExperienceEntitiesToDomain(List<CandidateExperienceEntity> experiences) {
        if (experiences == null) {
            return null;
        }

        List<CandidateExperience> domainExperiences = new ArrayList<>();
        for (CandidateExperienceEntity experienceEntity : experiences) {
            domainExperiences.add(new CandidateExperience.Builder()
                    .candidateExperienceId(experienceEntity.getCandidateExperienceId())
                    .companyName(experienceEntity.getCompanyName())
                    .candidatePosition(experienceEntity.getCandidatePosition())
                    .description(experienceEntity.getDescription())
                    .fromDate(experienceEntity.getFromDate())
                    .toDate(experienceEntity.getToDate())
                    .candidateId(experienceEntity.getCandidateId())
                    .build());
        }
        return domainExperiences;
    }


    default List<CandidateProject> mapCandidateProjectEntitiesToDomain(List<CandidateProjectEntity> projects) {
        if (projects == null) {
            return null;
        }

        List<CandidateProject> domainProjects = new ArrayList<>();
        for (CandidateProjectEntity projectEntity : projects) {
            domainProjects.add(new CandidateProject.Builder()
                    .candidateProjectId(projectEntity.getCandidateProjectId())
                    .projectTitle(projectEntity.getProjectTitle())
                    .technologies(projectEntity.getTechnologies())
                    .description(projectEntity.getDescription())
                    .fromDate(projectEntity.getFromDate())
                    .toDate(projectEntity.getToDate())
                    .projectLink(projectEntity.getProjectLink())
                    .candidateId(projectEntity.getCandidateId())
                    .build());
        }
        return domainProjects;
    }


    default List<CandidateEducation> mapCandidateEducationEntitiesToDomain(List<CandidateEducationEntity> education) {
        if (education == null) {
            return null;
        }

        List<CandidateEducation> domainEducation = new ArrayList<>();
        for (CandidateEducationEntity eduEntity : education) {
            domainEducation.add(new CandidateEducation.Builder()
                    .candidateEducationId(eduEntity.getCandidateEducationId())
                    .institution(eduEntity.getInstitution())
                    .degree(eduEntity.getDegree())
                    .fieldOfStudy(eduEntity.getFieldOfStudy())
                    .fromDate(eduEntity.getFromDate())
                    .toDate(eduEntity.getToDate())
                    .candidateId(eduEntity.getCandidateId())
                    .build());
        }
        return domainEducation;
    }

    default List<CandidateCourse> mapCandidateCourseEntitiesToDomain(List<CandidateCourseEntity> courses) {
        if (courses == null) {
            return null;
        }

        List<CandidateCourse> domainCourses = new ArrayList<>();
        for (CandidateCourseEntity courseEntity : courses) {
            domainCourses.add(new CandidateCourse.Builder()
                    .candidateCourseId(courseEntity.getCandidateCourseId())
                    .institution(courseEntity.getInstitution())
                    .courseTitle(courseEntity.getCourseTitle())
                    .description(courseEntity.getDescription())
                    .technologies(courseEntity.getTechnologies())
                    .fromDate(courseEntity.getFromDate())
                    .toDate(courseEntity.getToDate())
                    .candidateId(courseEntity.getCandidateId())
                    .build());
        }
        return domainCourses;
    }

}


