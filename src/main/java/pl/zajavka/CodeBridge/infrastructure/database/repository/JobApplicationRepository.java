package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.JobApplicationDAO;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.JobApplicationJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.JobApplicationEntityMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<JobApplication> findApplicationsByCandidateId(Integer candidateId) {

        List<JobApplicationEntity> jobApplicationEntities= jobApplicationJpaRepository.findApplicationsByCandidateId(candidateId);

        return jobApplicationEntities.stream()
                .map(jobApplicationEntityMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobApplication> findEmployerJobApplicationsByEmployerId(Integer employerId) {

        List<JobApplicationEntity> jobApplicationEntities = jobApplicationJpaRepository.findJobApplicationsByEmployerId(employerId);
        return jobApplicationEntities.stream().map(jobApplicationEntityMapper::mapToDomain).collect(Collectors.toList());
    }


}
