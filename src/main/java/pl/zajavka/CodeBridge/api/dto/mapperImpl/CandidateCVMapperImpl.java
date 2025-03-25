package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCVMapper;
import pl.zajavka.CodeBridge.domain.*;

import java.util.ArrayList;
import java.util.List;

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
                .candidateExperiences(mapCandidateExperiences(candidate.getCandidateExperiences()))
                .candidateProjects(mapCandidateProjects(candidate.getCandidateProjects()))
                .candidateEducationStages(mapCandidateEducationStages(candidate.getCandidateEducationStages()))
                .candidateCourses(mapCandidateCourses(candidate.getCandidateCourses()))
                .hobby(candidate.getHobby())
                .userId(candidate.getUserId())
                .build();
    }


    public List<CandidateCourseDTO> mapCandidateCourses(List<CandidateCourse> courses) {
        if (courses == null) return null;

        List<CandidateCourseDTO> courseDTOList = new ArrayList<>();

        for (CandidateCourse course : courses) {
            if (course != null) {
                CandidateCourseDTO courseDTO = new CandidateCourseDTO(
                        course.getCandidateCourseId(),
                        course.getInstitution(),
                        course.getCourseTitle(),
                        course.getDescription(),
                        course.getTechnologies(),
                        course.getFromDate(),
                        course.getToDate()
                );
                courseDTOList.add(courseDTO);
            }
        }
        return courseDTOList;
    }

    public List<CandidateEducationDTO> mapCandidateEducationStages(List<CandidateEducation> educationStages) {
        if (educationStages == null) return null;

        List<CandidateEducationDTO> educationDTOList = new ArrayList<>();

        for (CandidateEducation education : educationStages) {
            if (education != null) {
                CandidateEducationDTO educationDTO = new CandidateEducationDTO(
                        education.getCandidateEducationId(),
                        education.getInstitution(),
                        education.getDegree(),
                        education.getFieldOfStudy(),
                        education.getFromDate(),
                        education.getToDate());
                educationDTOList.add(educationDTO);
            }
        }

        return educationDTOList;
    }


    public List<CandidateExperienceDTO> mapCandidateExperiences(List<CandidateExperience> experiences) {
        if (experiences == null) return null;

        List<CandidateExperienceDTO> experienceDTOList = new ArrayList<>();

        for (CandidateExperience experience : experiences) {
            if (experience != null) {
                CandidateExperienceDTO experienceDTO = new CandidateExperienceDTO(
                        experience.getCandidateExperienceId(),
                        experience.getCompanyName(),
                        experience.getCandidatePosition(),
                        experience.getDescription(),
                        experience.getFromDate(),
                        experience.getToDate()
                );
                experienceDTOList.add(experienceDTO);
            }
        }

        return experienceDTOList;
    }


    public List<CandidateProjectDTO> mapCandidateProjects(List<CandidateProject> projects) {
        if (projects == null) return null;

        List<CandidateProjectDTO> projectDTOList = new ArrayList<>();

        for (CandidateProject project : projects) {
            if (project != null) {
                CandidateProjectDTO projectDTO = new CandidateProjectDTO(
                        project.getCandidateProjectId(),
                        project.getProjectTitle(),
                        project.getTechnologies(),
                        project.getDescription(),
                        project.getFromDate(),
                        project.getToDate(),
                        project.getProjectLink()
                );
                projectDTOList.add(projectDTO);
            }
        }
        return projectDTOList;
    }

}
