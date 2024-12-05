package pl.zajavka.CodeBridge.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateId")
@ToString(of = {"candidateId", "name", "surname", "email"})
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


}
