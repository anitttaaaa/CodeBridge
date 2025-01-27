package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateProject;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateProjectEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateProjectEntityMapper {

    CandidateProject mapToDomain(CandidateProjectEntity candidateProjectEntity);

    @Mapping(target = "candidate.candidateId", source = "candidateId")
    CandidateProjectEntity mapToEntity(CandidateProject candidateProject);


}