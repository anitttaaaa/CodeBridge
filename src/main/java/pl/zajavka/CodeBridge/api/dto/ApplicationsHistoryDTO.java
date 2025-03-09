package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

public class ApplicationsHistoryDTO {

    private final Integer applicationHistoryId;
    private final JobOfferDTO jobOffer;
    private final EmployerDTO employer;
    private final CandidateDTO candidate;
    private final ApplicationStatus applicationStatus;

    // Private constructor to enforce use of the Builder
    private ApplicationsHistoryDTO(Builder builder) {
        this.applicationHistoryId = builder.applicationHistoryId;
        this.jobOffer = builder.jobOffer;
        this.employer = builder.employer;
        this.candidate = builder.candidate;
        this.applicationStatus = builder.applicationStatus;
    }

    public Integer getApplicationHistoryId() {
        return applicationHistoryId;
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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    // Builder class
    public static class Builder {
        private Integer applicationHistoryId;
        private JobOfferDTO jobOffer;
        private EmployerDTO employer;
        private CandidateDTO candidate;
        private ApplicationStatus applicationStatus;

        // Setter methods for each property
        public Builder applicationHistoryId(Integer applicationHistoryId) {
            this.applicationHistoryId = applicationHistoryId;
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

        public Builder applicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
            return this;
        }

        // Build method to return the final ApplicationsHistoryDTO object
        public ApplicationsHistoryDTO build() {
            return new ApplicationsHistoryDTO(this);
        }
    }
}
