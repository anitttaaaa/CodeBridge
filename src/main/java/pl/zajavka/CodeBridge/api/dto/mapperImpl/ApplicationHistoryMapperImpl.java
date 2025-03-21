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

        Candidate candidate = new Candidate.Builder()
                .name(applicationsHistoryDTO.getCandidateName())
                .surname(applicationsHistoryDTO.getCandidateSurname())
                .email(applicationsHistoryDTO.getCandidateEmail())
                .phone(applicationsHistoryDTO.getCandidatePhone())
                .techSpecialization(applicationsHistoryDTO.getCandidateTechSpecialization())
                .candidateSkills(applicationsHistoryDTO.getCandidateSkills())
                .build();

        Employer employer = new Employer.EmployerBuilder()
                .companyName(applicationsHistoryDTO.getCompanyName())
                .build();

        JobOffer jobOffer = new JobOffer.JobOfferBuilder()
                .jobOfferId(applicationsHistoryDTO.getJobOfferId())
                .build();

        return new ApplicationsHistory.Builder()
                .applicationHistoryId(applicationsHistoryDTO.getApplicationHistoryId())
                .jobOffer(jobOffer)
                .employer(employer)
                .candidate(candidate)
                .applicationStatus(applicationsHistoryDTO.getApplicationStatusEnum())
                .build();
    }

    @Override
    public ApplicationsHistoryDTO mapToDto(ApplicationsHistory applicationsHistory) {
        return new ApplicationsHistoryDTO.Builder()
                .applicationHistoryId(applicationsHistory.getApplicationHistoryId())

                .jobOfferTitle(applicationsHistory.getJobOffer().getJobOfferTitle())
                .jobOfferDescription(applicationsHistory.getJobOffer().getDescription())
                .jobOfferTechSpecialization(applicationsHistory.getJobOffer().getTechSpecialization())
                .jobOfferWorkType(applicationsHistory.getJobOffer().getWorkType())
                .jobOfferCity(applicationsHistory.getJobOffer().getCity())
                .jobOfferExperience(applicationsHistory.getJobOffer().getExperience())
                .jobOfferSalary(applicationsHistory.getJobOffer().getSalary())
                .jobOfferMustHaveSkills(applicationsHistory.getJobOffer().getMustHaveSkills())
                .jobOfferNiceToHaveSkills(applicationsHistory.getJobOffer().getNiceToHaveSkills())

                .companyName(applicationsHistory.getEmployer().getCompanyName())

                .candidateEmail(applicationsHistory.getCandidate().getEmail())
                .candidateName(applicationsHistory.getCandidate().getName())
                .candidateSurname(applicationsHistory.getCandidate().getSurname())
                .candidatePhone(applicationsHistory.getCandidate().getPhone())
                .candidateTechSpecialization(applicationsHistory.getCandidate().getTechSpecialization())
                .candidateSkills(applicationsHistory.getCandidate().getCandidateSkills())

                .applicationStatusEnum(applicationsHistory.getApplicationStatusEnum())
                .build();
    }

}

