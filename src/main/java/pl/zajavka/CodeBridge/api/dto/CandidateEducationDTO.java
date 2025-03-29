package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateEducationDTO {

    private final Integer candidateEducationId;
    private final String institution;
    private final String degree;
    private final String fieldOfStudy;
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public CandidateEducationDTO(Integer candidateEducationId, String institution, String degree, String fieldOfStudy,
                                  LocalDate fromDate, LocalDate toDate) {
        this.candidateEducationId = candidateEducationId;
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    @Override
    public String toString() {
        return "CandidateEducationDTO{" +
                "candidateEducationId=" + candidateEducationId +
                ", institution='" + institution + '\'' +
                ", degree='" + degree + '\'' +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    public static class Builder {
        private Integer candidateEducationId;
        private String institution;
        private String degree;
        private String fieldOfStudy;
        private LocalDate fromDate;
        private LocalDate toDate;

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

        public CandidateEducationDTO build() {
            return new CandidateEducationDTO(candidateEducationId, institution, degree, fieldOfStudy, fromDate, toDate);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
