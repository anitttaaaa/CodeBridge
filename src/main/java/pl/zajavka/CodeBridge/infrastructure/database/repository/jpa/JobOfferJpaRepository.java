package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;

@Repository
public interface JobOfferJpaRepository extends JpaRepository<JobOfferEntity, Integer> {

//    List<JobOfferEntity> findByTechSpecialization(TechSpecializationsEnum techSpecialization);


}
