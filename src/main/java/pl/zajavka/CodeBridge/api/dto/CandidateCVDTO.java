package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.util.List;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCVDTO {


    private byte[] profilePhoto;

    private Integer candidateId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String linkedIn;
    private String gitHub;
    private String techSpecialization;
    private List<String> candidateSkills;

    private List<CandidateExperienceDTO> candidateExperiences;
    private List<CandidateProjectDTO> candidateProjects;
    private List<CandidateEducationDTO> candidateEducationStages;
    private List<CandidateCourseDTO> candidateCourses;

    private String hobby;
    private Integer userId;


}