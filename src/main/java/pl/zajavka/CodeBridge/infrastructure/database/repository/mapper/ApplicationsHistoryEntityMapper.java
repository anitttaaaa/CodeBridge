package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.infrastructure.database.entity.ApplicationsHistoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ApplicationsHistoryEntityMapper {

    ApplicationsHistory mapToDomain(ApplicationsHistoryEntity applicationsHistoryEntity);



    ApplicationsHistoryEntity mapToEntity(ApplicationsHistory applicationsHistory);

}
