package ladybugger.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ladybugger.model.CaseType;

@Repository
public interface CaseTypeRepository extends JpaRepository<CaseType, Long> {
    Optional<CaseType> findById(int id);
	Boolean existsByid(String id);
}
