package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.domain.JobApplication;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobApplicationEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationJpaRepository extends JpaRepository<JobApplicationEntity, Integer> {

    List<JobApplicationEntity>  findByCandidate_CandidateId(Integer candidateId);
}
