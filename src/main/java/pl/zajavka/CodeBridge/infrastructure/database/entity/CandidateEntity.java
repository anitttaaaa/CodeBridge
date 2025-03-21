package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import pl.zajavka.CodeBridge.api.enums.SkillsEnum;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;

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
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "linked_in")
    private String linkedIn;

    @Column(name = "git_hub")
    private String gitHub;

    @Column(name = "tech_specialization")
    @Enumerated(EnumType.STRING)
    private TechSpecializationsEnum techSpecialization;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "hobby")
    private String hobby;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "candidate_skills", joinColumns = @JoinColumn(name = "candidate_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_name")
    private List<SkillsEnum> candidateSkills;

    @Column(name = "profile_photo", columnDefinition="bytea")
    private byte[] profilePhoto;

    @OneToMany(mappedBy = "candidateId")
    private List<CandidateExperienceEntity> candidateExperiences;

    @OneToMany(mappedBy = "candidateId")
    private List<CandidateProjectEntity> candidateProjects;

    @OneToMany(mappedBy = "candidateId")
    private List<CandidateEducationEntity> candidateEducationStages;

    @OneToMany(mappedBy = "candidateId")
    private List<CandidateCourseEntity> candidateCourses;

    @OneToMany(mappedBy = "candidate")
    private Set<JobApplicationEntity> jobApplications;

    @Override
    public String toString() {
        return "CandidateEntity{" +
                "candidateId=" + candidateId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                ", linkedIn='" + linkedIn + '\'' +
                ", gitHub='" + gitHub + '\'' +
                ", techSpecialization=" + techSpecialization +
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

    public CandidateEntity() {
    }

    private CandidateEntity(Builder builder) {
        this.candidateId = builder.candidateId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phone = builder.phone;
        this.status = builder.status;
        this.userId = builder.userId;
        this.linkedIn = builder.linkedIn;
        this.gitHub = builder.gitHub;
        this.techSpecialization = builder.techSpecialization;
        this.aboutMe = builder.aboutMe;
        this.hobby = builder.hobby;
        this.candidateSkills = builder.candidateSkills;
        this.profilePhoto = builder.profilePhoto;
        this.candidateExperiences = builder.candidateExperiences;
        this.candidateProjects = builder.candidateProjects;
        this.candidateEducationStages = builder.candidateEducationStages;
        this.candidateCourses = builder.candidateCourses;
        this.jobApplications = builder.jobApplications;
    }

    public static class Builder {
        private Integer candidateId;
        private String name;
        private String surname;
        private String email;
        private String phone;
        private StatusEnum status;
        private Integer userId;
        private String linkedIn;
        private String gitHub;
        private TechSpecializationsEnum techSpecialization;
        private String aboutMe;
        private String hobby;
        private List<SkillsEnum> candidateSkills;
        private byte[] profilePhoto;
        private List<CandidateExperienceEntity> candidateExperiences;
        private List<CandidateProjectEntity> candidateProjects;
        private List<CandidateEducationEntity> candidateEducationStages;
        private List<CandidateCourseEntity> candidateCourses;
        private Set<JobApplicationEntity> jobApplications;

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder status(StatusEnum status) {
            this.status = status;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder linkedIn(String linkedIn) {
            this.linkedIn = linkedIn;
            return this;
        }

        public Builder gitHub(String gitHub) {
            this.gitHub = gitHub;
            return this;
        }

        public Builder techSpecialization(TechSpecializationsEnum techSpecialization) {
            this.techSpecialization = techSpecialization;
            return this;
        }

        public Builder aboutMe(String aboutMe) {
            this.aboutMe = aboutMe;
            return this;
        }

        public Builder hobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        public Builder candidateSkills(List<SkillsEnum> candidateSkills) {
            this.candidateSkills = candidateSkills;
            return this;
        }

        public Builder profilePhoto(byte[] profilePhoto) {
            this.profilePhoto = profilePhoto;
            return this;
        }

        public Builder candidateExperiences(List<CandidateExperienceEntity> candidateExperiences) {
            this.candidateExperiences = candidateExperiences;
            return this;
        }

        public Builder candidateProjects(List<CandidateProjectEntity> candidateProjects) {
            this.candidateProjects = candidateProjects;
            return this;
        }

        public Builder candidateEducationStages(List<CandidateEducationEntity> candidateEducationStages) {
            this.candidateEducationStages = candidateEducationStages;
            return this;
        }

        public Builder candidateCourses(List<CandidateCourseEntity> candidateCourses) {
            this.candidateCourses = candidateCourses;
            return this;
        }

        public Builder jobApplications(Set<JobApplicationEntity> jobApplications) {
            this.jobApplications = jobApplications;
            return this;
        }

        public CandidateEntity build() {
            return new CandidateEntity(this);
        }
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

    public StatusEnum getStatus() {
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

    public TechSpecializationsEnum getTechSpecialization() {
        return techSpecialization;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getHobby() {
        return hobby;
    }

    public List<SkillsEnum> getCandidateSkills() {
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
