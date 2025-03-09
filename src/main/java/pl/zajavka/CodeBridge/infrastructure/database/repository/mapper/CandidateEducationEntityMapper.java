package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEducationEntityMapper {

    default CandidateEducation mapFromEntity(CandidateEducationEntity candidateEducationEntity) {
        if (candidateEducationEntity == null) {
            return null;
        }

        return new CandidateEducation.Builder()
                .candidateEducationId(candidateEducationEntity.getCandidateEducationId())
                .institution(candidateEducationEntity.getInstitution())
                .degree(candidateEducationEntity.getDegree())
                .fieldOfStudy(candidateEducationEntity.getFieldOfStudy())
                .fromDate(candidateEducationEntity.getFromDate())
                .toDate(candidateEducationEntity.getToDate())
                .candidateId(candidateEducationEntity.getCandidateId())
                .build();
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
