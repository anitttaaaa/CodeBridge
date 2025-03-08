package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateProjectDTO {

    private Integer candidateProjectId;
    private String projectTitle;
    private String technologies;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String projectLink;
    private Integer candidateId;

    public Integer getCandidateProjectId() {
        return candidateProjectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getTechnologies() {
        return technologies;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public CandidateProjectDTO() {
    }

    public CandidateProjectDTO(Integer candidateProjectId, String projectTitle, String technologies, String description, LocalDate fromDate, LocalDate toDate, String projectLink, Integer candidateId) {
        this.candidateProjectId = candidateProjectId;
        this.projectTitle = projectTitle;
        this.technologies = technologies;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.projectLink = projectLink;
        this.candidateId = candidateId;
    }
}
