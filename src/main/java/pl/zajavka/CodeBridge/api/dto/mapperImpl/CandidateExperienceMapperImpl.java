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

        return new CandidateExperienceDTO(
                candidateExperience.getCandidateExperienceId(),
                candidateExperience.getCompanyName(),
                candidateExperience.getCandidatePosition(),
                candidateExperience.getDescription(),
                candidateExperience.getFromDate(),
                candidateExperience.getToDate(),
                candidateExperience.getCandidateId()
        );
    }

    @Override
    public CandidateExperience mapToDomain(CandidateExperienceDTO candidateExperienceDTO) { if (candidateExperienceDTO == null) {
        System.out.println("DEBUG: candidateExperienceDTO jest NULL!");
        return null;
    }

        System.out.println("DEBUG: candidateExperienceDTO -> " + candidateExperienceDTO);

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
