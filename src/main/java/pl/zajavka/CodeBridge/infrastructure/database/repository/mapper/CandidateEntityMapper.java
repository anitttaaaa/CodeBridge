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

        // Tworzenie obiektu Candidate za pomocą konstruktora
        return new Candidate(
                candidateEntity.getCandidateId(),
                candidateEntity.getName(),
                candidateEntity.getSurname(),
                candidateEntity.getEmail(),
                candidateEntity.getPhone(),
                candidateEntity.getStatus(),
                candidateEntity.getUserId(),
                candidateEntity.getLinkedIn(),
                candidateEntity.getGitHub(),
                candidateEntity.getTechSpecialization(),
                candidateEntity.getAboutMe(),
                candidateEntity.getHobby(),
                candidateEntity.getCandidateSkills(),
                mapCandidateExperienceEntitiesToDomain(candidateEntity.getCandidateExperiences()),
                mapCandidateProjectEntitiesToDomain(candidateEntity.getCandidateProjects()),
                mapCandidateEducationEntitiesToDomain(candidateEntity.getCandidateEducationStages()),
                mapCandidateCourseEntitiesToDomain(candidateEntity.getCandidateCourses()),
                candidateEntity.getProfilePhoto()
        );
    }

    // Mapowanie CandidateExperienceEntity -> CandidateExperience
    default List<CandidateExperience> mapCandidateExperienceEntitiesToDomain(List<CandidateExperienceEntity> experiences) {
        if (experiences == null) {
            return null;
        }
        List<CandidateExperience> domainExperiences = new ArrayList<>();
        for (CandidateExperienceEntity experienceEntity : experiences) {
            domainExperiences.add(new CandidateExperience(
                    experienceEntity.getCandidateExperienceId(),
                    experienceEntity.getCompanyName(),
                    experienceEntity.getCandidatePosition(),
                    experienceEntity.getDescription(),
                    experienceEntity.getFromDate(),
                    experienceEntity.getToDate(),
                    experienceEntity.getCandidateId()
            ));
        }
        return domainExperiences;
    }

    // Mapowanie CandidateProjectEntity -> CandidateProject
    default List<CandidateProject> mapCandidateProjectEntitiesToDomain(List<CandidateProjectEntity> projects) {
        if (projects == null) {
            return null;
        }
        List<CandidateProject> domainProjects = new ArrayList<>();
        for (CandidateProjectEntity projectEntity : projects) {
            domainProjects.add(new CandidateProject(
                    projectEntity.getCandidateProjectId(),
                    projectEntity.getProjectTitle(),
                    projectEntity.getTechnologies(),
                    projectEntity.getDescription(),
                    projectEntity.getFromDate(),
                    projectEntity.getToDate(),
                    projectEntity.getProjectLink(),
                    projectEntity.getCandidateId()
            ));
        }
        return domainProjects;
    }

    // Mapowanie CandidateEducationEntity -> CandidateEducation
    default List<CandidateEducation> mapCandidateEducationEntitiesToDomain(List<CandidateEducationEntity> education) {
        if (education == null) {
            return null;
        }
        List<CandidateEducation> domainEducation = new ArrayList<>();
        for (CandidateEducationEntity eduEntity : education) {
            domainEducation.add(new CandidateEducation(
                    eduEntity.getCandidateEducationId(),
                    eduEntity.getInstitution(),
                    eduEntity.getDegree(),
                    eduEntity.getFieldOfStudy(),
                    eduEntity.getFromDate(),
                    eduEntity.getToDate(),
                    eduEntity.getCandidateId()
            ));
        }
        return domainEducation;
    }

    // Mapowanie CandidateCourseEntity -> CandidateCourse
    default List<CandidateCourse> mapCandidateCourseEntitiesToDomain(List<CandidateCourseEntity> courses) {
        if (courses == null) {
            return null;
        }
        List<CandidateCourse> domainCourses = new ArrayList<>();
        for (CandidateCourseEntity courseEntity : courses) {
            domainCourses.add(new CandidateCourse(
                    courseEntity.getCandidateCourseId(),
                    courseEntity.getInstitution(),
                    courseEntity.getCourseTitle(),
                    courseEntity.getDescription(),
                    courseEntity.getTechnologies(),
                    courseEntity.getFromDate(),
                    courseEntity.getToDate(),
                    courseEntity.getCandidateId()
            ));
        }
        return domainCourses;
    }
}


