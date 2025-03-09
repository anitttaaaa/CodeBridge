package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.ApplicationsHistoryEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;

public interface ApplicationsHistoryEntityMapper {

    ApplicationsHistory mapToDomain(ApplicationsHistoryEntity entity);

    JobOffer mapJobOffer(JobOfferEntity jobOfferEntity);

    Employer mapEmployer(EmployerEntity employerEntity);

    Candidate mapCandidate(CandidateEntity candidateEntity);

    ApplicationsHistoryEntity mapToEntity(ApplicationsHistory applicationsHistory);

    JobOfferEntity mapJobOfferEntity(JobOffer jobOffer);

    EmployerEntity mapEmployerEntity(Employer employer);

    CandidateEntity mapCandidateEntity(Candidate candidate);
}
