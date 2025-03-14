package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatusEnum;

import java.util.Objects;

@Entity
@Table(name = "job_application")
public class JobApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false)
    private Integer applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateEntity candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOfferEntity jobOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private EmployerEntity employer;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status", nullable = false)
    private ApplicationStatusEnum applicationStatusEnum;

    public JobApplicationEntity() {
    }


    public static Builder builder() {

        return new Builder();
    }


    private JobApplicationEntity(Builder builder) {
        this.applicationId = builder.applicationId;
        this.jobOffer = builder.jobOffer;
        this.employer = builder.employer;
        this.candidate = builder.candidate;
        this.applicationStatusEnum = builder.applicationStatusEnum;
    }

    public static class Builder {

        private Integer applicationId;
        private JobOfferEntity jobOffer;
        private EmployerEntity employer;
        private CandidateEntity candidate;
        private ApplicationStatusEnum applicationStatusEnum;

        public Builder applicationId(Integer applicationId) {
            this.applicationId = applicationId;
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

        public JobApplicationEntity build() {
            return new JobApplicationEntity(this);
        }
    }

    public Integer getApplicationId() {
        return applicationId;
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
        JobApplicationEntity that = (JobApplicationEntity) o;
        return Objects.equals(applicationId, that.applicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId);
    }

    @Override
    public String toString() {
        return "JobApplicationEntity{" +
                "applicationId=" + applicationId +
                ", jobOffer=" + jobOffer +
                ", employer=" + employer +
                ", candidate=" + candidate +
                ", applicationStatus=" + applicationStatusEnum +
                '}';
    }
}
