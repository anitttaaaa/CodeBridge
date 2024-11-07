package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.util.List;

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
    byte[] profilePhoto;


}
