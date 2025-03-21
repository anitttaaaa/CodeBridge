package pl.zajavka.CodeBridge.domain;

import java.time.LocalDate;
import java.util.Objects;


public class CandidateEducation {

    private final Integer candidateEducationId;
    private final String institution;
    private final String degree;
    private final String fieldOfStudy;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Integer candidateId;

    private CandidateEducation(Builder builder) {
        this.candidateEducationId = builder.candidateEducationId;
        this.institution = builder.institution;
        this.degree = builder.degree;
        this.fieldOfStudy = builder.fieldOfStudy;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.candidateId = builder.candidateId;
    }

    public static class Builder {
        private Integer candidateEducationId;
        private String institution;
        private String degree;
        private String fieldOfStudy;
        private LocalDate fromDate;
        private LocalDate toDate;
        private Integer candidateId;

        public Builder candidateEducationId(Integer candidateEducationId) {
            this.candidateEducationId = candidateEducationId;
            return this;
        }

        public Builder institution(String institution) {
            this.institution = institution;
            return this;
        }

        public Builder degree(String degree) {
            this.degree = degree;
            return this;
        }

        public Builder fieldOfStudy(String fieldOfStudy) {
            this.fieldOfStudy = fieldOfStudy;
            return this;
        }

        public Builder fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public CandidateEducation build() {
            return new CandidateEducation(this);
        }
    }

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
