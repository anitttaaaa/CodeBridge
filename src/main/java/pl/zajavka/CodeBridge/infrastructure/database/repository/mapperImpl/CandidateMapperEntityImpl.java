package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.*;
import pl.zajavka.CodeBridge.infrastructure.database.entity.*;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateEntityMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidateMapperEntityImpl implements CandidateEntityMapper {

    @Override
    public CandidateEntity mapCandidateToEntity(Candidate candidate) {
        return new CandidateEntity.Builder()
                .candidateId(candidate.getCandidateId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .status(candidate.getStatus())
                .userId(candidate.getUserId())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .aboutMe(candidate.getAboutMe())
                .hobby(candidate.getHobby())
                .candidateSkills(candidate.getCandidateSkills())
                .profilePhoto(candidate.getProfilePhoto())
                .candidateExperiences(mapCandidateExperiencesToEntities(candidate.getCandidateExperiences()))
                .candidateProjects(mapCandidateProjectsToEntities(candidate.getCandidateProjects()))
                .candidateEducationStages(mapCandidateEducationToEntities(candidate.getCandidateEducationStages()))
                .candidateCourses(mapCandidateCoursesToEntities(candidate.getCandidateCourses()))
                .build();
    }

    @Override
    public List<CandidateExperienceEntity> mapCandidateExperiencesToEntities(List<CandidateExperience> experiences) {
        List<CandidateExperienceEntity> entities = new ArrayList<>();
        for (CandidateExperience experience : experiences) {
            CandidateExperienceEntity entity = new CandidateExperienceEntity.Builder()
                    .candidateExperienceId(experience.getCandidateExperienceId())
                    .companyName(experience.getCompanyName())
                    .candidatePosition(experience.getCandidatePosition())
                    .description(experience.getDescription())
                    .fromDate(experience.getFromDate())
                    .toDate(experience.getToDate())
                    .candidateId(experience.getCandidateId())
                    .build();
            entities.add(entity);
        }
        return entities;
    }

    @Override
    public List<CandidateProjectEntity> mapCandidateProjectsToEntities(List<CandidateProject> projects) {
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

    @Override
    public List<CandidateEducationEntity> mapCandidateEducationToEntities(List<CandidateEducation> education) {
        if (education == null) {
            return null;
        }
        List<CandidateEducationEntity> entities = new ArrayList<>();
        for (CandidateEducation edu : education) {
            entities.add(new CandidateEducationEntity.Builder()
                    .candidateEducationId(edu.getCandidateEducationId())
                    .institution(edu.getInstitution())
                    .degree(edu.getDegree())
                    .fieldOfStudy(edu.getFieldOfStudy())
                    .fromDate(edu.getFromDate())
                    .toDate(edu.getToDate())
                    .candidateId(edu.getCandidateId())
                    .build());
        }
        return entities;
    }

    @Override
    public List<CandidateCourseEntity> mapCandidateCoursesToEntities(List<CandidateCourse> courses) {

        List<CandidateCourseEntity> entities = new ArrayList<>();
        for (CandidateCourse course : courses) {
            entities.add(new CandidateCourseEntity.Builder()
                    .candidateCourseId(course.getCandidateCourseId())
                    .institution(course.getInstitution())
                    .courseTitle(course.getCourseTitle())
                    .description(course.getDescription())
                    .technologies(course.getTechnologies())
                    .fromDate(course.getFromDate())
                    .toDate(course.getToDate())
                    .candidateId(course.getCandidateId())
                    .build());
        }
        return entities;
    }

    @Override
    public Candidate mapCandidateEntityToDomain(CandidateEntity candidateEntity) {

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

    @Override
    public List<CandidateExperience> mapCandidateExperienceEntitiesToDomain(List<CandidateExperienceEntity> experiences) {

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

    @Override
    public List<CandidateProject> mapCandidateProjectEntitiesToDomain(List<CandidateProjectEntity> projects) {

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

    @Override
    public List<CandidateEducation> mapCandidateEducationEntitiesToDomain(List<CandidateEducationEntity> education) {

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

    @Override
    public List<CandidateCourse> mapCandidateCourseEntitiesToDomain(List<CandidateCourseEntity> courses) {

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
