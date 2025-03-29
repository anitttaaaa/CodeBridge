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

    public CandidateProjectDTO(Integer candidateProjectId, String projectTitle, String technologies,
                                String description, LocalDate fromDate, LocalDate toDate, String projectLink) {
        this.candidateProjectId = candidateProjectId;
        this.projectTitle = projectTitle;
        this.technologies = technologies;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.projectLink = projectLink;
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
                '}';
    }

    public static class Builder {
        private Integer candidateProjectId;
        private String projectTitle;
        private String technologies;
        private String description;
        private LocalDate fromDate;
        private LocalDate toDate;
        private String projectLink;

        public Builder candidateProjectId(Integer candidateProjectId) {
            this.candidateProjectId = candidateProjectId;
            return this;
        }

        public Builder projectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
            return this;
        }

        public Builder technologies(String technologies) {
            this.technologies = technologies;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
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

        public Builder projectLink(String projectLink) {
            this.projectLink = projectLink;
            return this;
        }

        public CandidateProjectDTO build() {
            return new CandidateProjectDTO(candidateProjectId, projectTitle, technologies, description, fromDate, toDate, projectLink);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
