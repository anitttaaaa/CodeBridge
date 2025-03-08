package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateExperienceDTO {

    private Integer candidateExperienceId;
    private String companyName;
    private String candidatePosition;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer candidateId;

    public CandidateExperienceDTO() {
    }

    public CandidateExperienceDTO(Integer candidateExperienceId, String companyName,
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
