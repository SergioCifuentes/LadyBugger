package ladybugger.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ladybugger.model.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    Optional<Project> findById(int id);
	Boolean existsByid(String id);
}
