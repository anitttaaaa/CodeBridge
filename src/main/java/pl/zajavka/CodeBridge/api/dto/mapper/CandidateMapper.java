package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring")
public interface CandidateMapper {

    default CandidateDTO mapToDto(Candidate candidate) {
        return new CandidateDTO.Builder()
                .candidateId(candidate.getCandidateId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .status(candidate.getStatus())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .aboutMe(candidate.getAboutMe())
                .hobby(candidate.getHobby())
                .userId(candidate.getUserId())
                .profilePhoto(candidate.getProfilePhoto())
                .candidateSkills(candidate.getCandidateSkills())
                .candidateExperiences(mapCandidateExperiences(candidate.getCandidateExperiences()))  // ✅ FIX
                .candidateProjects(mapCandidateProjects(candidate.getCandidateProjects()))          // ✅ FIX
                .candidateEducationStages(mapCandidateEducationStages(candidate.getCandidateEducationStages())) // ✅ FIX
                .candidateCourses(mapCandidateCourses(candidate.getCandidateCourses()))            // ✅ FIX
                .build();
    }

    default Candidate mapToDomain(CandidateDTO candidateDTO) {
        return new Candidate.Builder()
                .candidateId(candidateDTO.getCandidateId())
                .name(candidateDTO.getName())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .phone(candidateDTO.getPhone())
                .status(candidateDTO.getStatus())
                .linkedIn(candidateDTO.getLinkedIn())
                .gitHub(candidateDTO.getGitHub())
                .techSpecialization(candidateDTO.getTechSpecialization())
                .aboutMe(candidateDTO.getAboutMe())
                .hobby(candidateDTO.getHobby())
                .userId(candidateDTO.getUserId())
                .profilePhoto(candidateDTO.getProfilePhoto())
                .candidateSkills(candidateDTO.getCandidateSkills())
                .candidateExperiences(mapToCandidateExperiences(candidateDTO.getCandidateExperiences())) // ✅ FIX
                .candidateProjects(mapToCandidateProjects(candidateDTO.getCandidateProjects()))           // ✅ FIX
                .candidateEducationStages(mapToCandidateEducationStages(candidateDTO.getCandidateEducationStages())) // ✅ FIX
                .candidateCourses(mapToCandidateCourses(candidateDTO.getCandidateCourses()))             // ✅ FIX
                .build();
    }

    // ✅ DODANE: Metody mapujące listy obiektów
    private static List<CandidateExperienceDTO> mapCandidateExperiences(List<CandidateExperience> experiences) {
        return (experiences == null) ? List.of() :
                experiences.stream()
                        .map(CandidateMapper::mapCandidateExperienceToDTO)
                        .collect(Collectors.toList());
    }

    private static List<CandidateProjectDTO> mapCandidateProjects(List<CandidateProject> projects) {
        return (projects == null) ? List.of() :
                projects.stream()
                        .map(CandidateMapper::mapCandidateProjectToDTO)
                        .collect(Collectors.toList());
    }

    private static List<CandidateEducationDTO> mapCandidateEducationStages(List<CandidateEducation> educationStages) {
        return (educationStages == null) ? List.of() :
                educationStages.stream()
                        .map(CandidateMapper::mapCandidateEducationToDTO)
                        .collect(Collectors.toList());
    }

    private static List<CandidateCourseDTO> mapCandidateCourses(List<CandidateCourse> courses) {
        return (courses == null) ? List.of() :
                courses.stream()
                        .map(CandidateMapper::mapCandidateCourseToDTO)
                        .collect(Collectors.toList());
    }

    private static List<CandidateExperience> mapToCandidateExperiences(List<CandidateExperienceDTO> experiencesDTO) {
        return (experiencesDTO == null) ? List.of() :
                experiencesDTO.stream()
                        .map(CandidateMapper::mapToCandidateExperience)
                        .collect(Collectors.toList());
    }

    private static List<CandidateProject> mapToCandidateProjects(List<CandidateProjectDTO> projectsDTO) {
        return (projectsDTO == null) ? List.of() :
                projectsDTO.stream()
                        .map(CandidateMapper::mapToCandidateProject)
                        .collect(Collectors.toList());
    }

    private static List<CandidateEducation> mapToCandidateEducationStages(List<CandidateEducationDTO> educationStagesDTO) {
        return (educationStagesDTO == null) ? List.of() :
                educationStagesDTO.stream()
                        .map(CandidateMapper::mapToCandidateEducation)
                        .collect(Collectors.toList());
    }

    private static List<CandidateCourse> mapToCandidateCourses(List<CandidateCourseDTO> coursesDTO) {
        return (coursesDTO == null) ? List.of() :
                coursesDTO.stream()
                        .map(CandidateMapper::mapToCandidateCourse)
                        .collect(Collectors.toList());
    }

    // ✅ DODANE: Metody mapujące pojedyncze obiekty
    private static CandidateExperienceDTO mapCandidateExperienceToDTO(CandidateExperience experience) {
        return new CandidateExperienceDTO.Builder()
                .candidateExperienceId(experience.getCandidateExperienceId())
                .companyName(experience.getCompanyName())
                .candidatePosition(experience.getCandidatePosition())
                .description(experience.getDescription())
                .fromDate(experience.getFromDate())
                .toDate(experience.getToDate())
                .candidateId(experience.getCandidateId())
                .build();
    }

    private static CandidateProjectDTO mapCandidateProjectToDTO(CandidateProject project) {
        return new CandidateProjectDTO.Builder()
                .candidateProjectId(project.getCandidateProjectId())
                .projectTitle(project.getProjectTitle())
                .technologies(project.getTechnologies())
                .description(project.getDescription())
                .fromDate(project.getFromDate())
                .toDate(project.getToDate())
                .projectLink(project.getProjectLink())
                .candidateId(project.getCandidateId())
                .build();
    }

    private static CandidateEducationDTO mapCandidateEducationToDTO(CandidateEducation education) {
        return new CandidateEducationDTO.Builder()
                .candidateEducationId(education.getCandidateEducationId())
                .institution(education.getInstitution())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .fromDate(education.getFromDate())
                .toDate(education.getToDate())
                .candidateId(education.getCandidateId())
                .build();
    }

    private static CandidateCourseDTO mapCandidateCourseToDTO(CandidateCourse course) {
        return new CandidateCourseDTO.Builder()
                .candidateCourseId(course.getCandidateCourseId())
                .institution(course.getInstitution())
                .courseTitle(course.getCourseTitle())
                .description(course.getDescription())
                .technologies(course.getTechnologies())
                .fromDate(course.getFromDate())
                .toDate(course.getToDate())
                .candidateId(course.getCandidateId())
                .build();
    }

    // ✅ DODANE: Metody mapujące DTO -> domena
    private static CandidateExperience mapToCandidateExperience(CandidateExperienceDTO experienceDTO) {
        return new CandidateExperience.Builder()
                .candidateExperienceId(experienceDTO.getCandidateExperienceId())
                .companyName(experienceDTO.getCompanyName())
                .candidatePosition(experienceDTO.getCandidatePosition())
                .description(experienceDTO.getDescription())
                .fromDate(experienceDTO.getFromDate())
                .toDate(experienceDTO.getToDate())
                .candidateId(experienceDTO.getCandidateId())
                .build();
    }

    private static CandidateProject mapToCandidateProject(CandidateProjectDTO projectDTO) {
        return new CandidateProject.Builder()
                .candidateProjectId(projectDTO.getCandidateProjectId())
                .projectTitle(projectDTO.getProjectTitle())
                .technologies(projectDTO.getTechnologies())
                .description(projectDTO.getDescription())
                .fromDate(projectDTO.getFromDate())
                .toDate(projectDTO.getToDate())
                .projectLink(projectDTO.getProjectLink())
                .candidateId(projectDTO.getCandidateId())
                .build();
    }

    private static CandidateEducation mapToCandidateEducation(CandidateEducationDTO educationDTO) {
        return new CandidateEducation.Builder()
                .candidateEducationId(educationDTO.getCandidateEducationId())
                .institution(educationDTO.getInstitution())
                .degree(educationDTO.getDegree())
                .fieldOfStudy(educationDTO.getFieldOfStudy())
                .fromDate(educationDTO.getFromDate())
                .toDate(educationDTO.getToDate())
                .candidateId(educationDTO.getCandidateId())
                .build();
    }
    private static CandidateCourse mapToCandidateCourse(CandidateCourseDTO courseDTO) {
        return new CandidateCourse.Builder()
                .candidateCourseId(courseDTO.getCandidateCourseId())
                .institution(courseDTO.getInstitution())
                .courseTitle(courseDTO.getCourseTitle())
                .description(courseDTO.getDescription())
                .technologies(courseDTO.getTechnologies())
                .fromDate(courseDTO.getFromDate())
                .toDate(courseDTO.getToDate())
                .candidateId(courseDTO.getCandidateId())
                .build();
    }
}
