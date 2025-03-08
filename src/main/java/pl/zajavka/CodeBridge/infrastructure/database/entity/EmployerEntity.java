package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.Objects;
import java.util.Set;

@Builder

@Entity
@Table (name = "employer")
public class EmployerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private Integer employerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nip")
    private String nip;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employer")
    private Set<JobOfferEntity> jobOffers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employer")
    private Set<JobApplicationEntity> jobApplications;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployerEntity that = (EmployerEntity) o;
        return Objects.equals(employerId, that.employerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employerId);
    }

    @Override
    public String toString() {
        return "EmployerEntity{" +
                "employerId=" + employerId +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", nip='" + nip + '\'' +
                ", userId=" + userId +
                ", jobOffers=" + jobOffers +
                ", jobApplications=" + jobApplications +
                '}';
    }

    public EmployerEntity() {
    }

    public EmployerEntity(Integer employerId, String companyName, String email, String nip,
                          Integer userId, Set<JobOfferEntity> jobOffers,
                          Set<JobApplicationEntity> jobApplications) {
        this.employerId = employerId;
        this.companyName = companyName;
        this.email = email;
        this.nip = nip;
        this.userId = userId;
        this.jobOffers = jobOffers;
        this.jobApplications = jobApplications;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public String getNip() {
        return nip;
    }

    public Integer getUserId() {
        return userId;
    }

    public Set<JobOfferEntity> getJobOffers() {
        return jobOffers;
    }

    public Set<JobApplicationEntity> getJobApplications() {
        return jobApplications;
    }
}
