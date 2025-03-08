package pl.zajavka.CodeBridge.api.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public class EmployerDTO {

    private Integer employerId;
    private String companyName;
    private String email;
    private String nip;
    private Integer userId;

    private Set<JobOfferDTO> jobOffers;
    private Set<JobOfferDTO> jobApplications;


    public EmployerDTO() {
    }

    public EmployerDTO(Integer employerId, String companyName, String email,
                       String nip, Integer userId, Set<JobOfferDTO> jobOffers,
                       Set<JobOfferDTO> jobApplications) {
        this.employerId = employerId;
        this.companyName = companyName;
        this.email = email;
        this.nip = nip;
        this.userId = userId;
        this.jobOffers = jobOffers;
        this.jobApplications = jobApplications;
    }

    public EmployerDTO(Integer employerId, String companyName, String email, String nip, Integer userId) {
        this.employerId = employerId;
        this.companyName = companyName;
        this.email = email;
        this.nip = nip;
        this.userId = userId;
    }

    public EmployerDTO(Integer employerId, String companyName) {
        this.employerId = employerId;
        this.companyName = companyName;
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

    public Set<JobOfferDTO> getJobApplications() {
        return jobApplications;
    }
}
