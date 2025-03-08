package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;



@Builder

@Entity
@Table(name = "candidate_course")
public class CandidateCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_course_id")
    private Integer candidateCourseId;

    @Column(name = "institution")
    private String institution;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "technologies")
    private String technologies;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "candidate_id")
    private Integer candidateId;





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateCourseEntity that = (CandidateCourseEntity) o;
        return Objects.equals(candidateCourseId, that.candidateCourseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateCourseId);
    }

    @Override
    public String toString() {
        return "CandidateCourseEntity{" +
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

    public CandidateCourseEntity() {
    }

    public CandidateCourseEntity(Integer candidateCourseId, String institution, String courseTitle,
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
