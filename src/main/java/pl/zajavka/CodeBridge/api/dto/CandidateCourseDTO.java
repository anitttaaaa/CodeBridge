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


    public CandidateCourseDTO(Integer candidateCourseId, String institution, String courseTitle, String description,
                               String technologies, LocalDate fromDate, LocalDate toDate) {
        this.candidateCourseId = candidateCourseId;
        this.institution = institution;
        this.courseTitle = courseTitle;
        this.description = description;
        this.technologies = technologies;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    @Override
    public String toString() {
        return "CandidateCourseDTO{" +
                "candidateCourseId=" + candidateCourseId +
                ", institution='" + institution + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", description='" + description + '\'' +
                ", technologies='" + technologies + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    public static class Builder {
        private Integer candidateCourseId;
        private String institution;
        private String courseTitle;
        private String description;
        private String technologies;
        private LocalDate fromDate;
        private LocalDate toDate;

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

        public CandidateCourseDTO build() {
            return new CandidateCourseDTO(candidateCourseId, institution, courseTitle, description, technologies, fromDate, toDate);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
