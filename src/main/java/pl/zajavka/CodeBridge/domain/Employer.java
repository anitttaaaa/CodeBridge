package pl.zajavka.CodeBridge.domain;

import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Employer {

    private final Integer employerId;
    private final String companyName;
    private final String email;
    private final String nip;
    private final Integer userId;
    private final Set<JobOffer> jobOffers;
    private final Set<JobApplication> jobApplications;

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

    private Employer(EmployerBuilder builder) {
        this.employerId = builder.employerId;
        this.companyName = builder.companyName;
        this.email = builder.email;
        this.nip = builder.nip;
        this.userId = builder.userId;
        this.jobOffers = builder.jobOffers;
        this.jobApplications = builder.jobApplications;
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

    public Set<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public Set<JobApplication> getJobApplications() {
        return jobApplications;
    }




    public static class EmployerBuilder {

        private Integer employerId;
        private String companyName;
        private String email;
        private String nip;
        private Integer userId;
        private Set<JobOffer> jobOffers = new HashSet<>();
        private Set<JobApplication> jobApplications = new HashSet<>();

        public EmployerBuilder employerId(Integer employerId) {
            this.employerId = employerId;
            return this;
        }

        public EmployerBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public EmployerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployerBuilder nip(String nip) {
            this.nip = nip;
            return this;
        }

        public EmployerBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public EmployerBuilder jobOffers(Set<JobOffer> jobOffers) {
            this.jobOffers = jobOffers;
            return this;
        }

        public EmployerBuilder jobApplications(Set<JobApplication> jobApplications) {
            this.jobApplications = jobApplications;
            return this;
        }

        public Employer build() {
            return new Employer(this);
        }
    }
}
