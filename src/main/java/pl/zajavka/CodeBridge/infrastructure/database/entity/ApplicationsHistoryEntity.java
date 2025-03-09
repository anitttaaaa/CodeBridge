package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import pl.zajavka.CodeBridge.api.enums.ApplicationStatus;

import java.util.Objects;

@Entity
@Table(name = "applications_history")
public class ApplicationsHistoryEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_history_id", nullable = false)
    private Integer applicationHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOfferEntity jobOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private EmployerEntity employer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateEntity candidate;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status", nullable = false)
    private ApplicationStatus applicationStatus;






    public Integer getApplicationHistoryId() {
        return applicationHistoryId;
    }

    public JobOfferEntity getJobOffer() {
        return jobOffer;
    }

    public EmployerEntity getEmployer() {
        return employer;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationsHistoryEntity that = (ApplicationsHistoryEntity) o;
        return Objects.equals(applicationHistoryId, that.applicationHistoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationHistoryId);
    }

    @Override
    public String toString() {
        return "ApplicationsHistoryEntity{" +
                "applicationHistoryId=" + applicationHistoryId +
                ", jobOffer=" + jobOffer +
                ", employer=" + employer +
                ", candidate=" + candidate +
                ", applicationStatus=" + applicationStatus +
                '}';
    }

    public ApplicationsHistoryEntity() {
    }

    public ApplicationsHistoryEntity(Integer applicationHistoryId, JobOfferEntity jobOffer,
                                     EmployerEntity employer, CandidateEntity candidate,
                                     ApplicationStatus applicationStatus) {
        this.applicationHistoryId = applicationHistoryId;
        this.jobOffer = jobOffer;
        this.employer = employer;
        this.candidate = candidate;
        this.applicationStatus = applicationStatus;
    }
}
