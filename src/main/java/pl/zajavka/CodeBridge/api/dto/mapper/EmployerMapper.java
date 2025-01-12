package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.domain.Employer;

@Mapper(componentModel = "spring")
public interface EmployerMapper {

    EmployerDTO mapToDto(Employer employer);

    Employer mapToDomain(EmployerDTO employerDTO);
}
