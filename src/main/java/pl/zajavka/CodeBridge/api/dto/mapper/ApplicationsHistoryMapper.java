package pl.zajavka.CodeBridge.api.dto.mapper;

import pl.zajavka.CodeBridge.api.dto.ApplicationsHistoryDTO;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;

public interface ApplicationsHistoryMapper {

    ApplicationsHistoryDTO mapToDto(ApplicationsHistory applicationsHistory);

    ApplicationsHistory mapToDomain(ApplicationsHistoryDTO applicationsHistoryDTO);


}
