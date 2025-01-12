package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.domain.JobApplication;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {

    JobApplicationDTO mapToDto(JobApplication jobApplication);


    @Mapping(target = "employer", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    @Mapping(target = "applicationStatus", ignore = true)
    JobApplication mapToDomain(JobApplicationDTO jobApplicationDTO);
}
