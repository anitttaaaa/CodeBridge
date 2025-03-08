package pl.zajavka.CodeBridge.domain;

import lombok.*;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

import java.util.Objects;

public class JobApplication {

    Integer applicationId;
    JobOffer jobOffer;
    Employer employer;
    Candidate candidate;
    ApplicationStatus applicationStatus;

    public JobApplication(JobOffer jobOfferId, Employer employerId, Candidate candidateId, ApplicationStatus applicationStatus) {
        this.jobOffer = jobOfferId;
        this.employer = employerId;
        this.candidate = candidateId;
        this.applicationStatus = applicationStatus;
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
                ", applicationStatus=" + applicationStatus +
                '}';
    }

    public JobApplication() {
    }

    public JobApplication(Integer applicationId, JobOffer jobOffer, Employer employer,
                          Candidate candidate, ApplicationStatus applicationStatus) {
        this.applicationId = applicationId;
        this.jobOffer = jobOffer;
        this.employer = employer;
        this.candidate = candidate;
        this.applicationStatus = applicationStatus;
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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }
}
