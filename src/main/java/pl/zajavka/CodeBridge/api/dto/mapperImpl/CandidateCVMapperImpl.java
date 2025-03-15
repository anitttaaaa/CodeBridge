package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCVMapper;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateCVMapperImpl implements CandidateCVMapper {

    @Override
    public CandidateCVDTO mapToDto(Candidate candidate) {
        return new CandidateCVDTO.Builder()
                .profilePhoto(candidate.getProfilePhoto())
                .candidateId(candidate.getCandidateId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .candidateSkills(candidate.getCandidateSkills())
                .candidateExperiences(mapCandidateExperiences(candidate.getCandidateExperiences())) // Mapowanie listy doświadczeń
                .candidateProjects(mapCandidateProjects(candidate.getCandidateProjects())) // Mapowanie listy projektów
                .candidateEducationStages(mapCandidateEducationStages(candidate.getCandidateEducationStages())) // Mapowanie listy edukacji
                .candidateCourses(mapCandidateCourses(candidate.getCandidateCourses())) // Mapowanie listy kursów
                .hobby(candidate.getHobby())
                .userId(candidate.getUserId())
                .build();
    }


    public List<CandidateCourseDTO> mapCandidateCourses(List<CandidateCourse> courses) {
        if (courses == null) return null;
        return courses.stream()
                .map(course -> course == null ? null : CandidateCourseDTO.builder()
                        .candidateCourseId(course.getCandidateCourseId())
                        .institution(course.getInstitution())
                        .courseTitle(course.getCourseTitle())
                        .description(course.getDescription())
                        .technologies(course.getTechnologies())
                        .fromDate(course.getFromDate())
                        .toDate(course.getToDate())
                        .candidateId(course.getCandidateId())
                        .build())
                .collect(Collectors.toList());
    }

    public List<CandidateEducationDTO> mapCandidateEducationStages(List<CandidateEducation> educationStages) {
        if (educationStages == null) return null;
        return educationStages.stream()
                .map(education -> education == null ? null : CandidateEducationDTO.builder()
                        .candidateEducationId(education.getCandidateEducationId())
                        .institution(education.getInstitution())
                        .degree(education.getDegree())
                        .fieldOfStudy(education.getFieldOfStudy())
                        .fromDate(education.getFromDate())
                        .toDate(education.getToDate())
                        .candidateId(education.getCandidateId())
                        .build())
                .collect(Collectors.toList());
    }

    public List<CandidateExperienceDTO> mapCandidateExperiences(List<CandidateExperience> experiences) {
        if (experiences == null) return null;
        return experiences.stream()
                .map(experience -> experience == null ? null : CandidateExperienceDTO.builder()
                        .candidateExperienceId(experience.getCandidateExperienceId())
                        .companyName(experience.getCompanyName())
                        .candidatePosition(experience.getCandidatePosition())
                        .description(experience.getDescription())
                        .fromDate(experience.getFromDate())
                        .toDate(experience.getToDate())
                        .candidateId(experience.getCandidateId())
                        .build())
                .collect(Collectors.toList());
    }

    public List<CandidateProjectDTO> mapCandidateProjects(List<CandidateProject> projects) {
        if (projects == null) return null;
        return projects.stream()
                .map(project -> project == null ? null : CandidateProjectDTO.builder()
                        .candidateProjectId(project.getCandidateProjectId())
                        .projectTitle(project.getProjectTitle())
                        .technologies(project.getTechnologies())
                        .description(project.getDescription())
                        .fromDate(project.getFromDate())
                        .toDate(project.getToDate())
                        .projectLink(project.getProjectLink())
                        .candidateId(project.getCandidateId())
                        .build())
                .collect(Collectors.toList());
    }
}
