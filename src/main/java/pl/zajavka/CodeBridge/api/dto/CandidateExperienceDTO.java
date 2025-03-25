package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateExperienceDTO {
    private final Integer candidateExperienceId;
    private final String companyName;
    private final String candidatePosition;
    private final String description;
    private final LocalDate fromDate;
    private final LocalDate toDate;

    @Override
    public String toString() {
        return "CandidateExperienceDTO{" +
                "candidateExperienceId=" + candidateExperienceId +
                ", companyName='" + companyName + '\'' +
                ", candidatePosition='" + candidatePosition + '\'' +
                ", description='" + description + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate;
    }

    ;


    public CandidateExperienceDTO(
            Integer candidateExperienceId,
            String companyName,
            String candidatePosition,
            String description,
            LocalDate fromDate,
            LocalDate toDate) {
        this.candidateExperienceId = candidateExperienceId;
        this.companyName = companyName;
        this.candidatePosition = candidatePosition;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

}