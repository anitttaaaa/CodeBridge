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

    public CandidateEducationEntity() {
    }

    private CandidateEducationEntity(Builder builder) {
        this.candidateEducationId = builder.candidateEducationId;
        this.institution = builder.institution;
        this.degree = builder.degree;
        this.fieldOfStudy = builder.fieldOfStudy;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.candidateId = builder.candidateId;
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

    public static class Builder {
        private Integer candidateEducationId;
        private String institution;
        private String degree;
        private String fieldOfStudy;
        private LocalDate fromDate;
        private LocalDate toDate;
        private Integer candidateId;

        public Builder candidateEducationId(Integer candidateEducationId) {
            this.candidateEducationId = candidateEducationId;
            return this;
        }

        public Builder institution(String institution) {
            this.institution = institution;
            return this;
        }

        public Builder degree(String degree) {
            this.degree = degree;
            return this;
        }

        public Builder fieldOfStudy(String fieldOfStudy) {
            this.fieldOfStudy = fieldOfStudy;
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

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public CandidateEducationEntity build() {
            return new CandidateEducationEntity(this);
        }
    }
}
