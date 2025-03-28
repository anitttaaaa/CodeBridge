package pl.zajavka.CodeBridge.domain;

import java.time.LocalDate;
import java.util.Objects;


public class CandidateCourse {

    private final Integer candidateCourseId;
    private final String institution;
    private final String courseTitle;
    private final String description;
    private final String technologies;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Integer candidateId;

    public CandidateCourse(Integer candidateCourseId, String institution, String courseTitle,
                           String description, String technologies, LocalDate fromDate,
                           LocalDate toDate, Integer candidateId) {
        this.candidateCourseId = candidateCourseId;
        this.institution = institution;
        this.courseTitle = courseTitle;
        this.description = description;
        this.technologies = technologies;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.candidateId = candidateId;
    }

    private CandidateCourse(Builder builder) {
        this.candidateCourseId = builder.candidateCourseId;
        this.institution = builder.institution;
        this.courseTitle = builder.courseTitle;
        this.description = builder.description;
        this.technologies = builder.technologies;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.candidateId = builder.candidateId;
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

        public CandidateCourse build() {
            return new CandidateCourse(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateCourse that = (CandidateCourse) o;
        return Objects.equals(candidateCourseId, that.candidateCourseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateCourseId);
    }

    @Override
    public String toString() {
        return "CandidateCourse{" +
                "candidateCourseId=" + candidateCourseId +
                ", institution='" + institution + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", description='" + description + '\'' +
                ", technologies='" + technologies + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
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

