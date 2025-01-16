package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.JobOfferEntity;

import java.util.List;

@Repository
public interface JobOfferJpaRepository extends JpaRepository<JobOfferEntity, Integer> {

    @Query("SELECT jo FROM JobOfferEntity jo WHERE jo.employer.employerId = :employerId")
    List<JobOfferEntity> findJobOffersByEmployerId(Integer employerId);
}
