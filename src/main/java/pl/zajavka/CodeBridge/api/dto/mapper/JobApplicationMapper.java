package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.JobApplicationDTO;
import pl.zajavka.CodeBridge.domain.JobApplication;

public interface JobApplicationMapper {

    JobApplicationDTO mapToDto(JobApplication jobApplication);

    JobApplication mapToDomain(JobApplicationDTO jobApplicationDTO);

}