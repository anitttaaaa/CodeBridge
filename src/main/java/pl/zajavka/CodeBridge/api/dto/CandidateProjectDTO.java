package pl.zajavka.CodeBridge.api.dto;

import java.time.LocalDate;

public class CandidateProjectDTO {

    private final Integer candidateProjectId;
    private final String projectTitle;
    private final String technologies;
    private final String description;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final String projectLink;
    private final Integer candidateId;

    public CandidateProjectDTO(Integer candidateProjectId, String projectTitle, String technologies,
                               String description, LocalDate fromDate, LocalDate toDate, String projectLink, Integer candidateId) {
        this.candidateProjectId = candidateProjectId;
        this.projectTitle = projectTitle;
        this.technologies = technologies;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.projectLink = projectLink;
        this.candidateId = candidateId;
    }

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

    @Override
    public String toString() {
        return "CandidateProjectDTO{" +
                "candidateProjectId=" + candidateProjectId +
                ", projectTitle='" + projectTitle + '\'' +
                ", technologies='" + technologies + '\'' +
                ", description='" + description + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", projectLink='" + projectLink + '\'' +
                ", candidateId=" + candidateId +
                '}';
    }
}
