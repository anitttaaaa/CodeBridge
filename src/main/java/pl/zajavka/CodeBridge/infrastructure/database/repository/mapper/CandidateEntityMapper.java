package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.*;
import pl.zajavka.CodeBridge.infrastructure.database.entity.*;

import java.util.List;

public interface CandidateEntityMapper {

    CandidateEntity mapCandidateToEntity(Candidate candidate);

    Candidate mapCandidateEntityToDomain(CandidateEntity candidateEntity);


}
