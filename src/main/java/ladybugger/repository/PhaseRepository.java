package ladybugger.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ladybugger.model.Phase;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {
    Optional<Phase> findById(int id);
	Boolean existsByid(String id);
}
