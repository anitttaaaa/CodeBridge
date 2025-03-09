package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateExperienceEntityMapper;

@Component
public class CandidateExperienceEntityMapperImpl implements CandidateExperienceEntityMapper {

    @Override
    public CandidateExperience mapFromEntity(CandidateExperienceEntity candidateExperienceEntity) {
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

    @Override
    public CandidateExperienceEntity mapToEntity(CandidateExperience candidateExperience) {
        if (candidateExperience == null) {
            return null;
        }

        return new CandidateExperienceEntity.Builder()
                .candidateExperienceId(candidateExperience.getCandidateExperienceId())
                .companyName(candidateExperience.getCompanyName())
                .candidatePosition(candidateExperience.getCandidatePosition())
                .description(candidateExperience.getDescription())
                .fromDate(candidateExperience.getFromDate())
                .toDate(candidateExperience.getToDate())
                .candidateId(candidateExperience.getCandidateId())
                .build();
    }
}
