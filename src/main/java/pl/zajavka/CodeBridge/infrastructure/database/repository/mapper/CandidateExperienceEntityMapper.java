package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateExperienceEntityMapper {

    default CandidateExperience mapFromEntity(CandidateExperienceEntity candidateExperienceEntity) {
        // Tworzymy obiekt CandidateExperience za pomocą konstruktora
        return new CandidateExperience(
                candidateExperienceEntity.getCandidateExperienceId(),
                candidateExperienceEntity.getCompanyName(),
                candidateExperienceEntity.getCandidatePosition(),
                candidateExperienceEntity.getDescription(),
                candidateExperienceEntity.getFromDate(),
                candidateExperienceEntity.getToDate(),
                candidateExperienceEntity.getCandidateId()
        );
    }

    // Mapowanie z CandidateExperience do CandidateExperienceEntity za pomocą konstruktora
    default CandidateExperienceEntity mapToEntity(CandidateExperience candidateExperience) {
        // Tworzymy obiekt CandidateExperienceEntity za pomocą konstruktora
        return new CandidateExperienceEntity(
                candidateExperience.getCandidateExperienceId(),
                candidateExperience.getCompanyName(),
                candidateExperience.getCandidatePosition(),
                candidateExperience.getDescription(),
                candidateExperience.getFromDate(),
                candidateExperience.getToDate(),
                candidateExperience.getCandidateId()
        );
    }


}
