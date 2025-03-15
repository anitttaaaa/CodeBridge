package pl.zajavka.CodeBridge.domain;

import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Candidate {

    private final Integer candidateId;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final StatusEnum status;
    private final Integer userId;
    private final String linkedIn;
    private final String gitHub;
    private final TechSpecializationsEnum techSpecialization;
    private final String aboutMe;
    private final String hobby;
    private final List<String> candidateSkills;
    private final List<CandidateExperience> candidateExperiences;
    private final List<CandidateProject> candidateProjects;
    private final List<CandidateEducation> candidateEducationStages;
    private final List<CandidateCourse> candidateCourses;
    private final byte[] profilePhoto;

    private Candidate(Builder builder) {
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
        this.candidateExperiences = builder.candidateExperiences;
        this.candidateProjects = builder.candidateProjects;
        this.candidateEducationStages = builder.candidateEducationStages;
        this.candidateCourses = builder.candidateCourses;
        this.profilePhoto = builder.profilePhoto;
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
        private List<String> candidateSkills = new ArrayList<String>();
        private List<CandidateExperience> candidateExperiences = new ArrayList<>();
        private List<CandidateProject> candidateProjects = new ArrayList<>();
        private List<CandidateEducation> candidateEducationStages = new ArrayList<>();
        private List<CandidateCourse> candidateCourses = new ArrayList<>();
        private byte[] profilePhoto;

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

        public Builder candidateSkills(List<String> candidateSkills) {
            this.candidateSkills = candidateSkills;
            return this;
        }

        public Builder candidateExperiences(List<CandidateExperience> candidateExperiences) {
            this.candidateExperiences = candidateExperiences;
            return this;
        }

        public Builder candidateProjects(List<CandidateProject> candidateProjects) {
            this.candidateProjects = candidateProjects;
            return this;
        }

        public Builder candidateEducationStages(List<CandidateEducation> candidateEducationStages) {
            this.candidateEducationStages = candidateEducationStages;
            return this;
        }

        public Builder candidateCourses(List<CandidateCourse> candidateCourses) {
            this.candidateCourses = candidateCourses;
            return this;
        }

        public Builder profilePhoto(byte[] profilePhoto) {
            this.profilePhoto = profilePhoto;
            return this;
        }

        public Candidate build() {
            return new Candidate(this);
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

    public List<String> getCandidateSkills() {
        return candidateSkills;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public List<CandidateExperience> getCandidateExperiences() {
        return Objects.isNull(candidateExperiences) ? new ArrayList<>() : candidateExperiences;
    }

    public List<CandidateProject> getCandidateProjects() {
        return Objects.isNull(candidateProjects) ? new ArrayList<>() : candidateProjects;
    }

    public List<CandidateEducation> getCandidateEducationStages() {
        return Objects.isNull(candidateEducationStages) ? new ArrayList<>() : candidateEducationStages;
    }

    public List<CandidateCourse> getCandidateCourses() {
        return Objects.isNull(candidateCourses) ? new ArrayList<>() : candidateCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(candidateId, candidate.candidateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId);
    }

    @Override
    public String toString() {
        return "Candidate{" +
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
                ", candidateExperiences=" + candidateExperiences +
                ", candidateProjects=" + candidateProjects +
                ", candidateEducationStages=" + candidateEducationStages +
                ", candidateCourses=" + candidateCourses +
                ", profilePhoto=" + (profilePhoto != null ? profilePhoto.length : "null") +
                '}';
    }
}
