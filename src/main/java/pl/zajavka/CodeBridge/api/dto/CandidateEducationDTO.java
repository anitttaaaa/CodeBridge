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

    private CandidateEducationDTO(Builder builder) {
        this.candidateEducationId = builder.candidateEducationId;
        this.institution = builder.institution;
        this.degree = builder.degree;
        this.fieldOfStudy = builder.fieldOfStudy;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.candidateId = builder.candidateId;
    }

    public static Builder builder() {
        return new Builder();
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

        public CandidateEducationDTO build() {
            return new CandidateEducationDTO(this);
        }
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
