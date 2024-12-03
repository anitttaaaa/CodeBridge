package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;

public interface CandidateCourseJpaRepository extends JpaRepository<CandidateCourseEntity, Integer> {
}
