package ladybugger.repository;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import ladybugger.model.PhaseAssignment;

@Repository
public interface PhaseAssignmentRepository extends JpaRepository<PhaseAssignment, Long>{
    Optional<PhaseAssignment> findById(Long id);
    @Query(value="SELECT * FROM phase_assignment where casem=?1 AND phase=?2 limit 1" , nativeQuery = true)
    PhaseAssignment findDev(Long caseId,Long phaseId);
    @Query(value="SELECT * FROM phase_assignment where dev=?1" , nativeQuery = true)
    List<PhaseAssignment> findPhasesByDev(Long devId);

}
