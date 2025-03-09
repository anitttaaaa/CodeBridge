package pl.zajavka.CodeBridge.domain;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

import java.util.Objects;

public class ApplicationsHistory {
    private final Integer applicationHistoryId;
    private final JobOffer jobOffer;
    private final Employer employer;
    private final Candidate candidate;
    private final ApplicationStatus applicationStatus;

    // Private constructor to enforce the use of the Builder
    private ApplicationsHistory(Builder builder) {
        this.applicationHistoryId = builder.applicationHistoryId;
        this.jobOffer = builder.jobOffer;
        this.employer = builder.employer;
        this.candidate = builder.candidate;
        this.applicationStatus = builder.applicationStatus;
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
                ", applicationStatus=" + applicationStatus +
                '}';
    }

    // Getters
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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    // Builder class
    public static class Builder {
        private Integer applicationHistoryId;
        private JobOffer jobOffer;
        private Employer employer;
        private Candidate candidate;
        private ApplicationStatus applicationStatus;

        // Setter methods for each property
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

        public Builder applicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
            return this;
        }

        // Build method to return the final ApplicationsHistory object
        public ApplicationsHistory build() {
            return new ApplicationsHistory(this);
        }
    }
}
