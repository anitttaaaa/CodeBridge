package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationJpaRepository extends JpaRepository<JobApplicationEntity, Integer> {



    @Query("SELECT ja FROM JobApplicationEntity ja WHERE ja.candidate.candidateId = :candidateId")
    List<JobApplicationEntity> findApplicationsByCandidateId(@Param("candidateId") Integer candidateId);
    @Query("SELECT ja FROM JobApplicationEntity ja WHERE ja.employer.employerId = :employerId")
    List<JobApplicationEntity> findJobApplicationsByEmployerId(Integer employerId);
}
