package pl.zajavka.CodeBridge.domain;

import java.time.LocalDate;
import java.util.Objects;


public class CandidateProject {

    private final Integer candidateProjectId;
    private final String projectTitle;
    private final String technologies;
    private final String description;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final String projectLink;
    private final Integer candidateId;

    private CandidateProject(Builder builder) {
        this.candidateProjectId = builder.candidateProjectId;
        this.projectTitle = builder.projectTitle;
        this.technologies = builder.technologies;
        this.description = builder.description;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.projectLink = builder.projectLink;
        this.candidateId = builder.candidateId;
    }

    public static class Builder {
        private Integer candidateProjectId;
        private String projectTitle;
        private String technologies;
        private String description;
        private LocalDate fromDate;
        private LocalDate toDate;
        private String projectLink;
        private Integer candidateId;

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

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public CandidateProject build() {
            return new CandidateProject(this);
        }
    }


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
