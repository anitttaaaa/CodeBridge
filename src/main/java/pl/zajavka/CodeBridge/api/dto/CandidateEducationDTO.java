package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateEducationDTO {

    private final Integer candidateEducationId;
    private final String institution;
    private final String degree;
    private final String fieldOfStudy;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Integer candidateId;

    public CandidateEducationDTO(Integer candidateEducationId, String institution, String degree, String fieldOfStudy,
                                 LocalDate fromDate, LocalDate toDate, Integer candidateId) {
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

    @Override
    public String toString() {
        return "CandidateEducationDTO{" +
                "candidateEducationId=" + candidateEducationId +
                ", institution='" + institution + '\'' +
                ", degree='" + degree + '\'' +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
    }
}
