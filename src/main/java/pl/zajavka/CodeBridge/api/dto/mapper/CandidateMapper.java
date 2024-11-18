package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.domain.Candidate;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateDTO candidateToDto(Candidate candidate);
    @Mapping(target = "candidateExperiences", ignore = true)
    Candidate candidateToDomain(CandidateDTO candidateDTO);





}
