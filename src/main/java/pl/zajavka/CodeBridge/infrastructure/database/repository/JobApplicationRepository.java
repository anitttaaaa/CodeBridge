package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.JobApplicationDAO;
import pl.zajavka.CodeBridge.domain.ApplicationsHistory;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.infrastructure.database.entity.ApplicationsHistoryEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobApplicationJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.ApplicationsHistoryEntityMapper;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobApplicationEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class JobApplicationRepository implements JobApplicationDAO {

    JobApplicationEntityMapper jobApplicationEntityMapper;
    ApplicationsHistoryEntityMapper applicationsHistoryEntityMapper;
    JobApplicationJpaRepository jobApplicationJpaRepository;

    @Override
    public void createJobApplication(JobApplication jobApplication) {


        JobApplicationEntity jobApplicationToSave = jobApplicationEntityMapper.mapToEntity(jobApplication);
        JobApplicationEntity jobApplicationSavedSaved = jobApplicationJpaRepository.saveAndFlush(jobApplicationToSave);

    }

    @Override
    public List<JobApplication> findApplicationsByCandidateId(Integer candidateId) {

        List<JobApplicationEntity> jobApplicationEntities = jobApplicationJpaRepository.findApplicationsByCandidateId(candidateId);

        return jobApplicationEntities.stream()
                .map(jobApplicationEntityMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobApplication> findEmployerJobApplicationsByEmployerId(Integer employerId) {

        List<JobApplicationEntity> jobApplicationEntities = jobApplicationJpaRepository.findJobApplicationsByEmployerId(employerId);
        return jobApplicationEntities.stream().map(jobApplicationEntityMapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<JobApplication> findApplicationById(Integer applicationId) {
        return jobApplicationJpaRepository.findById(applicationId)
                .map(jobApplicationEntityMapper::mapToDomain);
    }

    @Override
    public void save(JobApplication jobApplicationWithStatus) {
        JobApplicationEntity jobApplicationToSave = jobApplicationEntityMapper.mapToEntity(jobApplicationWithStatus);
        jobApplicationJpaRepository.saveAndFlush(jobApplicationToSave);

    }

    @Override
    public void deleteById(Integer applicationId) {
        jobApplicationJpaRepository.deleteById(applicationId);
    }

    @Override
    public List<ApplicationsHistory> findEmployerHistoryApplicationsByEmployerId(Integer employerId) {
        List<ApplicationsHistoryEntity> historyApplicationEntities = jobApplicationJpaRepository.findHistoryApplicationsByEmployerId(employerId);
        return historyApplicationEntities.stream().map(applicationsHistoryEntityMapper::mapToDomain).collect(Collectors.toList());    }


}
