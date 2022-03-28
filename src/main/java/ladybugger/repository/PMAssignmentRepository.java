package ladybugger.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import ladybugger.model.PMAssignment;
import ladybugger.model.Project;



@Repository
public interface PMAssignmentRepository extends JpaRepository<PMAssignment, Long>{
    List<PMAssignment> findByProject(Project project);
    @Query(value="SELECT * FROM pmassignment where project_id=?1 ORDER BY date desc limit 1" , nativeQuery = true)
    PMAssignment findLastManager(Long projectId);
}

