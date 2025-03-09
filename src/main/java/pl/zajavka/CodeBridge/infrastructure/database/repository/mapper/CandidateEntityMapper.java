package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.*;
import pl.zajavka.CodeBridge.infrastructure.database.entity.*;

import java.util.List;

public interface CandidateEntityMapper {

    CandidateEntity mapCandidateToEntity(Candidate candidate);

    List<CandidateExperienceEntity> mapCandidateExperiencesToEntities(List<CandidateExperience> experiences);

    List<CandidateProjectEntity> mapCandidateProjectsToEntities(List<CandidateProject> projects);

    List<CandidateEducationEntity> mapCandidateEducationToEntities(List<CandidateEducation> education);

    List<CandidateCourseEntity> mapCandidateCoursesToEntities(List<CandidateCourse> courses);

    Candidate mapCandidateEntityToDomain(CandidateEntity candidateEntity);

    List<CandidateExperience> mapCandidateExperienceEntitiesToDomain(List<CandidateExperienceEntity> experiences);

    List<CandidateProject> mapCandidateProjectEntitiesToDomain(List<CandidateProjectEntity> projects);

    List<CandidateEducation> mapCandidateEducationEntitiesToDomain(List<CandidateEducationEntity> education);

    List<CandidateCourse> mapCandidateCourseEntitiesToDomain(List<CandidateCourseEntity> courses);
}
