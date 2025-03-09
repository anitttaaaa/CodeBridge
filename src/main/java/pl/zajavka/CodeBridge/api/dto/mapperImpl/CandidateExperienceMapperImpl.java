package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateExperienceMapper;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

@Component
public class CandidateExperienceMapperImpl implements CandidateExperienceMapper {

    @Override
    public CandidateExperienceDTO mapToDto(CandidateExperience candidateExperience) {
        if (candidateExperience == null) {
            return null;
        }

        return new CandidateExperienceDTO.Builder()
                .candidateExperienceId(candidateExperience.getCandidateExperienceId())
                .companyName(candidateExperience.getCompanyName())
                .candidatePosition(candidateExperience.getCandidatePosition())
                .description(candidateExperience.getDescription())
                .fromDate(candidateExperience.getFromDate())
                .toDate(candidateExperience.getToDate())
                .candidateId(candidateExperience.getCandidateId())
                .build();
    }

    @Override
    public CandidateExperience mapToDomain(CandidateExperienceDTO candidateExperienceDTO) {
        if (candidateExperienceDTO == null) {
            return null;
        }

        return new CandidateExperience.Builder()
                .candidateExperienceId(candidateExperienceDTO.getCandidateExperienceId())
                .companyName(candidateExperienceDTO.getCompanyName())
                .candidatePosition(candidateExperienceDTO.getCandidatePosition())
                .description(candidateExperienceDTO.getDescription())
                .fromDate(candidateExperienceDTO.getFromDate())
                .toDate(candidateExperienceDTO.getToDate())
                .candidateId(candidateExperienceDTO.getCandidateId())
                .build();
    }
}
