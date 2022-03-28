package ladybugger.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ladybugger.model.PhaseAssignment;

@Repository
public interface PhaseAssignmentRepository extends JpaRepository<PhaseAssignment, Long>{
    Optional<PhaseAssignment> findById(Long id);
}
