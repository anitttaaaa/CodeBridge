package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateMapperImpl implements CandidateMapper {

    @Override
    public CandidateDTO mapToDto(Candidate candidate) {
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
                .candidateExperiences(mapCandidateExperiences(candidate.getCandidateExperiences()))
                .candidateProjects(mapCandidateProjects(candidate.getCandidateProjects()))
                .candidateEducationStages(mapCandidateEducationStages(candidate.getCandidateEducationStages()))
                .candidateCourses(mapCandidateCourses(candidate.getCandidateCourses()))
                .build();
    }

    @Override
    public Candidate mapToDomain(CandidateDTO candidateDTO) {
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
                .candidateExperiences(mapToCandidateExperiences(candidateDTO.getCandidateExperiences()))
                .candidateProjects(mapToCandidateProjects(candidateDTO.getCandidateProjects()))
                .candidateEducationStages(mapToCandidateEducationStages(candidateDTO.getCandidateEducationStages()))
                .candidateCourses(mapToCandidateCourses(candidateDTO.getCandidateCourses()))
                .build();
    }

    // Metody mapujące listy obiektów
    @Override
    public List<CandidateExperienceDTO> mapCandidateExperiences(List<CandidateExperience> experiences) {
        return experiences == null ? List.of() :
                experiences.stream()
                        .map(this::mapCandidateExperienceToDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public List<CandidateProjectDTO> mapCandidateProjects(List<CandidateProject> projects) {
        return projects == null ? List.of() :
                projects.stream()
                        .map(this::mapCandidateProjectToDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public List<CandidateEducationDTO> mapCandidateEducationStages(List<CandidateEducation> educationStages) {
        return educationStages == null ? List.of() :
                educationStages.stream()
                        .map(this::mapCandidateEducationToDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public List<CandidateCourseDTO> mapCandidateCourses(List<CandidateCourse> courses) {
        return courses == null ? List.of() :
                courses.stream()
                        .map(this::mapCandidateCourseToDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public List<CandidateExperience> mapToCandidateExperiences(List<CandidateExperienceDTO> experiencesDTO) {
        return experiencesDTO == null ? List.of() :
                experiencesDTO.stream()
                        .map(this::mapToCandidateExperience)
                        .collect(Collectors.toList());
    }

    @Override
    public List<CandidateProject> mapToCandidateProjects(List<CandidateProjectDTO> projectsDTO) {
        return projectsDTO == null ? List.of() :
                projectsDTO.stream()
                        .map(this::mapToCandidateProject)
                        .collect(Collectors.toList());
    }

    @Override
    public List<CandidateEducation> mapToCandidateEducationStages(List<CandidateEducationDTO> educationStagesDTO) {
        return educationStagesDTO == null ? List.of() :
                educationStagesDTO.stream()
                        .map(this::mapToCandidateEducation)
                        .collect(Collectors.toList());
    }

    @Override
    public List<CandidateCourse> mapToCandidateCourses(List<CandidateCourseDTO> coursesDTO) {
        return coursesDTO == null ? List.of() :
                coursesDTO.stream()
                        .map(this::mapToCandidateCourse)
                        .collect(Collectors.toList());
    }

    // Metody mapujące pojedyncze obiekty
    private CandidateExperienceDTO mapCandidateExperienceToDTO(CandidateExperience experience) {
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

    private CandidateProjectDTO mapCandidateProjectToDTO(CandidateProject project) {
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

    private CandidateEducationDTO mapCandidateEducationToDTO(CandidateEducation education) {
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

    private CandidateCourseDTO mapCandidateCourseToDTO(CandidateCourse course) {
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

    // Metody mapujące DTO -> domena
    private CandidateExperience mapToCandidateExperience(CandidateExperienceDTO experienceDTO) {
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

    private CandidateProject mapToCandidateProject(CandidateProjectDTO projectDTO) {
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

    private CandidateEducation mapToCandidateEducation(CandidateEducationDTO educationDTO) {
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

    private CandidateCourse mapToCandidateCourse(CandidateCourseDTO courseDTO) {
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
