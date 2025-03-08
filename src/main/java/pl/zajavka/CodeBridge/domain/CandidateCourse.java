package pl.zajavka.CodeBridge.domain;

import java.time.LocalDate;
import java.util.Objects;


public class CandidateCourse {

    Integer candidateCourseId;
    String institution;
    String courseTitle;
    String description;
    String technologies;
    LocalDate fromDate;
    LocalDate toDate;
    Integer candidateId;

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

    public CandidateCourse() {
    }

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

