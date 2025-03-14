package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatusEnum;

public class JobApplicationDTO {
    private final Integer applicationId;
    private final JobOfferDTO jobOffer;
    private final EmployerDTO employer;
    private final CandidateDTO candidate;
    private final ApplicationStatusEnum applicationStatusEnum;


    public JobApplicationDTO(Integer applicationId, JobOfferDTO jobOffer, EmployerDTO employer,
                             CandidateDTO candidate, ApplicationStatusEnum applicationStatusEnum) {
        this.applicationId = applicationId;
        this.jobOffer = jobOffer;
        this.employer = employer;
        this.candidate = candidate;
        this.applicationStatusEnum = applicationStatusEnum;
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
}
