package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    // Mapowanie główne
    default CandidateDTO mapToDto(Candidate candidate) {
        return new CandidateDTO(
                candidate.getCandidateId(),
                candidate.getName(),
                candidate.getSurname(),
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getStatus(),
                candidate.getLinkedIn(),
                candidate.getGitHub(),
                candidate.getTechSpecialization(),
                candidate.getAboutMe(),
                candidate.getHobby(),
                candidate.getUserId(),
                candidate.getProfilePhoto(),
                candidate.getCandidateSkills(),
                mapCandidateExperiences(candidate.getCandidateExperiences()), // Mapowanie listy doświadczeń
                mapCandidateProjects(candidate.getCandidateProjects()), // Mapowanie listy projektów
                mapCandidateEducationStages(candidate.getCandidateEducationStages()), // Mapowanie listy edukacji
                mapCandidateCourses(candidate.getCandidateCourses()) // Mapowanie listy kursów
        );
    }

    // Mapowanie z DTO na domain
    default Candidate mapToDomain(CandidateDTO candidateDTO) {
        return new Candidate(
                candidateDTO.getCandidateId(),
                candidateDTO.getName(),
                candidateDTO.getSurname(),
                candidateDTO.getEmail(),
                candidateDTO.getPhone(),
                candidateDTO.getStatus(),
                candidateDTO.getLinkedIn(),
                candidateDTO.getGitHub(),
                candidateDTO.getTechSpecialization(),
                candidateDTO.getAboutMe(),
                candidateDTO.getHobby(),
                candidateDTO.getUserId(),
                candidateDTO.getProfilePhoto(),
                candidateDTO.getCandidateSkills(),
                mapToCandidateExperiences(candidateDTO.getCandidateExperiences()),
                mapToCandidateProjects(candidateDTO.getCandidateProjects()),
                mapToCandidateEducationStages(candidateDTO.getCandidateEducationStages()),
                mapToCandidateCourses(candidateDTO.getCandidateCourses())
        );
    }


    default List<CandidateExperience> mapToCandidateExperiences(List<CandidateExperienceDTO> experiencesDTO) {
        return (experiencesDTO == null) ? List.of() :
                experiencesDTO.stream()
                        .map(this::mapToCandidateExperience)
                        .collect(Collectors.toList());
    }

    default List<CandidateProject> mapToCandidateProjects(List<CandidateProjectDTO> projectsDTO) {
        return (projectsDTO == null) ? List.of() :
                projectsDTO.stream()
                        .map(this::mapToCandidateProject)
                        .collect(Collectors.toList());
    }

    default List<CandidateEducation> mapToCandidateEducationStages(List<CandidateEducationDTO> educationStagesDTO) {
        return (educationStagesDTO == null) ? List.of() :
                educationStagesDTO.stream()
                        .map(this::mapToCandidateEducation)
                        .collect(Collectors.toList());
    }

    default List<CandidateCourse> mapToCandidateCourses(List<CandidateCourseDTO> coursesDTO) {
        return (coursesDTO == null) ? List.of() :
                coursesDTO.stream()
                        .map(this::mapToCandidateCourse)
                        .collect(Collectors.toList());
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

    // Metody do mapowania z DTO na domain
    CandidateExperience mapToCandidateExperience(CandidateExperienceDTO experienceDTO);

    CandidateProject mapToCandidateProject(CandidateProjectDTO projectDTO);

    CandidateEducation mapToCandidateEducation(CandidateEducationDTO educationDTO);

    CandidateCourse mapToCandidateCourse(CandidateCourseDTO courseDTO);
}
