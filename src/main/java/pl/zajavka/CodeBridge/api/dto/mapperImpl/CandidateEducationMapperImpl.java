package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateEducationDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateEducationMapper;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

@Component
public class CandidateEducationMapperImpl implements CandidateEducationMapper {

    @Override
    public CandidateEducationDTO mapToDto(CandidateEducation candidateEducation) {
        if (candidateEducation == null) {
            return null;
        }

        return new CandidateEducationDTO(
                candidateEducation.getCandidateEducationId(),
                candidateEducation.getInstitution(),
                candidateEducation.getDegree(),
                candidateEducation.getFieldOfStudy(),
                candidateEducation.getFromDate(),
                candidateEducation.getToDate()
        );
    }

    @Override
    public CandidateEducation mapToDomain(CandidateEducationDTO candidateEducationDTO) {
        if (candidateEducationDTO == null) {
            return null;
        }

        return new CandidateEducation.Builder()
                .candidateEducationId(candidateEducationDTO.getCandidateEducationId())
                .institution(candidateEducationDTO.getInstitution())
                .degree(candidateEducationDTO.getDegree())
                .fieldOfStudy(candidateEducationDTO.getFieldOfStudy())
                .fromDate(candidateEducationDTO.getFromDate())
                .toDate(candidateEducationDTO.getToDate())
                .build();
    }
}
