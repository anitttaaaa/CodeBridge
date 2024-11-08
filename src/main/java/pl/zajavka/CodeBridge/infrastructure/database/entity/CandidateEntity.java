package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "candidateId")
@ToString(of = {"name", "surname","email"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Integer candidateId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "linked_in")
    private String linkedIn;

    @Column(name = "git_hub")
    private String gitHub;

    @Column(name = "tech_specialization")
    private String techSpecialization;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "hobby")
    private String hobby;

    @ElementCollection
    @CollectionTable(name = "candidate_skills", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name = "candidate_skills")
    private List<String> candidateSkills;

//    @ElementCollection
//    @CollectionTable(name = "candidate_experiences", joinColumns = @JoinColumn(name = "candidate_id"))
//    @Column(name = "candidate_experiences")
//    private  List<CandidateExperience> candidateExperiences;


    @Column(name = "profile_photo", columnDefinition="bytea")
    private byte[] profilePhoto;


}

