package pl.zajavka.CodeBridge.api.dto.mapper;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.domain.*;

import java.util.List;
@Component
public interface CandidateCVMapper {

    CandidateCVDTO mapToDto(Candidate candidate);

}
