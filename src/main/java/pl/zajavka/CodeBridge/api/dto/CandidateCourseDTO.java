package pl.zajavka.CodeBridge.api.dto;


import java.time.LocalDate;

public class CandidateCourseDTO {

    private Integer candidateCourseId;
    private String institution;
    private String courseTitle;
    private String description;
    private String technologies;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer candidateId;

    public CandidateCourseDTO(Integer candidateCourseId, String institution, String courseTitle, String description,
                              String technologies, LocalDate fromDate, LocalDate toDate, Integer candidateId) {
        this.candidateCourseId = candidateCourseId;
        this.institution = institution;
        this.courseTitle = courseTitle;
        this.description = description;
        this.technologies = technologies;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.candidateId = candidateId;
    }

    public CandidateCourseDTO() {
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
