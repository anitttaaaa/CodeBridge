package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    public CandidateEntity(Integer candidateId, String name, String surname,
                           String email, String phone, String techSpecialization,
                           List<String> candidateSkills) {
        this.candidateId = candidateId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.techSpecialization = techSpecialization;
        this.candidateSkills = candidateSkills;
    }

    public CandidateEntity(Integer candidateId, String name, String surname, String email, String phone, Integer userId, String techSpecialization, List<String> candidateSkills) {
        this.candidateId = candidateId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
        this.techSpecialization = techSpecialization;
        this.candidateSkills = candidateSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateEntity that = (CandidateEntity) o;
        return Objects.equals(candidateId, that.candidateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId);
    }

    @Override
    public String toString() {
        return "CandidateEntity{" +
                "candidateId=" + candidateId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", linkedIn='" + linkedIn + '\'' +
                ", gitHub='" + gitHub + '\'' +
                ", techSpecialization='" + techSpecialization + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", hobby='" + hobby + '\'' +
                ", candidateSkills=" + candidateSkills +
                ", profilePhoto=" + Arrays.toString(profilePhoto) +
                ", candidateExperiences=" + candidateExperiences +
                ", candidateProjects=" + candidateProjects +
                ", candidateEducationStages=" + candidateEducationStages +
                ", candidateCourses=" + candidateCourses +
                ", jobApplications=" + jobApplications +
                '}';
    }

    public CandidateEntity() {
    }

    public CandidateEntity(Integer candidateId, String name, String surname, String email,
                           String phone, String status, Integer userId, String linkedIn,
                           String gitHub, String techSpecialization, String aboutMe,
                           String hobby, List<String> candidateSkills, byte[] profilePhoto,
                           List<CandidateExperienceEntity> candidateExperiences,
                           List<CandidateProjectEntity> candidateProjects,
                           List<CandidateEducationEntity> candidateEducationStages,
                           List<CandidateCourseEntity> candidateCourses,
                           Set<JobApplicationEntity> jobApplications) {
        this.candidateId = candidateId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.userId = userId;
        this.linkedIn = linkedIn;
        this.gitHub = gitHub;
        this.techSpecialization = techSpecialization;
        this.aboutMe = aboutMe;
        this.hobby = hobby;
        this.candidateSkills = candidateSkills;
        this.profilePhoto = profilePhoto;
        this.candidateExperiences = candidateExperiences;
        this.candidateProjects = candidateProjects;
        this.candidateEducationStages = candidateEducationStages;
        this.candidateCourses = candidateCourses;
        this.jobApplications = jobApplications;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public String getGitHub() {
        return gitHub;
    }

    public String getTechSpecialization() {
        return techSpecialization;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getHobby() {
        return hobby;
    }

    public List<String> getCandidateSkills() {
        return candidateSkills;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public List<CandidateExperienceEntity> getCandidateExperiences() {
        return candidateExperiences;
    }

    public List<CandidateProjectEntity> getCandidateProjects() {
        return candidateProjects;
    }

    public List<CandidateEducationEntity> getCandidateEducationStages() {
        return candidateEducationStages;
    }

    public List<CandidateCourseEntity> getCandidateCourses() {
        return candidateCourses;
    }

    public Set<JobApplicationEntity> getJobApplications() {
        return jobApplications;
    }
}

