package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.SalaryEnum;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CandidateDTO {

    private final Integer candidateId;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final StatusEnum status;
    private final String linkedIn;
    private final String gitHub;
    private final TechSpecializationsEnum techSpecialization;
    private final String aboutMe;
    private final String hobby;
    private final Integer userId;
    private final byte[] profilePhoto;
    private final List<String> candidateSkills;
    private final List<CandidateExperienceDTO> candidateExperiences;
    private final List<CandidateProjectDTO> candidateProjects;
    private final List<CandidateEducationDTO> candidateEducationStages;
    private final List<CandidateCourseDTO> candidateCourses;

    private CandidateDTO(Builder builder) {
        this.candidateId = builder.candidateId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phone = builder.phone;
        this.status = builder.status;
        this.linkedIn = builder.linkedIn;
        this.gitHub = builder.gitHub;
        this.techSpecialization = builder.techSpecialization;
        this.aboutMe = builder.aboutMe;
        this.hobby = builder.hobby;
        this.userId = builder.userId;
        this.profilePhoto = builder.profilePhoto;
        this.candidateSkills = builder.candidateSkills;
        this.candidateExperiences = builder.candidateExperiences;
        this.candidateProjects = builder.candidateProjects;
        this.candidateEducationStages = builder.candidateEducationStages;
        this.candidateCourses = builder.candidateCourses;
    }

    public static class Builder {
        private Integer candidateId;
        private String name;
        private String surname;
        private String email;
        private String phone;
        private StatusEnum status;
        private String linkedIn;
        private String gitHub;
        private TechSpecializationsEnum techSpecialization;
        private String aboutMe;
        private String hobby;
        private Integer userId;
        private byte[] profilePhoto;
        private List<String> candidateSkills;
        private List<CandidateExperienceDTO> candidateExperiences;
        private List<CandidateProjectDTO> candidateProjects;
        private List<CandidateEducationDTO> candidateEducationStages;
        private List<CandidateCourseDTO> candidateCourses;

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

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder profilePhoto(byte[] profilePhoto) {
            this.profilePhoto = profilePhoto;
            return this;
        }

        public Builder candidateSkills(List<String> candidateSkills) {
            this.candidateSkills = candidateSkills;
            return this;
        }

        public Builder candidateExperiences(List<CandidateExperienceDTO> candidateExperiences) {
            this.candidateExperiences = candidateExperiences;
            return this;
        }

        public Builder candidateProjects(List<CandidateProjectDTO> candidateProjects) {
            this.candidateProjects = candidateProjects;
            return this;
        }

        public Builder candidateEducationStages(List<CandidateEducationDTO> candidateEducationStages) {
            this.candidateEducationStages = candidateEducationStages;
            return this;
        }

        public Builder candidateCourses(List<CandidateCourseDTO> candidateCourses) {
            this.candidateCourses = candidateCourses;
            return this;
        }

        public CandidateDTO build() {
            return new CandidateDTO(this);
        }
    }

    public static Builder builder() {
        return new Builder();
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

    public Integer getUserId() {
        return userId;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public List<String> getCandidateSkills() {
        return candidateSkills;
    }

    public List<CandidateExperienceDTO> getCandidateExperiences() {
        return candidateExperiences;
    }

    public List<CandidateProjectDTO> getCandidateProjects() {
        return candidateProjects;
    }

    public List<CandidateEducationDTO> getCandidateEducationStages() {
        return candidateEducationStages;
    }

    public List<CandidateCourseDTO> getCandidateCourses() {
        return candidateCourses;
    }

    @Override
    public String toString() {
        return "CandidateDTO{" +
                "candidateId=" + candidateId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", linkedIn='" + linkedIn + '\'' +
                ", gitHub='" + gitHub + '\'' +
                ", techSpecialization=" + techSpecialization +
                ", aboutMe='" + aboutMe + '\'' +
                ", hobby='" + hobby + '\'' +
                ", userId=" + userId +
                ", profilePhoto=" + Arrays.toString(profilePhoto) +
                ", candidateSkills=" + candidateSkills +
                ", candidateExperiences=" + candidateExperiences +
                ", candidateProjects=" + candidateProjects +
                ", candidateEducationStages=" + candidateEducationStages +
                ", candidateCourses=" + candidateCourses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateDTO that = (CandidateDTO) o;
        return Objects.equals(candidateId, that.candidateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId);
    }
}
