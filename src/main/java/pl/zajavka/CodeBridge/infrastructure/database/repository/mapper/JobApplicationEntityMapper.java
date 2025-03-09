package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;

public interface JobApplicationEntityMapper {

    JobApplicationEntity mapToEntity(JobApplication jobApplication);

    JobApplication mapToDomain(JobApplicationEntity jobApplicationEntity);

}
