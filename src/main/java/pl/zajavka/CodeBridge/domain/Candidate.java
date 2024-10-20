package pl.zajavka.CodeBridge.domain;

import lombok.*;

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
    String city;
    String bio;
    byte[] profilePhoto;
}
