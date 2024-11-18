package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateExperienceJpaRepository extends JpaRepository<CandidateExperienceEntity, Integer> {

    @Query("SELECT ce FROM CandidateExperienceEntity ce WHERE ce.candidate.candidateId = :candidateId")
    List<CandidateExperienceEntity> findExperienceByCandidateId(@Param("candidateId") Integer candidateId);
}
