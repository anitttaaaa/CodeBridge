package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.domain.Employer;

@Mapper(componentModel = "spring")
public interface EmployerMapper {

    @Mapping(target = "jobOffers", ignore = true)
    @Mapping(target = "jobApplications", ignore = true)
    EmployerDTO mapToDto(Employer employer);

    @Mapping(target = "jobApplications", ignore = true)
    Employer mapToDomain(EmployerDTO employerDTO);
}
