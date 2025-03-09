package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.ApplicationsHistoryDTO;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.JobOfferDTO;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;

public interface ApplicationsHistoryMapper {

    ApplicationsHistoryDTO mapToDto(ApplicationsHistory applicationsHistory);

    ApplicationsHistory mapToDomain(ApplicationsHistoryDTO applicationsHistoryDTO);

    JobOfferDTO mapJobOffer(JobOffer jobOffer);
    JobOffer mapJobOfferToDomain(JobOfferDTO jobOfferDTO);

    EmployerDTO mapEmployer(Employer employer);
    Employer mapEmployerToDomain(EmployerDTO employerDTO);

    CandidateDTO mapCandidate(Candidate candidate);
    Candidate mapCandidateToDomain(CandidateDTO candidateDTO);
}
