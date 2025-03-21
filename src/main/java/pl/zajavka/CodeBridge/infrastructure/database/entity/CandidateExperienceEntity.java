package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "candidate_experience")
public class CandidateExperienceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_experience_id")
    private Integer candidateExperienceId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "candidate_position")
    private String candidatePosition;

    @Column(name = "description")
    private String description;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "candidate_id")
    private Integer candidateId;

    public CandidateExperienceEntity() {
    }

    private CandidateExperienceEntity(Builder builder) {
        this.candidateExperienceId = builder.candidateExperienceId;
        this.companyName = builder.companyName;
        this.candidatePosition = builder.candidatePosition;
        this.description = builder.description;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.candidateId = builder.candidateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateExperienceEntity that = (CandidateExperienceEntity) o;
        return Objects.equals(candidateExperienceId, that.candidateExperienceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateExperienceId);
    }

    @Override
    public String toString() {
        return "CandidateExperienceEntity{" +
                "candidateExperienceId=" + candidateExperienceId +
                ", companyName='" + companyName + '\'' +
                ", candidatePosition='" + candidatePosition + '\'' +
                ", description='" + description + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
    }

    public Integer getCandidateExperienceId() {
        return candidateExperienceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCandidatePosition() {
        return candidatePosition;
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

    public Integer getCandidateId() {
        return candidateId;
    }

    public static class Builder {

        private Integer candidateExperienceId;
        private String companyName;
        private String candidatePosition;
        private String description;
        private LocalDate fromDate;
        private LocalDate toDate;
        private Integer candidateId;

        public Builder candidateExperienceId(Integer candidateExperienceId) {
            this.candidateExperienceId = candidateExperienceId;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder candidatePosition(String candidatePosition) {
            this.candidatePosition = candidatePosition;
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

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public CandidateExperienceEntity build() {
            return new CandidateExperienceEntity(this);
        }
    }
}
