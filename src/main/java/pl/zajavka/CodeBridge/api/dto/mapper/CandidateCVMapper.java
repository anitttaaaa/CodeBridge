package pl.zajavka.CodeBridge.api.dto.mapper;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;
@Component
public interface CandidateCVMapper {

    CandidateCVDTO mapToDto(Candidate candidate);

    List<CandidateExperienceDTO> mapCandidateExperiences(List<CandidateExperience> experiences);

    List<CandidateProjectDTO> mapCandidateProjects(List<CandidateProject> projects);

    List<CandidateEducationDTO> mapCandidateEducationStages(List<CandidateEducation> educationStages);

    List<CandidateCourseDTO> mapCandidateCourses(List<CandidateCourse> courses);

    CandidateCourseDTO mapCandidateCourseToDTO(CandidateCourse course);

    CandidateEducationDTO mapCandidateEducationToDTO(CandidateEducation education);

    CandidateExperienceDTO mapCandidateExperienceToDTO(CandidateExperience experience);

    CandidateProjectDTO mapCandidateProjectToDTO(CandidateProject project);
}
