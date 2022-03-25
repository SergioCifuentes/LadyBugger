package ladybugger.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ladybugger.model.PMAssignment;



@Repository
public interface PMAssignmentRepository extends JpaRepository<PMAssignment, Long>{
    
}
