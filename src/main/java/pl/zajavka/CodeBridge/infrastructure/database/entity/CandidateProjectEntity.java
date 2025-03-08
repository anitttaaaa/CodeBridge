package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "candidate_project")
public class CandidateProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_project_id")
    private Integer candidateProjectId;

    @Column(name = "project_title")
    private String projectTitle;

    @Column(name = "technologies")
    private String technologies;

    @Column(name = "description")
    private String description;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "project_link")
    private String projectLink;

    @Column(name = "candidate_id")
    private Integer candidateId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateProjectEntity that = (CandidateProjectEntity) o;
        return Objects.equals(candidateProjectId, that.candidateProjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateProjectId);
    }

    @Override
    public String toString() {
        return "CandidateProjectEntity{" +
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

    public CandidateProjectEntity() {
    }

    public CandidateProjectEntity(Integer candidateProjectId, String projectTitle, String technologies, String description, LocalDate fromDate, LocalDate toDate, String projectLink, Integer candidateId) {
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