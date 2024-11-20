package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateId")
@ToString(of = {"candidateId", "name", "surname", "email"})
public class Candidate {

    Integer candidateId;
    String name;
    String surname;
    String email;
    String phone;
    Integer userId;
    String linkedIn;
    String gitHub;
    String techSpecialization;
    String aboutMe;
    String hobby;
    List<String> candidateSkills;
    List<CandidateExperience> candidateExperiences;

    byte[] profilePhoto;

    public List<CandidateExperience> getCandidateExperiences() {
        return Objects.isNull(candidateExperiences) ? new ArrayList<>() : candidateExperiences;

    }


}
