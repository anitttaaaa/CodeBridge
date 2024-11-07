package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.JobOfferToDatabase;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobOfferEntityMapper {

    JobOfferEntity mapToEntity(JobOfferToDatabase jobOfferToDatabase);


}
