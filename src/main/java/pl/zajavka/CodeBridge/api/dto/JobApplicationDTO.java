package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatusEnum;

public class JobApplicationDTO {
    private final Integer applicationId;
    private final JobOfferDTO jobOffer;
    private final EmployerDTO employer;
    private final CandidateDTO candidate;
    private final ApplicationStatusEnum applicationStatusEnum;

    private JobApplicationDTO(Builder builder) {
        this.applicationId = builder.applicationId;
        this.jobOffer = builder.jobOffer;
        this.employer = builder.employer;
        this.candidate = builder.candidate;
        this.applicationStatusEnum = builder.applicationStatusEnum;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public JobOfferDTO getJobOffer() {
        return jobOffer;
    }

    public EmployerDTO getEmployer() {
        return employer;
    }

    public CandidateDTO getCandidate() {
        return candidate;
    }

    public ApplicationStatusEnum getApplicationStatusEnum() {
        return applicationStatusEnum;
    }

    @Override
    public String toString() {
        return "JobApplicationDTO{" +
                "applicationId=" + applicationId +
                ", jobOffer=" + jobOffer +
                ", employer=" + employer +
                ", candidate=" + candidate +
                ", applicationStatusEnum=" + applicationStatusEnum +
                '}';
    }

    public static class Builder {
        private Integer applicationId;
        private JobOfferDTO jobOffer;
        private EmployerDTO employer;
        private CandidateDTO candidate;
        private ApplicationStatusEnum applicationStatusEnum;

        public Builder applicationId(Integer applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder jobOffer(JobOfferDTO jobOffer) {
            this.jobOffer = jobOffer;
            return this;
        }

        public Builder employer(EmployerDTO employer) {
            this.employer = employer;
            return this;
        }

        public Builder candidate(CandidateDTO candidate) {
            this.candidate = candidate;
            return this;
        }

        public Builder applicationStatusEnum(ApplicationStatusEnum applicationStatusEnum) {
            this.applicationStatusEnum = applicationStatusEnum;
            return this;
        }

        public JobApplicationDTO build() {
            return new JobApplicationDTO(this);
        }
    }
}
