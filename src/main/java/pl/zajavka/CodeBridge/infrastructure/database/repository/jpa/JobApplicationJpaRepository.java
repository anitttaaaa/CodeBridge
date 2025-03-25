package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.ApplicationsHistoryEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;

import java.util.List;

@Repository
public interface JobApplicationJpaRepository extends JpaRepository<JobApplicationEntity, Integer> {


    @Query("SELECT ja FROM JobApplicationEntity ja WHERE ja.candidate.candidateId = :candidateId")
    List<JobApplicationEntity> findApplicationsByCandidateId(@Param("candidateId") Integer candidateId);

    @Query("SELECT ja FROM JobApplicationEntity ja WHERE ja.employer.employerId = :employerId")
    List<JobApplicationEntity> findJobApplicationsByEmployerId(Integer employerId);


    @Query("SELECT ja FROM ApplicationsHistoryEntity ja WHERE ja.employer.employerId = :employerId")
    List<ApplicationsHistoryEntity> findHistoryApplicationsByEmployerId(Integer employerId);

    @Query("SELECT ja FROM ApplicationsHistoryEntity ja WHERE ja.candidate.candidateId = :candidateId")
    List<ApplicationsHistoryEntity> findHistoryApplicationsByCandidateId(Integer candidateId);
}
