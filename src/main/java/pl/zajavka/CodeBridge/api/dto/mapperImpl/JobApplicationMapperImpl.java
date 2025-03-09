package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.JobApplicationMapper;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;

@Component
public class JobApplicationMapperImpl implements JobApplicationMapper {

    @Override
    public JobApplicationDTO mapToDto(JobApplication jobApplication) {
        JobOfferDTO jobOfferDTO = mapJobOfferToDto(jobApplication.getJobOffer());
        EmployerDTO employerDTO = mapEmployerToDto(jobApplication.getEmployer());
        CandidateDTO candidateDTO = mapCandidateToDto(jobApplication.getCandidate());

        return new JobApplicationDTO.JobApplicationDTOBuilder()
                .applicationId(jobApplication.getApplicationId())
                .jobOffer(jobOfferDTO)
                .employer(employerDTO)
                .candidate(candidateDTO)
                .applicationStatus(jobApplication.getApplicationStatus())
                .build();
    }

    @Override
    public JobApplication mapToDomain(JobApplicationDTO jobApplicationDTO) {
        JobOffer jobOffer = mapJobOfferFromDto(jobApplicationDTO.getJobOffer());
        Employer employer = mapEmployerFromDto(jobApplicationDTO.getEmployer());
        Candidate candidate = mapCandidateFromDto(jobApplicationDTO.getCandidate());

        return new JobApplication.JobApplicationBuilder()
                .applicationId(jobApplicationDTO.getApplicationId())
                .jobOffer(jobOffer)
                .employer(employer)
                .candidate(candidate)
                .jobApplicationStatus(jobApplicationDTO.getApplicationStatus())
                .build();
    }

    @Override
    public JobOfferDTO mapJobOfferToDto(JobOffer jobOffer) {
        return new JobOfferDTO.JobOfferDTOBuilder()
                .jobOfferId(jobOffer.getJobOfferId())
                .jobOfferTitle(jobOffer.getJobOfferTitle())
                .description(jobOffer.getDescription())
                .techSpecialization(jobOffer.getTechSpecialization())
                .workType(jobOffer.getWorkType())
                .city(jobOffer.getCity())
                .experience(jobOffer.getExperience())
                .salary(jobOffer.getSalary())
                .mustHaveSkills(jobOffer.getMustHaveSkills())
                .niceToHaveSkills(jobOffer.getNiceToHaveSkills())
                .build();
    }

    @Override
    public EmployerDTO mapEmployerToDto(Employer employer) {
        return new EmployerDTO.Builder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .build();
    }

    @Override
    public CandidateDTO mapCandidateToDto(Candidate candidate) {
        return new CandidateDTO.Builder()
                .candidateId(candidate.getCandidateId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .techSpecialization(candidate.getTechSpecialization())
                .candidateSkills(candidate.getCandidateSkills())
                .build();
    }

    @Override
    public JobOffer mapJobOfferFromDto(JobOfferDTO jobOfferDTO) {
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
    public Employer mapEmployerFromDto(EmployerDTO employerDTO) {
        return new Employer.EmployerBuilder()
                .employerId(employerDTO.getEmployerId())
                .companyName(employerDTO.getCompanyName())
                .build();
    }

    @Override
    public Candidate mapCandidateFromDto(CandidateDTO candidateDTO) {
        return new Candidate.Builder()
                .candidateId(candidateDTO.getCandidateId())
                .name(candidateDTO.getName())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .phone(candidateDTO.getPhone())
                .techSpecialization(candidateDTO.getTechSpecialization())
                .candidateSkills(candidateDTO.getCandidateSkills())
                .build();
    }
}
