package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateExperienceEntityMapper {

    default CandidateExperience mapFromEntity(CandidateExperienceEntity candidateExperienceEntity) {
        if (candidateExperienceEntity == null) {
            return null;
        }

        return new CandidateExperience.Builder()
                .candidateExperienceId(candidateExperienceEntity.getCandidateExperienceId())
                .companyName(candidateExperienceEntity.getCompanyName())
                .candidatePosition(candidateExperienceEntity.getCandidatePosition())
                .description(candidateExperienceEntity.getDescription())
                .fromDate(candidateExperienceEntity.getFromDate())
                .toDate(candidateExperienceEntity.getToDate())
                .candidateId(candidateExperienceEntity.getCandidateId())
                .build();
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
