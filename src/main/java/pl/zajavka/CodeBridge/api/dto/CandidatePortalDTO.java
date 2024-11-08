package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.util.List;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePortalDTO {


    private Integer candidateId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String linkedIn;
    private String gitHub;
    private String techSpecialization;
    private String aboutMe;
    private String hobby;
    private List<String> candidateSkills;
    private Integer userId;

    private byte[] profilePhoto;

    private List<CandidateExperienceDTO> candidateExperiences;

}
