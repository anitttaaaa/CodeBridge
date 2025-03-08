package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CandidateCVMapper {

    default CandidateCVDTO mapToDto(Candidate candidate) {
        return new CandidateCVDTO(
                candidate.getProfilePhoto(),
                candidate.getCandidateId(),
                candidate.getName(),
                candidate.getSurname(),
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getLinkedIn(),
                candidate.getGitHub(),
                candidate.getTechSpecialization(),
                candidate.getCandidateSkills(),
                mapCandidateExperiences(candidate.getCandidateExperiences()), // Mapowanie listy doświadczeń
                mapCandidateProjects(candidate.getCandidateProjects()), // Mapowanie listy projektów
                mapCandidateEducationStages(candidate.getCandidateEducationStages()), // Mapowanie listy edukacji
                mapCandidateCourses(candidate.getCandidateCourses()), // Mapowanie listy kursów
                candidate.getHobby(),
                candidate.getUserId()
        );
    }

    // Mapowanie list obiektów
    default List<CandidateExperienceDTO> mapCandidateExperiences(List<CandidateExperience> experiences) {
        if (experiences == null) return null;
        return experiences.stream()
                .map(this::mapCandidateExperienceToDTO)
                .collect(Collectors.toList());
    }

    default List<CandidateProjectDTO> mapCandidateProjects(List<CandidateProject> projects) {
        if (projects == null) return null;
        return projects.stream()
                .map(this::mapCandidateProjectToDTO)
                .collect(Collectors.toList());
    }

    default List<CandidateEducationDTO> mapCandidateEducationStages(List<CandidateEducation> educationStages) {
        if (educationStages == null) return null;
        return educationStages.stream()
                .map(this::mapCandidateEducationToDTO)
                .collect(Collectors.toList());
    }

    default List<CandidateCourseDTO> mapCandidateCourses(List<CandidateCourse> courses) {
        if (courses == null) return null;
        return courses.stream()
                .map(this::mapCandidateCourseToDTO)
                .collect(Collectors.toList());
    }

    // Metody do mapowania pojedynczych obiektów
    CandidateExperienceDTO mapCandidateExperienceToDTO(CandidateExperience experience);

    CandidateProjectDTO mapCandidateProjectToDTO(CandidateProject project);

    CandidateEducationDTO mapCandidateEducationToDTO(CandidateEducation education);

    CandidateCourseDTO mapCandidateCourseToDTO(CandidateCourse course);
}
