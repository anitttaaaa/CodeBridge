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

                .jobOfferTitle(jobApplication.getJobOffer().getJobOfferTitle())
                .jobOfferDescription(jobApplication.getJobOffer().getDescription())
                .jobOfferTechSpecialization(jobApplication.getJobOffer().getTechSpecialization())
                .jobOfferWorkType(jobApplication.getJobOffer().getWorkType())
                .jobOfferCity(jobApplication.getJobOffer().getCity())
                .jobOfferExperience(jobApplication.getJobOffer().getExperience())
                .jobOfferSalary(jobApplication.getJobOffer().getSalary())
                .jobOfferMustHaveSkills(jobApplication.getJobOffer().getMustHaveSkills())
                .jobOfferNiceToHaveSkills(jobApplication.getJobOffer().getNiceToHaveSkills())

                .companyName(jobApplication.getEmployer().getCompanyName())

                .candidateEmail(jobApplication.getCandidate().getEmail())
                .candidateName(jobApplication.getCandidate().getName())
                .candidateSurname(jobApplication.getCandidate().getSurname())
                .candidatePhone(jobApplication.getCandidate().getPhone())
                .candidateTechSpecialization(jobApplication.getCandidate().getTechSpecialization())
                .candidateSkills(jobApplication.getCandidate().getCandidateSkills())

                .applicationStatusEnum(jobApplication.getApplicationStatusEnum())
                .build();
    }


    @Override
    public JobApplication mapToDomain(JobApplicationDTO jobApplicationDTO) {


        Candidate candidate = new Candidate.Builder()
                .name(jobApplicationDTO.getCandidateName())
                .surname(jobApplicationDTO.getCandidateSurname())
                .email(jobApplicationDTO.getCandidateEmail())
                .phone(jobApplicationDTO.getCandidatePhone())
                .techSpecialization(jobApplicationDTO.getCandidateTechSpecialization())
                .candidateSkills(jobApplicationDTO.getCandidateSkills())
                .build();

        Employer employer = new Employer.EmployerBuilder()
                .companyName(jobApplicationDTO.getCompanyName())
                .build();

        JobOffer jobOffer = new JobOffer.JobOfferBuilder()
                .jobOfferId(jobApplicationDTO.getJobOfferId())
                .build();

        return new JobApplication.JobApplicationBuilder()
                .applicationId(jobApplicationDTO.getApplicationId())
                .jobOffer(jobOffer)
                .employer(employer)
                .candidate(candidate)
                .jobApplicationStatus(jobApplicationDTO.getApplicationStatusEnum())
                .build();
    }


}
