package pl.zajavka.CodeBridge.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.infrastructure.database.entity.SalaryEntity;

@Repository
public interface SalaryJpaRepository extends JpaRepository<SalaryEntity,Integer> {
}
