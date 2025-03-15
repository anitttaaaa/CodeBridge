package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.ApplicationsHistoryDTO;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.ApplicationsHistoryMapper;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;

@Component
public class ApplicationHistoryMapperImpl implements ApplicationsHistoryMapper {

    @Override
    public ApplicationsHistory mapToDomain(ApplicationsHistoryDTO applicationsHistoryDTO) {
        return new ApplicationsHistory.Builder()
                .applicationHistoryId(applicationsHistoryDTO.getApplicationHistoryId())
                .jobOffer(mapJobOfferToDomain(applicationsHistoryDTO.getJobOffer()))
                .employer(mapEmployerToDomain(applicationsHistoryDTO.getEmployer()))
                .candidate(mapCandidateToDomain(applicationsHistoryDTO.getCandidate()))
                .applicationStatus(applicationsHistoryDTO.getApplicationStatusEnum())
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

    @Override
    public ApplicationsHistoryDTO mapToDto(ApplicationsHistory applicationsHistory) {
        return new ApplicationsHistoryDTO.Builder()
                .applicationHistoryId(applicationsHistory.getApplicationHistoryId())
                .jobOffer(mapJobOfferToDTO(applicationsHistory.getJobOffer()))
                .employer(mapEmployerToDTO(applicationsHistory.getEmployer()))
                .candidate(mapCandidateToDTO(applicationsHistory.getCandidate()))
                .applicationStatus(applicationsHistory.getApplicationStatusEnum())
                .build();
    }




    public JobOfferDTO mapJobOfferToDTO(JobOffer jobOffer) {
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

    public EmployerDTO mapEmployerToDTO(Employer employer) {
        return new EmployerDTO.Builder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .build();
    }



    public CandidateDTO mapCandidateToDTO(Candidate candidate) {
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


}

