package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;

@Repository
public interface CandidateExperienceJpaRepository extends JpaRepository<CandidateExperienceEntity, Integer> {
}
