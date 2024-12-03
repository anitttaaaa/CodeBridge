package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;

@Repository
public interface CandidateEducationJpaRepository extends JpaRepository<CandidateEducationEntity, Integer> {
}
