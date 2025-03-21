package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.SalaryEnum;
import pl.zajavka.CodeBridge.api.enums.SkillsEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CandidateCVDTO {

    private final byte[] profilePhoto;
    private final Integer candidateId;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String linkedIn;
    private final String gitHub;
    private final TechSpecializationsEnum techSpecialization;
    private final List<SkillsEnum> candidateSkills;
    private final List<CandidateExperienceDTO> candidateExperiences;
    private final List<CandidateProjectDTO> candidateProjects;
    private final List<CandidateEducationDTO> candidateEducationStages;
    private final List<CandidateCourseDTO> candidateCourses;
    private final String hobby;
    private final Integer userId;

    private final String encodedImage;

    private CandidateCVDTO(Builder builder) {
        this.profilePhoto = builder.profilePhoto;
        this.candidateId = builder.candidateId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phone = builder.phone;
        this.linkedIn = builder.linkedIn;
        this.gitHub = builder.gitHub;
        this.techSpecialization = builder.techSpecialization;
        this.candidateSkills = builder.candidateSkills;
        this.candidateExperiences = builder.candidateExperiences;
        this.candidateProjects = builder.candidateProjects;
        this.candidateEducationStages = builder.candidateEducationStages;
        this.candidateCourses = builder.candidateCourses;
        this.hobby = builder.hobby;
        this.userId = builder.userId;
        this.encodedImage = builder.encodedImage;
    }

    public static class Builder {
        private byte[] profilePhoto;
        private Integer candidateId;
        private String name;
        private String surname;
        private String email;
        private String phone;
        private String linkedIn;
        private String gitHub;
        private TechSpecializationsEnum techSpecialization;
        private List<SkillsEnum> candidateSkills = new ArrayList<SkillsEnum>();
        private List<CandidateExperienceDTO> candidateExperiences = new ArrayList<>();
        private List<CandidateProjectDTO> candidateProjects = new ArrayList<>();
        private List<CandidateEducationDTO> candidateEducationStages = new ArrayList<>();
        private List<CandidateCourseDTO> candidateCourses = new ArrayList<>();
        private String hobby;
        private Integer userId;

        private String encodedImage;

        public Builder encodedImage(String encodedImage) {
            this.encodedImage = encodedImage;
            return this;
        }

        public Builder profilePhoto(byte[] profilePhoto) {
            this.profilePhoto = profilePhoto;
            return this;
        }

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

        public Builder candidateSkills(List<SkillsEnum> candidateSkills) {
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

        public Builder hobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public CandidateCVDTO build() {
            return new CandidateCVDTO(this);
        }
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
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

    public String getLinkedIn() {
        return linkedIn;
    }

    public String getGitHub() {
        return gitHub;
    }

    public TechSpecializationsEnum getTechSpecialization() {
        return techSpecialization;
    }

    public List<SkillsEnum> getCandidateSkills() {
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

    public String getHobby() {
        return hobby;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    @Override
    public String toString() {
        return "CandidateCVDTO{" +
                "profilePhoto=" + (profilePhoto != null ? profilePhoto.length : "null") +
                ", candidateId=" + candidateId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", linkedIn='" + linkedIn + '\'' +
                ", gitHub='" + gitHub + '\'' +
                ", techSpecialization='" + techSpecialization + '\'' +
                ", candidateSkills=" + candidateSkills +
                ", candidateExperiences=" + candidateExperiences +
                ", candidateProjects=" + candidateProjects +
                ", candidateEducationStages=" + candidateEducationStages +
                ", candidateCourses=" + candidateCourses +
                ", hobby='" + hobby + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateCVDTO that = (CandidateCVDTO) o;
        return Arrays.equals(profilePhoto, that.profilePhoto);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(profilePhoto);
    }
}
