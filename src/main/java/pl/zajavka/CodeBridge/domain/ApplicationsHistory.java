package pl.zajavka.CodeBridge.domain;

import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

import java.util.Objects;


public class ApplicationsHistory {
    Integer applicationHistoryId;
    JobOffer jobOffer;
    Employer employer;
    Candidate candidate;
    ApplicationStatus applicationStatus;


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

    public ApplicationsHistory() {
    }

    public ApplicationsHistory(Integer applicationHistoryId, JobOffer jobOffer,
                               Employer employer, Candidate candidate,
                               ApplicationStatus applicationStatus) {
        this.applicationHistoryId = applicationHistoryId;
        this.jobOffer = jobOffer;
        this.employer = employer;
        this.candidate = candidate;
        this.applicationStatus = applicationStatus;
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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }
}
