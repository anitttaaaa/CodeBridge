package pl.zajavka.CodeBridge.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Employer {

    Integer employerId;
    String companyName;
    String email;
    String nip;
    Integer userId;
    Set<JobOffer> jobOffers;
    Set<JobApplication> jobApplications;

    public Employer(Integer employerId) {
        this.employerId = employerId;
    }

    public Employer(Integer employerId, String companyName, String email, String nip, Integer userId) {
        this.employerId = employerId;
        this.companyName = companyName;
        this.email = email;
        this.nip = nip;
        this.userId = userId;
    }

    public Employer(Integer employerId, String companyName) {
        this.employerId = employerId;
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(employerId, employer.employerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employerId);
    }

    @Override
    public String toString() {
        return "Employer{" +
                "employerId=" + employerId +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", nip='" + nip + '\'' +
                ", userId=" + userId +
                ", jobOffers=" + jobOffers +
                ", jobApplications=" + jobApplications +
                '}';
    }

    public Set<JobOffer> getJobOffers() {
        return Objects.isNull(jobOffers) ? new HashSet<>() : jobOffers;
    }

    public Set<JobApplication> getJobApplications() {
        return Objects.isNull(jobApplications) ? new HashSet<>() : jobApplications;
    }


    public Employer() {
    }

    public Employer(Integer employerId, String companyName, String email, String nip,
                    Integer userId, Set<JobOffer> jobOffers, Set<JobApplication> jobApplications) {
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
}
