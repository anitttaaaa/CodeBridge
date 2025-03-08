package pl.zajavka.CodeBridge.domain;

import java.time.LocalDate;
import java.util.Objects;


public class CandidateProject {

    Integer candidateProjectId;
    String projectTitle;
    String technologies;
    String description;
    LocalDate fromDate;
    LocalDate toDate;
    String projectLink;
    Integer candidateId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateProject that = (CandidateProject) o;
        return Objects.equals(candidateProjectId, that.candidateProjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateProjectId);
    }

    @Override
    public String toString() {
        return "CandidateProject{" +
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

    public CandidateProject() {
    }

    public CandidateProject(Integer candidateProjectId, String projectTitle, String technologies, String description, LocalDate fromDate, LocalDate toDate, String projectLink, Integer candidateId) {
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
}
