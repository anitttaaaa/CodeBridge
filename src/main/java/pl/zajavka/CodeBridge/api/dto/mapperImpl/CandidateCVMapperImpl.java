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

    @Override
    public List<CandidateExperienceDTO> mapCandidateExperiences(List<CandidateExperience> experiences) {
        if (experiences == null) return null;
        return experiences.stream()
                .map(this::mapCandidateExperienceToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidateProjectDTO> mapCandidateProjects(List<CandidateProject> projects) {
        if (projects == null) return null;
        return projects.stream()
                .map(this::mapCandidateProjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidateEducationDTO> mapCandidateEducationStages(List<CandidateEducation> educationStages) {
        if (educationStages == null) return null;
        return educationStages.stream()
                .map(this::mapCandidateEducationToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidateCourseDTO> mapCandidateCourses(List<CandidateCourse> courses) {
        if (courses == null) return null;
        return courses.stream()
                .map(this::mapCandidateCourseToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateCourseDTO mapCandidateCourseToDTO(CandidateCourse course) {
        if (course == null) return null;
        return new CandidateCourseDTO(
                course.getCandidateCourseId(),
                course.getInstitution(),
                course.getCourseTitle(),
                course.getDescription(),
                course.getTechnologies(),
                course.getFromDate(),
                course.getToDate(),
                course.getCandidateId()
                );
    }

    @Override
    public CandidateEducationDTO mapCandidateEducationToDTO(CandidateEducation education) {
        if (education == null) return null;
        return new CandidateEducationDTO(
                education.getCandidateEducationId(),
                education.getInstitution(),
                education.getDegree(),
                education.getFieldOfStudy(),
                education.getFromDate(),
                education.getToDate(),
                education.getCandidateId()
        );

    }

    @Override
    public CandidateExperienceDTO mapCandidateExperienceToDTO(CandidateExperience experience) {
        if (experience == null) {
            return null;
        }

        return new CandidateExperienceDTO(
                experience.getCandidateExperienceId(),
                experience.getCompanyName(),
                experience.getCandidatePosition(),
                experience.getDescription(),
                experience.getFromDate(),
                experience.getToDate(),
                experience.getCandidateId()
        );
    }

    @Override
    public CandidateProjectDTO mapCandidateProjectToDTO(CandidateProject project) {
        if (project == null) return null;
        return new CandidateProjectDTO(
                project.getCandidateProjectId(),
                project.getProjectTitle(),
                project.getTechnologies(),
                project.getDescription(),
                project.getFromDate(),
                project.getToDate(),
                project.getProjectLink(),
                project.getCandidateId()
        );
    }
}
