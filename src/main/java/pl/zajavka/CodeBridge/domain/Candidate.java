package pl.zajavka.CodeBridge.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class Candidate {

    Integer candidateId;
    String name;
    String surname;
    String email;
    String phone;
    String status;
    Integer userId;
    String linkedIn;
    String gitHub;
    String techSpecialization;
    String aboutMe;
    String hobby;
    List<String> candidateSkills;
    List<CandidateExperience> candidateExperiences;
    List<CandidateProject> candidateProjects;
    List<CandidateEducation> candidateEducationStages;
    List<CandidateCourse> candidateCourses;

    byte[] profilePhoto;

    public Candidate(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Candidate(Integer candidateId, String name, String surname, String email,
                     String phone, Integer userId, String techSpecialization,
                     List<String> candidateSkills) {
        this.candidateId = candidateId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
        this.techSpecialization = techSpecialization;
        this.candidateSkills = candidateSkills;
    }

    public Candidate(Integer candidateId, String name, String surname, String email,
                     String phone, String techSpecialization, List<String> candidateSkills) {
        this.candidateId = candidateId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.techSpecialization = techSpecialization;
        this.candidateSkills = candidateSkills;

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
                '}';
    }

    public Candidate() {
    }

    public Candidate(Integer candidateId, String name, String surname, String email, String phone, String status, String linkedIn, String gitHub, String techSpecialization, String aboutMe, String hobby, Integer userId, byte[] profilePhoto, List<String> candidateSkills, List<CandidateExperience> mapToCandidateExperiences, List<CandidateProject> mapToCandidateProjects, List<CandidateEducation> mapToCandidateEducationStages, List<CandidateCourse> mapToCandidateCourses) {
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
        this.candidateExperiences = mapToCandidateExperiences;
        this.candidateProjects = mapToCandidateProjects;
        this.candidateEducationStages = mapToCandidateEducationStages;
        this.candidateCourses = mapToCandidateCourses;
        this.profilePhoto = profilePhoto;
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

    public Candidate(Integer candidateId, String name, String surname, String email, String phone, String status, Integer userId, String linkedIn, String gitHub, String techSpecialization, String aboutMe, String hobby, List<String> candidateSkills, List<CandidateExperience> candidateExperiences, List<CandidateProject> candidateProjects, List<CandidateEducation> candidateEducationStages, List<CandidateCourse> candidateCourses, byte[] profilePhoto) {
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
        this.candidateExperiences = candidateExperiences;
        this.candidateProjects = candidateProjects;
        this.candidateEducationStages = candidateEducationStages;
        this.candidateCourses = candidateCourses;
        this.profilePhoto = profilePhoto;
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
}
