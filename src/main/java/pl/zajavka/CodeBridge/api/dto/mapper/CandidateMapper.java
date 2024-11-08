package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.CodeBridge.api.dto.CandidatePortalDTO;
import pl.zajavka.CodeBridge.domain.Candidate;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidatePortalDTO candidateToDto(Candidate candidate);
    Candidate candidateToDomain(CandidatePortalDTO candidatePortalDTO);





}
