package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateExperienceDTO;
import pl.zajavka.CodeBridge.domain.CandidateExperience;

@Mapper(componentModel = "spring")
public interface CandidateExperienceMapper {

    default CandidateExperienceDTO mapToDto(CandidateExperience candidateExperience) {
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

    default CandidateExperience mapToDomain(CandidateExperienceDTO candidateExperienceDTO) {
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
