package pl.zajavka.CodeBridge.api.dto;

import java.util.List;

public class CandidateDTO {

    private Integer candidateId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String status;
    private String linkedIn;
    private String gitHub;
    private String techSpecialization;
    private String aboutMe;
    private String hobby;
    private Integer userId;

    private byte[] profilePhoto;

    private List<String> candidateSkills;
    private List<CandidateExperienceDTO> candidateExperiences;
    private List<CandidateProjectDTO> candidateProjects;
    private List<CandidateEducationDTO> candidateEducationStages;
    private List<CandidateCourseDTO> candidateCourses;


    public CandidateDTO() {
    }

    public CandidateDTO(Integer candidateId, String name, String surname, String email,
                        String phone, String techSpecialization, List<String> candidateSkills) {
        this.candidateId = candidateId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.techSpecialization = techSpecialization;
        this.candidateSkills = candidateSkills;
    }

    public CandidateDTO(Integer candidateId, String name, String surname, String email, String phone, String status, String linkedIn, String gitHub, String techSpecialization, String aboutMe, String hobby, Integer userId, byte[] profilePhoto, List<String> candidateSkills, List<CandidateExperienceDTO> candidateExperiences, List<CandidateProjectDTO> candidateProjects, List<CandidateEducationDTO> candidateEducationStages, List<CandidateCourseDTO> candidateCourses) {
        this.candidateId = candidateId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.linkedIn = linkedIn;
        this.gitHub = gitHub;
        this.techSpecialization = techSpecialization;
        this.aboutMe = aboutMe;
        this.hobby = hobby;
        this.userId = userId;
        this.profilePhoto = profilePhoto;
        this.candidateSkills = candidateSkills;
        this.candidateExperiences = candidateExperiences;
        this.candidateProjects = candidateProjects;
        this.candidateEducationStages = candidateEducationStages;
        this.candidateCourses = candidateCourses;
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
}
