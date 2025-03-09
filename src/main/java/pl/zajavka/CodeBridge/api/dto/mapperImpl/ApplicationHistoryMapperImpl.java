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
    public ApplicationsHistoryDTO mapToDto(ApplicationsHistory applicationsHistory) {
        return new ApplicationsHistoryDTO.Builder()
                .applicationHistoryId(applicationsHistory.getApplicationHistoryId())
                .jobOffer(mapJobOffer(applicationsHistory.getJobOffer()))
                .employer(mapEmployer(applicationsHistory.getEmployer()))
                .candidate(mapCandidate(applicationsHistory.getCandidate()))
                .applicationStatus(applicationsHistory.getApplicationStatus())
                .build();
    }

    @Override
    public ApplicationsHistory mapToDomain(ApplicationsHistoryDTO applicationsHistoryDTO) {
        return new ApplicationsHistory.Builder()
                .applicationHistoryId(applicationsHistoryDTO.getApplicationHistoryId())
                .jobOffer(mapJobOfferToDomain(applicationsHistoryDTO.getJobOffer()))
                .employer(mapEmployerToDomain(applicationsHistoryDTO.getEmployer()))
                .candidate(mapCandidateToDomain(applicationsHistoryDTO.getCandidate()))
                .applicationStatus(applicationsHistoryDTO.getApplicationStatus())
                .build();
    }

    @Override
    public JobOfferDTO mapJobOffer(JobOffer jobOffer) {
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
    public JobOffer mapJobOfferToDomain(JobOfferDTO jobOfferDTO) {
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
    public EmployerDTO mapEmployer(Employer employer) {
        return new EmployerDTO.Builder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .email(employer.getEmail())
                .nip(employer.getNip())
                .userId(employer.getUserId())
                .build();
    }

    @Override
    public Employer mapEmployerToDomain(EmployerDTO employerDTO) {
        return new Employer.EmployerBuilder()
                .employerId(employerDTO.getEmployerId())
                .companyName(employerDTO.getCompanyName())
                .email(employerDTO.getEmail())
                .nip(employerDTO.getNip())
                .userId(employerDTO.getUserId())
                .build();
    }

    @Override
    public CandidateDTO mapCandidate(Candidate candidate) {
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
    public Candidate mapCandidateToDomain(CandidateDTO candidateDTO) {
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

