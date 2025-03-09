package pl.zajavka.CodeBridge.api.dto;

import java.util.Objects;
import java.util.Set;

public class EmployerDTO {

    private final Integer employerId;
    private final String companyName;
    private final String email;
    private final String nip;
    private final Integer userId;
    private final Set<JobOfferDTO> jobOffers;
    private final Set<JobApplicationDTO> jobApplications;

    private EmployerDTO(Builder builder) {
        this.employerId = builder.employerId;
        this.companyName = builder.companyName;
        this.email = builder.email;
        this.nip = builder.nip;
        this.userId = builder.userId;
        this.jobOffers = builder.jobOffers;
        this.jobApplications = builder.jobApplications;
    }

    public static class Builder {
        private Integer employerId;
        private String companyName;
        private String email;
        private String nip;
        private Integer userId;
        private Set<JobOfferDTO> jobOffers;
        private Set<JobApplicationDTO> jobApplications;

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

        public Builder jobOffers(Set<JobOfferDTO> jobOffers) {
            this.jobOffers = jobOffers;
            return this;
        }

        public Builder jobApplications(Set<JobApplicationDTO> jobApplications) {
            this.jobApplications = jobApplications;
            return this;
        }

        public EmployerDTO build() {
            return new EmployerDTO(this);
        }
    }

    public static Builder builder() {
        return new Builder();
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

    public Set<JobOfferDTO> getJobOffers() {
        return jobOffers;
    }

    public Set<JobApplicationDTO> getJobApplications() {
        return jobApplications;
    }

    @Override
    public String toString() {
        return "EmployerDTO{" +
                "employerId=" + employerId +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", nip='" + nip + '\'' +
                ", userId=" + userId +
                ", jobOffers=" + jobOffers +
                ", jobApplications=" + jobApplications +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployerDTO that = (EmployerDTO) o;
        return Objects.equals(employerId, that.employerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employerId);
    }
}
