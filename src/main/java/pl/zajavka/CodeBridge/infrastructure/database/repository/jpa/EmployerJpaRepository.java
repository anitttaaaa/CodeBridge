package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.EmployerEntity;

import java.util.Optional;

@Repository
public interface EmployerJpaRepository extends JpaRepository<EmployerEntity, Integer> {

    Optional<EmployerEntity> findByEmail(String email);
    Optional<EmployerEntity> findByUserId(Integer userId);



}
