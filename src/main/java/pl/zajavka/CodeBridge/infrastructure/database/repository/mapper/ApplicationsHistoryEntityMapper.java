package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.infrastructure.database.entity.ApplicationsHistoryEntity;

public interface ApplicationsHistoryEntityMapper {

    ApplicationsHistory mapToDomain(ApplicationsHistoryEntity entity);


    ApplicationsHistoryEntity mapToEntity(ApplicationsHistory applicationsHistory);


}
