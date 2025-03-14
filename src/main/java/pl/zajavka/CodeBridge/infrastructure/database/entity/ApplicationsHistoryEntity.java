package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatusEnum;

import java.util.Objects;

@Entity
@Table(name = "applications_history")
public class ApplicationsHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_history_id", nullable = false)
    private Integer applicationHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOfferEntity jobOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private EmployerEntity employer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateEntity candidate;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status", nullable = false)
    private ApplicationStatusEnum applicationStatusEnum;

    public ApplicationsHistoryEntity() {
    }

    public Integer getApplicationHistoryId() {
        return applicationHistoryId;
    }

    public JobOfferEntity getJobOffer() {
        return jobOffer;
    }

    public EmployerEntity getEmployer() {
        return employer;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public ApplicationStatusEnum getApplicationStatus() {
        return applicationStatusEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationsHistoryEntity that = (ApplicationsHistoryEntity) o;
        return Objects.equals(applicationHistoryId, that.applicationHistoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationHistoryId);
    }

    @Override
    public String toString() {
        return "ApplicationsHistoryEntity{" +
                "applicationHistoryId=" + applicationHistoryId +
                ", jobOffer=" + jobOffer +
                ", employer=" + employer +
                ", candidate=" + candidate +
                ", applicationStatus=" + applicationStatusEnum +
                '}';
    }

    private ApplicationsHistoryEntity(Builder builder) {
        this.applicationHistoryId = builder.applicationHistoryId;
        this.jobOffer = builder.jobOffer;
        this.employer = builder.employer;
        this.candidate = builder.candidate;
        this.applicationStatusEnum = builder.applicationStatusEnum;
    }

    public static class Builder {
        private Integer applicationHistoryId;
        private JobOfferEntity jobOffer;
        private EmployerEntity employer;
        private CandidateEntity candidate;
        private ApplicationStatusEnum applicationStatusEnum;

        public Builder applicationHistoryId(Integer applicationHistoryId) {
            this.applicationHistoryId = applicationHistoryId;
            return this;
        }

        public Builder jobOffer(JobOfferEntity jobOffer) {
            this.jobOffer = jobOffer;
            return this;
        }

        public Builder employer(EmployerEntity employer) {
            this.employer = employer;
            return this;
        }

        public Builder candidate(CandidateEntity candidate) {
            this.candidate = candidate;
            return this;
        }

        public Builder applicationStatus(ApplicationStatusEnum applicationStatusEnum) {
            this.applicationStatusEnum = applicationStatusEnum;
            return this;
        }

        public ApplicationsHistoryEntity build() {
            return new ApplicationsHistoryEntity(this);
        }
    }
}
