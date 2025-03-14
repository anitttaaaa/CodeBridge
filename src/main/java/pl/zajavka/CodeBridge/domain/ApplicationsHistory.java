package pl.zajavka.CodeBridge.domain;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatusEnum;

import java.util.Objects;

public class ApplicationsHistory {
    private final Integer applicationHistoryId;
    private final JobOffer jobOffer;
    private final Employer employer;
    private final Candidate candidate;
    private final ApplicationStatusEnum applicationStatusEnum;

    private ApplicationsHistory(Builder builder) {
        this.applicationHistoryId = builder.applicationHistoryId;
        this.jobOffer = builder.jobOffer;
        this.employer = builder.employer;
        this.candidate = builder.candidate;
        this.applicationStatusEnum = builder.applicationStatusEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationsHistory that = (ApplicationsHistory) o;
        return Objects.equals(applicationHistoryId, that.applicationHistoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationHistoryId);
    }

    @Override
    public String toString() {
        return "ApplicationsHistory{" +
                "applicationHistoryId=" + applicationHistoryId +
                ", jobOffer=" + jobOffer +
                ", employer=" + employer +
                ", candidate=" + candidate +
                ", applicationStatus=" + applicationStatusEnum +
                '}';
    }

    public Integer getApplicationHistoryId() {
        return applicationHistoryId;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public Employer getEmployer() {
        return employer;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public ApplicationStatusEnum getApplicationStatusEnum() {
        return applicationStatusEnum;
    }

    public static class Builder {
        private Integer applicationHistoryId;
        private JobOffer jobOffer;
        private Employer employer;
        private Candidate candidate;
        private ApplicationStatusEnum applicationStatusEnum;

        public Builder applicationHistoryId(Integer applicationHistoryId) {
            this.applicationHistoryId = applicationHistoryId;
            return this;
        }

        public Builder jobOffer(JobOffer jobOffer) {
            this.jobOffer = jobOffer;
            return this;
        }

        public Builder employer(Employer employer) {
            this.employer = employer;
            return this;
        }

        public Builder candidate(Candidate candidate) {
            this.candidate = candidate;
            return this;
        }

        public Builder applicationStatus(ApplicationStatusEnum applicationStatusEnum) {
            this.applicationStatusEnum = applicationStatusEnum;
            return this;
        }

        public ApplicationsHistory build() {
            return new ApplicationsHistory(this);
        }
    }
}
