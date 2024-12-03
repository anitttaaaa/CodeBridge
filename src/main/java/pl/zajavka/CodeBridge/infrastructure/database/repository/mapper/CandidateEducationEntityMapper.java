package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEducationEntityMapper {

    CandidateEducation mapFromEntity(CandidateEducationEntity candidateEducationEntity);


    CandidateEducationEntity mapToEntity(CandidateEducation candidateEducation);

}
