package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.Set;

public interface EmployerMapper {

    EmployerDTO mapToDto(Employer employer);

    Employer mapToDomain(EmployerDTO employerDTO);

    Set<JobOfferDTO> mapJobOffersToDTO(Set<JobOffer> jobOffers);

    Set<JobApplicationDTO> mapJobApplicationsToDTO(Set<JobApplication> jobApplications);

    Set<JobApplication> mapToJobApplications(Set<JobApplicationDTO> jobApplicationsDTO);

    Set<JobOffer> mapToJobOffers(Set<JobOfferDTO> jobOffersDTO);

    JobOfferDTO mapJobOfferToDTO(JobOffer jobOffer);

    JobOffer mapToJobOffer(JobOfferDTO jobOfferDTO);

    JobApplicationDTO mapJobApplicationToDTO(JobApplication jobApplication);

    JobApplication mapToJobApplication(JobApplicationDTO jobApplicationDTO);
}
