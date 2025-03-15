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

        return new JobApplicationDTO.Builder()
                .applicationId(jobApplication.getApplicationId())
                .jobOffer(mapJobOfferToDto(jobApplication.getJobOffer()))
                .employer(mapEmployerToDto(jobApplication.getEmployer()))
                .candidate(mapCandidateToDto(jobApplication.getCandidate()))
                .applicationStatusEnum(jobApplication.getApplicationStatusEnum())
                .build();
    }

    public JobOfferDTO mapJobOfferToDto(JobOffer jobOffer) {
        return new JobOfferDTO.Builder()
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



    public EmployerDTO mapEmployerToDto(Employer employer) {
        return new EmployerDTO.Builder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .build();
    }


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
    public JobApplication mapToDomain(JobApplicationDTO jobApplicationDTO) {

        return new JobApplication.JobApplicationBuilder()
                .applicationId(jobApplicationDTO.getApplicationId())
                .jobOffer(mapJobOfferToDomain(jobApplicationDTO.getJobOffer()))
                .employer(mapEmployerToDomain(jobApplicationDTO.getEmployer()))
                .candidate(mapCandidateToDomain(jobApplicationDTO.getCandidate()))
                .jobApplicationStatus(jobApplicationDTO.getApplicationStatusEnum())
                .build();
    }


    public JobOffer mapJobOfferToDomain(JobOfferDTO jobOfferDTO) {
        return new JobOffer.JobOfferBuilder()
                .jobOfferId(jobOfferDTO.getJobOfferId())
                .build();
    }


    public Employer mapEmployerToDomain(EmployerDTO employerDTO) {
        return new Employer.EmployerBuilder()
                .employerId(employerDTO.getEmployerId())
                .build();
    }


    public Candidate mapCandidateToDomain(CandidateDTO candidateDTO) {
        return new Candidate.Builder()
                .candidateId(candidateDTO.getCandidateId())
                .build();
    }
}
