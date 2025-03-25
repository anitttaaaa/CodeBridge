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


    @Override
    public String toString() {
        return "CandidateCourseDTO{" +
                "candidateCourseId=" + candidateCourseId +
                ", institution='" + institution + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", description='" + description + '\'' +
                ", technologies='" + technologies + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate;
    }


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
}
