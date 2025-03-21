package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import jakarta.persistence.criteria.CriteriaBuilder;
import pl.zajavka.CodeBridge.domain.CandidateProject;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateProjectEntity;

public interface CandidateProjectEntityMapper {

    CandidateProject mapToDomain(CandidateProjectEntity entity);

    CandidateProjectEntity mapToEntity(CandidateProject domain, Integer candidateId);

}
