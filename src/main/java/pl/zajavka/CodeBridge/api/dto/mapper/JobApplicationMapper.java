package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {
    // Mapowanie z JobApplication do JobApplicationDTO
    JobApplicationDTO mapToDto(JobApplication jobApplication);

    // Mapowanie z JobApplicationDTO do JobApplication
    default JobApplication mapToDomain(JobApplicationDTO jobApplicationDTO) {
        // Mapowanie z DTO na obiekt domenowy
        JobOffer jobOffer = mapJobOfferFromDto(jobApplicationDTO.getJobOffer());
        Employer employer = mapEmployerFromDto(jobApplicationDTO.getEmployer());
        Candidate candidate = mapCandidateFromDto(jobApplicationDTO.getCandidate());

        return new JobApplication(
                jobApplicationDTO.getApplicationId(), // applicationId
                jobOffer,   // jobOffer
                employer,   // employer
                candidate,  // candidate
                jobApplicationDTO.getApplicationStatus() // applicationStatus
        );
    }

    // Pomocnicze metody do mapowania z domenowych obiektów do DTO za pomocą konstruktorów
    default JobOfferDTO mapJobOffer(JobOffer jobOffer) {
        return new JobOfferDTO(
                jobOffer.getJobOfferId(),
                jobOffer.getJobOfferTitle(),
                jobOffer.getDescription(),
                jobOffer.getTechSpecialization(),
                jobOffer.getCity(),
                jobOffer.getWorkType(),
                jobOffer.getExperience(),
                jobOffer.getSalary(),
                jobOffer.getMustHaveSkills(),
                jobOffer.getNiceToHaveSkills()
        );
    }

    default EmployerDTO mapEmployer(Employer employer) {
        return new EmployerDTO(
                employer.getEmployerId(),
                employer.getCompanyName()
        );
    }

    default CandidateDTO mapCandidate(Candidate candidate) {
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


    // Pomocnicze metody do mapowania z DTO na obiekty domenowe za pomocą konstruktorów
    default JobOffer mapJobOfferFromDto(JobOfferDTO jobOfferDTO) {
        return new JobOffer(
                jobOfferDTO.getJobOfferId(),
                jobOfferDTO.getJobOfferTitle(),
                jobOfferDTO.getDescription(),
                jobOfferDTO.getTechSpecialization(),
                jobOfferDTO.getCity(),
                jobOfferDTO.getWorkType(),
                jobOfferDTO.getExperience(),
                jobOfferDTO.getSalary(),
                jobOfferDTO.getMustHaveSkills(),
                jobOfferDTO.getNiceToHaveSkills()
        );
    }

    default Employer mapEmployerFromDto(EmployerDTO employerDTO) {
        return new Employer(
                employerDTO.getEmployerId(),
                employerDTO.getCompanyName()
        );
    }

    default Candidate mapCandidateFromDto(CandidateDTO candidateDTO) {
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

