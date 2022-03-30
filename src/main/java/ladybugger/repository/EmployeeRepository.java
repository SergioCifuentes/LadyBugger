package ladybugger.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ladybugger.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findById(Long id);
	Optional<Employee> findByEmail(String email);
	Boolean existsByEmail(String email);

	@Query(value ="SELECT e.id,e.name,e.last_name FROM employee AS e INNER JOIN user_roles AS r ON e.id=r.user_id WHERE r.role_id = 1" , nativeQuery = true)
	List<Object[]> findByDevRole();
}