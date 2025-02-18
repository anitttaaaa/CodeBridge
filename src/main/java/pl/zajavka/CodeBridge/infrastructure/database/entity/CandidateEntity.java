package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "status")
    private String status;

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

    @Column(name = "profile_photo", columnDefinition="bytea")
    private byte[] profilePhoto;

    @OneToMany(mappedBy = "candidateId", fetch = FetchType.LAZY)
    private List<CandidateExperienceEntity> candidateExperiences;

    @OneToMany(mappedBy = "candidateId", fetch = FetchType.LAZY)
    private List<CandidateProjectEntity> candidateProjects;

    @OneToMany(mappedBy = "candidateId", fetch = FetchType.LAZY)
    private List<CandidateEducationEntity> candidateEducationStages;

    @OneToMany(mappedBy = "candidateId", fetch = FetchType.LAZY)
    private List<CandidateCourseEntity> candidateCourses;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private Set<JobApplicationEntity> jobApplications;

}

