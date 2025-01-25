package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;

import java.util.Optional;
@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Integer> {

    Optional<CandidateEntity> findByEmail(String email);

}
