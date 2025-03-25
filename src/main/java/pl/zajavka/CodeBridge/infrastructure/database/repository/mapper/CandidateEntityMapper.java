package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;

public interface CandidateEntityMapper {

    CandidateEntity mapCandidateToEntity(Candidate candidate);

    Candidate mapCandidateEntityToDomain(CandidateEntity candidateEntity);


}
