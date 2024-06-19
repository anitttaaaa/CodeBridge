package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferCategoryEntity;

@Repository
public interface JobOfferCategoryJpaRepository extends JpaRepository<JobOfferCategoryEntity, Integer> {
}
