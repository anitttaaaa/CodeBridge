package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEducationEntityMapper {

    default CandidateEducation mapFromEntity(CandidateEducationEntity candidateEducationEntity) {
        if (candidateEducationEntity == null) {
            return null;
        }
        return new CandidateEducation(
                candidateEducationEntity.getCandidateEducationId(),
                candidateEducationEntity.getInstitution(),
                candidateEducationEntity.getDegree(),
                candidateEducationEntity.getFieldOfStudy(),
                candidateEducationEntity.getFromDate(),
                candidateEducationEntity.getToDate(),
                candidateEducationEntity.getCandidateId()
        );
    }

    // Ręczne mapowanie z CandidateEducation na CandidateEducationEntity
    default CandidateEducationEntity mapToEntity(CandidateEducation candidateEducation) {
        if (candidateEducation == null) {
            return null;
        }
        return new CandidateEducationEntity(
                candidateEducation.getCandidateEducationId(),
                candidateEducation.getInstitution(),
                candidateEducation.getDegree(),
                candidateEducation.getFieldOfStudy(),
                candidateEducation.getFromDate(),
                candidateEducation.getToDate(),
                candidateEducation.getCandidateId()
        );
    }
}
