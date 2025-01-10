package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobApplicationEntityMapper {

    JobApplication mapFromEntity(JobApplicationEntity jobApplicationEntity);

    JobApplicationEntity mapToEntity(JobApplication jobApplication);


}
