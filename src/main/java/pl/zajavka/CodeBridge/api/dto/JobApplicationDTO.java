package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;


public class JobApplicationDTO {

    private Integer applicationId;

    private JobOfferDTO jobOffer;
    private EmployerDTO employer;
    private CandidateDTO candidate;
    private ApplicationStatus applicationStatus;


    public JobApplicationDTO() {
    }

    public JobApplicationDTO(Integer applicationId, JobOfferDTO jobOffer,
                             EmployerDTO employer, CandidateDTO candidate,
                             ApplicationStatus applicationStatus) {
        this.applicationId = applicationId;
        this.jobOffer = jobOffer;
        this.employer = employer;
        this.candidate = candidate;
        this.applicationStatus = applicationStatus;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public void setJobOffer(JobOfferDTO jobOffer) {
        this.jobOffer = jobOffer;
    }

    public void setEmployer(EmployerDTO employer) {
        this.employer = employer;
    }

    public void setCandidate(CandidateDTO candidate) {
        this.candidate = candidate;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }
}
