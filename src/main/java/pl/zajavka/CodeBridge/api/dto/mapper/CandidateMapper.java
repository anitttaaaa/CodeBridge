package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.domain.Candidate;

public interface CandidateMapper {

    CandidateDTO mapToDto(Candidate candidate);

    Candidate mapToDomain(CandidateDTO candidateDTO);


}
