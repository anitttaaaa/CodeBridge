package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateCourseDTO {

    private final Integer candidateCourseId;
    private final String institution;
    private final String courseTitle;
    private final String description;
    private final String technologies;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Integer candidateId;

    private CandidateCourseDTO(Builder builder) {
        this.candidateCourseId = builder.candidateCourseId;
        this.institution = builder.institution;
        this.courseTitle = builder.courseTitle;
        this.description = builder.description;
        this.technologies = builder.technologies;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.candidateId = builder.candidateId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer candidateCourseId;
        private String institution;
        private String courseTitle;
        private String description;
        private String technologies;
        private LocalDate fromDate;
        private LocalDate toDate;
        private Integer candidateId;

        public Builder candidateCourseId(Integer candidateCourseId) {
            this.candidateCourseId = candidateCourseId;
            return this;
        }

        public Builder institution(String institution) {
            this.institution = institution;
            return this;
        }

        public Builder courseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder technologies(String technologies) {
            this.technologies = technologies;
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

        public CandidateCourseDTO build() {
            return new CandidateCourseDTO(this);
        }
    }

    public Integer getCandidateCourseId() {
        return candidateCourseId;
    }

    public String getInstitution() {
        return institution;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getTechnologies() {
        return technologies;
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