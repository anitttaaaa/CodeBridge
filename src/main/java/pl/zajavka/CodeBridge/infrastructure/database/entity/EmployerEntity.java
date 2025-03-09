package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employer")
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

    public EmployerEntity() {
    }

    public EmployerEntity(Integer employerId, String companyName, String email,
                          String nip, Integer userId) {
        this.employerId = employerId;
        this.companyName = companyName;
        this.email = email;
        this.nip = nip;
        this.userId = userId;
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


    public static EmployerEntity.Builder builder() {

        return new EmployerEntity.Builder();
    }


    public static class Builder {

        private Integer employerId;
        private String companyName;
        private String email;
        private String nip;
        private Integer userId;
        private Set<JobOfferEntity> jobOffers;
        private Set<JobApplicationEntity> jobApplications;

        public Builder employerId(Integer employerId) {
            this.employerId = employerId;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder nip(String nip) {
            this.nip = nip;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder jobOffers(Set<JobOfferEntity> jobOffers) {
            this.jobOffers = jobOffers;
            return this;
        }

        public Builder jobApplications(Set<JobApplicationEntity> jobApplications) {
            this.jobApplications = jobApplications;
            return this;
        }

        public EmployerEntity build() {
            return new EmployerEntity(employerId, companyName, email, nip, userId, jobOffers, jobApplications);
        }
    }
}
