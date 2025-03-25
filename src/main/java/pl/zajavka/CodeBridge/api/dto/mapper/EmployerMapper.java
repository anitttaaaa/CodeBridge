package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.domain.Employer;

public interface EmployerMapper {

    EmployerDTO mapToDto(Employer employer);

    Employer mapToDomain(EmployerDTO employerDTO);

}
