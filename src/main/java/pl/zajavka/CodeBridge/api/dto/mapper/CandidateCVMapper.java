package pl.zajavka.CodeBridge.api.dto.mapper;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.CandidateCVDTO;
import pl.zajavka.CodeBridge.domain.Candidate;

@Component
public interface CandidateCVMapper {

    CandidateCVDTO mapToDto(Candidate candidate);

}
