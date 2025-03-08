package pl.zajavka.CodeBridge.api.dto;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

public class ApplicationsHistoryDTO {

    private Integer applicationHistoryId;

    private JobOfferDTO jobOffer;
    private EmployerDTO employer;
    private CandidateDTO candidate;
    private ApplicationStatus applicationStatus;

    public ApplicationsHistoryDTO(Integer applicationHistoryId, JobOfferDTO jobOffer, EmployerDTO employer, CandidateDTO candidate, ApplicationStatus applicationStatus) {
        this.applicationHistoryId = applicationHistoryId;
        this.jobOffer = jobOffer;
        this.employer = employer;
        this.candidate = candidate;
        this.applicationStatus = applicationStatus;
    }

    public ApplicationsHistoryDTO() {
    }

    public void setApplicationHistoryId(Integer applicationHistoryId) {
        this.applicationHistoryId = applicationHistoryId;
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
}
