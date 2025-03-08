package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.CandidateEducationDTO;
import pl.zajavka.CodeBridge.domain.CandidateEducation;

@Mapper(componentModel = "spring")
public interface CandidateEducationMapper {


    default CandidateEducationDTO mapToDto(CandidateEducation candidateEducation) {
        return new CandidateEducationDTO.Builder()
                .candidateEducationId(candidateEducation.getCandidateEducationId())
                .institution(candidateEducation.getInstitution())
                .degree(candidateEducation.getDegree())
                .fieldOfStudy(candidateEducation.getFieldOfStudy())
                .fromDate(candidateEducation.getFromDate())
                .toDate(candidateEducation.getToDate())
                .candidateId(candidateEducation.getCandidateId())
                .build();
    }

    default CandidateEducation mapToDomain(CandidateEducationDTO candidateEducationDTO) {
        return new CandidateEducation.Builder()
                .candidateEducationId(candidateEducationDTO.getCandidateEducationId())
                .institution(candidateEducationDTO.getInstitution())
                .degree(candidateEducationDTO.getDegree())
                .fieldOfStudy(candidateEducationDTO.getFieldOfStudy())
                .fromDate(candidateEducationDTO.getFromDate())
                .toDate(candidateEducationDTO.getToDate())
                .candidateId(candidateEducationDTO.getCandidateId())
                .build();
    }
}
