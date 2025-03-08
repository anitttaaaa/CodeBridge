package pl.zajavka.CodeBridge.domain;

import java.time.LocalDate;
import java.util.Objects;


public class CandidateExperience {

    Integer candidateExperienceId;
    String companyName;
    String candidatePosition;
    String description;
    LocalDate fromDate;
    LocalDate toDate;
    Integer candidateId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateExperience that = (CandidateExperience) o;
        return Objects.equals(candidateExperienceId, that.candidateExperienceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateExperienceId);
    }

    @Override
    public String toString() {
        return "CandidateExperience{" +
                "candidateExperienceId=" + candidateExperienceId +
                ", companyName='" + companyName + '\'' +
                ", candidatePosition='" + candidatePosition + '\'' +
                ", description='" + description + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
    }

    public CandidateExperience() {
    }

    public CandidateExperience(Integer candidateExperienceId, String companyName,
                               String candidatePosition, String description, LocalDate fromDate,
                               LocalDate toDate, Integer candidateId) {
        this.candidateExperienceId = candidateExperienceId;
        this.companyName = companyName;
        this.candidatePosition = candidatePosition;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.candidateId = candidateId;
    }

    public Integer getCandidateExperienceId() {
        return candidateExperienceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCandidatePosition() {
        return candidatePosition;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Integer getCandidateId() {
        return candidateId;
    }
}
