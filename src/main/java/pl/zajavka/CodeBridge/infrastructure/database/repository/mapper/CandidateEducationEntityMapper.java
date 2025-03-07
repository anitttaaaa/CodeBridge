package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEducationEntityMapper {

    CandidateEducation mapFromEntity(CandidateEducationEntity candidateEducationEntity);

    CandidateEducationEntity mapToEntity(CandidateEducation candidateEducation);

}
