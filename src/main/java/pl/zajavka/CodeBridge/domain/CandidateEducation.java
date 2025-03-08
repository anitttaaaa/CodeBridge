package pl.zajavka.CodeBridge.domain;

import java.time.LocalDate;
import java.util.Objects;


public class CandidateEducation {

    Integer candidateEducationId;
    String institution;
    String degree;
    String fieldOfStudy;
    LocalDate fromDate;
    LocalDate toDate;
    Integer candidateId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateEducation that = (CandidateEducation) o;
        return Objects.equals(candidateEducationId, that.candidateEducationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateEducationId);
    }

    @Override
    public String toString() {
        return "CandidateEducation{" +
                "candidateEducationId=" + candidateEducationId +
                ", institution='" + institution + '\'' +
                ", degree='" + degree + '\'' +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
    }

    public CandidateEducation() {
    }

    public CandidateEducation(Integer candidateEducationId, String institution, String degree,
                              String fieldOfStudy, LocalDate fromDate, LocalDate toDate,
                              Integer candidateId) {
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
