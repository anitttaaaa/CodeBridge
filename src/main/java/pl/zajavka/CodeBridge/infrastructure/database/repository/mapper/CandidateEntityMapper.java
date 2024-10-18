package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEntityMapper {

    @Mapping(source = "profilePhoto", target = "profilePhoto")
    Candidate mapFromEntity(CandidateEntity candidateEntity);
    @Mapping(source = "profilePhoto", target = "profilePhoto")
    CandidateEntity mapToEntity (Candidate candidate);



}
