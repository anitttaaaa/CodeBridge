package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;

public interface CandidateMapper {

    CandidateDTO mapToDto(Candidate candidate);

    Candidate mapToDomain(CandidateDTO candidateDTO);

    List<CandidateExperienceDTO> mapCandidateExperiences(List<CandidateExperience> experiences);

    List<CandidateProjectDTO> mapCandidateProjects(List<CandidateProject> projects);

    List<CandidateEducationDTO> mapCandidateEducationStages(List<CandidateEducation> educationStages);

    List<CandidateCourseDTO> mapCandidateCourses(List<CandidateCourse> courses);

    List<CandidateExperience> mapToCandidateExperiences(List<CandidateExperienceDTO> experiencesDTO);

    List<CandidateProject> mapToCandidateProjects(List<CandidateProjectDTO> projectsDTO);

    List<CandidateEducation> mapToCandidateEducationStages(List<CandidateEducationDTO> educationStagesDTO);

    List<CandidateCourse> mapToCandidateCourses(List<CandidateCourseDTO> coursesDTO);
}
