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
                .candidateExperiences(mapCandidateExperiencesToDTO(candidate.getCandidateExperiences()))
                .candidateProjects(mapCandidateProjectsToDTO(candidate.getCandidateProjects()))
                .candidateEducationStages(mapCandidateEducationStagesToDTO(candidate.getCandidateEducationStages()))
                .candidateCourses(mapCandidateCoursesToDTO(candidate.getCandidateCourses()))
                .build();
    }


    public List<CandidateExperienceDTO> mapCandidateExperiencesToDTO(List<CandidateExperience> experiences) {
        return experiences == null ? List.of() :
                experiences.stream()
                        .map(experience -> new CandidateExperienceDTO(
                                experience.getCandidateExperienceId(),
                                experience.getCompanyName(),
                                experience.getCandidatePosition(),
                                experience.getDescription(),
                                experience.getFromDate(),
                                experience.getToDate(),
                                experience.getCandidateId()
                        ))
                        .collect(Collectors.toList());
    }

    public List<CandidateProjectDTO> mapCandidateProjectsToDTO(List<CandidateProject> projects) {
        return projects == null ? List.of() :
                projects.stream()
                        .map(project -> new CandidateProjectDTO(
                                project.getCandidateProjectId(),
                                project.getProjectTitle(),
                                project.getTechnologies(),
                                project.getDescription(),
                                project.getFromDate(),
                                project.getToDate(),
                                project.getProjectLink(),
                                project.getCandidateId()
                        ))
                        .collect(Collectors.toList());
    }


    public List<CandidateEducationDTO> mapCandidateEducationStagesToDTO(List<CandidateEducation> educationStages) {
        return educationStages == null ? List.of() :
                educationStages.stream()
                        .map(education -> new CandidateEducationDTO(
                                education.getCandidateEducationId(),
                                education.getInstitution(),
                                education.getDegree(),
                                education.getFieldOfStudy(),
                                education.getFromDate(),
                                education.getToDate(),
                                education.getCandidateId()
                        ))
                        .collect(Collectors.toList());
    }

    public List<CandidateCourseDTO> mapCandidateCoursesToDTO(List<CandidateCourse> courses) {
        return courses == null ? List.of() :
                courses.stream()
                        .map(course -> new CandidateCourseDTO(
                                course.getCandidateCourseId(),
                                course.getInstitution(),
                                course.getCourseTitle(),
                                course.getDescription(),
                                course.getTechnologies(),
                                course.getFromDate(),
                                course.getToDate(),
                                course.getCandidateId()
                        ))
                        .collect(Collectors.toList());
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


    public List<CandidateExperience> mapToCandidateExperiences(List<CandidateExperienceDTO> experiencesDTO) {
        return experiencesDTO == null ? List.of() :
                experiencesDTO.stream()
                        .map(experienceDTO -> new CandidateExperience.Builder()
                                .candidateExperienceId(experienceDTO.getCandidateExperienceId())
                                .companyName(experienceDTO.getCompanyName())
                                .candidatePosition(experienceDTO.getCandidatePosition())
                                .description(experienceDTO.getDescription())
                                .fromDate(experienceDTO.getFromDate())
                                .toDate(experienceDTO.getToDate())
                                .build()
                        ).collect(Collectors.toList());
    }

    public List<CandidateProject> mapToCandidateProjects(List<CandidateProjectDTO> projectsDTO) {
        return projectsDTO == null ? List.of() :
                projectsDTO.stream()
                        .map(projectDTO -> projectDTO == null ? null : new CandidateProject.Builder()
                                .candidateProjectId(projectDTO.getCandidateProjectId())
                                .projectTitle(projectDTO.getProjectTitle())
                                .technologies(projectDTO.getTechnologies())
                                .description(projectDTO.getDescription())
                                .fromDate(projectDTO.getFromDate())
                                .toDate(projectDTO.getToDate())
                                .projectLink(projectDTO.getProjectLink())
                                .candidateId(projectDTO.getCandidateId())
                                .build()
                        )
                        .collect(Collectors.toList());
    }


    public List<CandidateEducation> mapToCandidateEducationStages(List<CandidateEducationDTO> educationStagesDTO) {
        return educationStagesDTO == null ? List.of() :
                educationStagesDTO.stream()
                        .map(educationDTO -> new CandidateEducation.Builder()
                                .candidateEducationId(educationDTO.getCandidateEducationId())
                                .institution(educationDTO.getInstitution())
                                .degree(educationDTO.getDegree())
                                .fieldOfStudy(educationDTO.getFieldOfStudy())
                                .fromDate(educationDTO.getFromDate())
                                .toDate(educationDTO.getToDate())
                                .build()
                        ).collect(Collectors.toList());
    }

    public List<CandidateCourse> mapToCandidateCourses(List<CandidateCourseDTO> coursesDTO) {
        return coursesDTO == null ? List.of() :
                coursesDTO.stream()
                        .map(courseDTO -> new CandidateCourse.Builder()
                                .candidateCourseId(courseDTO.getCandidateCourseId())
                                .institution(courseDTO.getInstitution())
                                .courseTitle(courseDTO.getCourseTitle())
                                .description(courseDTO.getDescription())
                                .technologies(courseDTO.getTechnologies())
                                .fromDate(courseDTO.getFromDate())
                                .toDate(courseDTO.getToDate())
                                .build()
                        )
                        .collect(Collectors.toList());
    }
}
