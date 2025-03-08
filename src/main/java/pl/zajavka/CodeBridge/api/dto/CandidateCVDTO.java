package pl.zajavka.CodeBridge.api.dto;

import java.util.List;

public class CandidateCVDTO {


    private byte[] profilePhoto;

    private Integer candidateId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String linkedIn;
    private String gitHub;
    private String techSpecialization;
    private List<String> candidateSkills;

    private List<CandidateExperienceDTO> candidateExperiences;
    private List<CandidateProjectDTO> candidateProjects;
    private List<CandidateEducationDTO> candidateEducationStages;
    private List<CandidateCourseDTO> candidateCourses;

    private String hobby;
    private Integer userId;

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

    public String getTechSpecialization() {
        return techSpecialization;
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

    public String getHobby() {
        return hobby;
    }

    public Integer getUserId() {
        return userId;
    }

    public CandidateCVDTO() {
    }

    public CandidateCVDTO(byte[] profilePhoto, Integer candidateId, String name, String surname, String email, String phone, String linkedIn, String gitHub, String techSpecialization, List<String> candidateSkills, List<CandidateExperienceDTO> candidateExperiences, List<CandidateProjectDTO> candidateProjects, List<CandidateEducationDTO> candidateEducationStages, List<CandidateCourseDTO> candidateCourses, String hobby, Integer userId) {
        this.profilePhoto = profilePhoto;
        this.candidateId = candidateId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.linkedIn = linkedIn;
        this.gitHub = gitHub;
        this.techSpecialization = techSpecialization;
        this.candidateSkills = candidateSkills;
        this.candidateExperiences = candidateExperiences;
        this.candidateProjects = candidateProjects;
        this.candidateEducationStages = candidateEducationStages;
        this.candidateCourses = candidateCourses;
        this.hobby = hobby;
        this.userId = userId;
    }
}
