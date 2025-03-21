package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;

public interface CandidateMapper {

    CandidateDTO mapToDto(Candidate candidate);

    Candidate mapToDomain(CandidateDTO candidateDTO);



}
