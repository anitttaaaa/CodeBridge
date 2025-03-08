package pl.zajavka.CodeBridge.infrastructure.database.repository;

import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.ApplicationsHistoryDAO;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.infrastructure.database.entity.ApplicationsHistoryEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.ApplicationsHistoryJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.ApplicationsHistoryEntityMapper;

@Repository
public class ApplicationsHistoryRepository implements ApplicationsHistoryDAO {

    ApplicationsHistoryEntityMapper applicationsHistoryEntityMapper;
    ApplicationsHistoryJpaRepository applicationsHistoryJpaRepository;

    public ApplicationsHistoryRepository(ApplicationsHistoryEntityMapper applicationsHistoryEntityMapper,
                                         ApplicationsHistoryJpaRepository applicationsHistoryJpaRepository) {
        this.applicationsHistoryEntityMapper = applicationsHistoryEntityMapper;
        this.applicationsHistoryJpaRepository = applicationsHistoryJpaRepository;
    }

    @Override
    public void saveInHistory(ApplicationsHistory jobApplicationAccepted) {

        ApplicationsHistoryEntity jobApplicationAddToHistory = applicationsHistoryEntityMapper.mapToEntity(jobApplicationAccepted);
        applicationsHistoryJpaRepository.saveAndFlush(jobApplicationAddToHistory);
    }
}
