package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateEducationDTO {

    private Integer candidateEducationId;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer candidateId;

    public CandidateEducationDTO() {
    }

    public CandidateEducationDTO(Integer candidateEducationId, String institution, String degree, String fieldOfStudy, LocalDate fromDate, LocalDate toDate, Integer candidateId) {
        this.candidateEducationId = candidateEducationId;
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.candidateId = candidateId;
    }

    public Integer getCandidateEducationId() {
        return candidateEducationId;
    }

    public String getInstitution() {
        return institution;
    }

    public String getDegree() {
        return degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
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
