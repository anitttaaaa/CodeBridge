package pl.zajavka.CodeBridge.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.domain.JobApplication;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {

    JobApplicationDTO mapToDto(JobApplication jobApplication);
    JobApplication mapToDomain(JobApplicationDTO jobApplicationDTO);
}
