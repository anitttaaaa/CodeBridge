package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.EmployerMapper;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployerMapperImpl implements EmployerMapper {

    @Override
    public EmployerDTO mapToDto(Employer employer) {
        return new EmployerDTO.Builder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .email(employer.getEmail())
                .nip(employer.getNip())
                .userId(employer.getUserId())
                .jobOffers(mapJobOffersToDTO(employer.getJobOffers()))
                .jobApplications(mapJobApplicationsToDTO(employer.getJobApplications()))
                .build();
    }

    @Override
    public Employer mapToDomain(EmployerDTO employerDTO) {
        return new Employer.EmployerBuilder()
                .employerId(employerDTO.getEmployerId())
                .companyName(employerDTO.getCompanyName())
                .email(employerDTO.getEmail())
                .nip(employerDTO.getNip())
                .userId(employerDTO.getUserId())
                .jobOffers(mapToJobOffers(employerDTO.getJobOffers()))
                .jobApplications(mapToJobApplications(employerDTO.getJobApplications()))
                .build();
    }

    @Override
    public Set<JobOfferDTO> mapJobOffersToDTO(Set<JobOffer> jobOffers) {
        return (jobOffers == null) ? Set.of() :
                jobOffers.stream()
                        .map(this::mapJobOfferToDTO)
                        .collect(Collectors.toSet());
    }

    @Override
    public Set<JobApplicationDTO> mapJobApplicationsToDTO(Set<JobApplication> jobApplications) {
        return (jobApplications == null) ? Set.of() :
                jobApplications.stream()
                        .map(this::mapJobApplicationToDTO)
                        .collect(Collectors.toSet());
    }

    @Override
    public Set<JobApplication> mapToJobApplications(Set<JobApplicationDTO> jobApplicationsDTO) {
        return (jobApplicationsDTO == null) ? Set.of() :
                jobApplicationsDTO.stream()
                        .map(this::mapToJobApplication)
                        .collect(Collectors.toSet());
    }

    @Override
    public Set<JobOffer> mapToJobOffers(Set<JobOfferDTO> jobOffersDTO) {
        return (jobOffersDTO == null) ? Set.of() :
                jobOffersDTO.stream()
                        .map(this::mapToJobOffer)
                        .collect(Collectors.toSet());
    }

    @Override
    public JobOfferDTO mapJobOfferToDTO(JobOffer jobOffer) {
        return new JobOfferDTO(
                jobOffer.getJobOfferId(),
                jobOffer.getJobOfferTitle(),
                jobOffer.getDescription(),
                jobOffer.getTechSpecialization(),
                jobOffer.getWorkType(),
                jobOffer.getCity(),
                jobOffer.getExperience(),
                jobOffer.getSalary(),
                jobOffer.getMustHaveSkills(),
                jobOffer.getNiceToHaveSkills(),
                jobOffer.getEmployer()
                );
    }

    @Override
    public JobOffer mapToJobOffer(JobOfferDTO jobOfferDTO) {
        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferDTO.getJobOfferId())
                .jobOfferTitle(jobOfferDTO.getJobOfferTitle())
                .description(jobOfferDTO.getDescription())
                .techSpecialization(jobOfferDTO.getTechSpecialization())
                .workType(jobOfferDTO.getWorkType())
                .city(jobOfferDTO.getCity())
                .experience(jobOfferDTO.getExperience())
                .salary(jobOfferDTO.getSalary())
                .mustHaveSkills(jobOfferDTO.getMustHaveSkills())
                .niceToHaveSkills(jobOfferDTO.getNiceToHaveSkills())
                .build();
    }

    @Override
    public JobApplicationDTO mapJobApplicationToDTO(JobApplication jobApplication) {
        return new JobApplicationDTO(
                jobApplication.getApplicationId(),
                mapJobOfferToDTO(jobApplication.getJobOffer()),
                mapEmployerToDTO(jobApplication.getEmployer()),
                mapCandidateToDTO(jobApplication.getCandidate()),
                jobApplication.getApplicationStatusEnum()
        );
    }

    private CandidateDTO mapCandidateToDTO(Candidate candidate) {
        return new CandidateDTO.Builder()
                .candidateId(candidate.getCandidateId())
                .build();
    }

    public EmployerDTO mapEmployerToDTO(Employer employer) {
        return new EmployerDTO.Builder()
                .employerId(employer.getEmployerId())
                .build();
    }


    @Override
    public JobApplication mapToJobApplication(JobApplicationDTO jobApplicationDTO) {
        return new JobApplication.JobApplicationBuilder()
                .applicationId(jobApplicationDTO.getApplicationId())
                .jobOffer(mapToJobOffer(jobApplicationDTO.getJobOffer()))
                .employer(mapToEmployer(jobApplicationDTO.getEmployer()))
                .candidate(mapToCandidate(jobApplicationDTO.getCandidate()))
                .jobApplicationStatus(jobApplicationDTO.getApplicationStatusEnum())
                .build();
    }


    private Candidate mapToCandidate(CandidateDTO candidate) {
        return new Candidate.Builder()
                .candidateId(candidate.getCandidateId())
                .build();
    }

    private Employer mapToEmployer(EmployerDTO employer) {
        return new Employer.EmployerBuilder()
                .employerId(employer.getEmployerId())
                .build();

    }

}
