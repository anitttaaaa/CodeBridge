package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.JobApplicationDAO;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobApplicationJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobApplicationEntityMapper;

@Repository
@AllArgsConstructor
public class JobApplicationRepository implements JobApplicationDAO {

    JobApplicationEntityMapper jobApplicationEntityMapper;
    JobApplicationJpaRepository jobApplicationJpaRepository;
    @Override
    public void createJobApplication(JobApplication jobApplication) {


        JobApplicationEntity jobApplicationToSave = jobApplicationEntityMapper.mapToEntity(jobApplication);
        JobApplicationEntity jobApplicationSavedSaved = jobApplicationJpaRepository.saveAndFlush(jobApplicationToSave);

    }
}
