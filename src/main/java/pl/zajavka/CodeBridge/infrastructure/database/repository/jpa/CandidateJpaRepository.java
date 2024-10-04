package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;

import java.util.Optional;
@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Integer> {

    Optional<CandidateEntity> findByEmail(String email);

    Optional<CandidateEntity> findByUserId(Integer userId);
}
