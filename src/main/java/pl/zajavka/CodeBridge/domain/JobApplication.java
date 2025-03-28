package pl.zajavka.CodeBridge.domain;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatusEnum;

import java.util.Objects;

public class JobApplication {

    private final Integer applicationId;
    private final JobOffer jobOffer;
    private final Employer employer;
    private final Candidate candidate;
    private final ApplicationStatusEnum applicationStatusEnum;

    private JobApplication(JobApplicationBuilder builder) {
        this.applicationId = builder.applicationId;
        this.jobOffer = builder.jobOffer;
        this.employer = builder.employer;
        this.candidate = builder.candidate;
        this.applicationStatusEnum = builder.applicationStatusEnum;
    }


    public Integer getApplicationId() {
        return applicationId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobApplication that = (JobApplication) o;
        return Objects.equals(applicationId, that.applicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId);
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "applicationId=" + applicationId +
                ", jobOffer=" + jobOffer +
                ", employer=" + employer +
                ", candidate=" + candidate +
                ", applicationStatus=" + applicationStatusEnum +
                '}';
    }

    public static class JobApplicationBuilder {

        private Integer applicationId;
        private JobOffer jobOffer;
        private Employer employer;
        private Candidate candidate;
        private ApplicationStatusEnum applicationStatusEnum;

        public JobApplicationBuilder applicationId(Integer applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public JobApplicationBuilder jobOffer(JobOffer jobOffer) {
            this.jobOffer = jobOffer;
            return this;
        }

        public JobApplicationBuilder employer(Employer employer) {
            this.employer = employer;
            return this;
        }

        public JobApplicationBuilder candidate(Candidate candidate) {
            this.candidate = candidate;
            return this;
        }

        public JobApplicationBuilder jobApplicationStatus(ApplicationStatusEnum applicationStatusEnum) {
            this.applicationStatusEnum = applicationStatusEnum;
            return this;
        }

        public JobApplication build() {
            return new JobApplication(this);
        }
    }
}
