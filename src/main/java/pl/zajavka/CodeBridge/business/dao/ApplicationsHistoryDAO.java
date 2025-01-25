package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.ApplicationsHistory;

public interface ApplicationsHistoryDAO {
    void saveInHistory(ApplicationsHistory jobApplicationAccepted);

}
