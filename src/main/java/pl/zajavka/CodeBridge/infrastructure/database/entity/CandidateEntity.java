package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;

import java.util.List;
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
        private String status;
        private Integer userId;
        private String linkedIn;
        private String gitHub;
        private String techSpecialization;
        private String aboutMe;
        private String hobby;
        private List<String> candidateSkills;
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

        public Builder status(String status) {
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

        public Builder techSpecialization(String techSpecialization) {
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

        public Builder candidateSkills(List<String> candidateSkills) {
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
