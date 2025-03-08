package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "candidate_education")
public class CandidateEducationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_education_id")
    private Integer candidateEducationId;

    @Column(name = "institution")
    private String institution;

    @Column(name = "degree")
    private String degree;

    @Column(name = "field_of_study")
    private String fieldOfStudy;

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
        CandidateEducationEntity that = (CandidateEducationEntity) o;
        return Objects.equals(candidateEducationId, that.candidateEducationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateEducationId);
    }

    @Override
    public String toString() {
        return "CandidateEducationEntity{" +
                "candidateEducationId=" + candidateEducationId +
                ", institution='" + institution + '\'' +
                ", degree='" + degree + '\'' +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
    }

    public CandidateEducationEntity() {
    }

    public CandidateEducationEntity(Integer candidateEducationId, String institution,
                                    String degree, String fieldOfStudy, LocalDate fromDate,
                                    LocalDate toDate, Integer candidateId) {
        this.candidateEducationId = candidateEducationId;
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.candidateId = candidateId;
    }

    public Integer getCandidateEducationId() {
        return candidateEducationId;
    }

    public String getInstitution() {
        return institution;
    }

    public String getDegree() {
        return degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
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
