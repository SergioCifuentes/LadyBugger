package ladybugger.repos;

import ladybugger.model.Employee;
import ladybugger.model.PhaseAssignment;
import ladybugger.model.Submission;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PhaseAssignmentRepository;
import ladybugger.repository.PhaseRepository;
import ladybugger.repository.SubmissionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeveloperRepoTest {
    @Autowired
    EmployeeRepository userRepository;
    @Autowired
    PhaseAssignmentRepository phaseAssignmentRepository;
    @Autowired
    SubmissionRepository submissionRepository;
    @Autowired
    PhaseRepository phaseRepository;

    @Test
    public void testSubmit() {
        Employee em = userRepository.findByEmail("email")
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        PhaseAssignment phaseA = phaseAssignmentRepository.findById((long) 1)
                .orElseThrow(() -> new RuntimeException("Error: Project not found"));
        Assertions.assertThat(phaseA.getDev().getId()).isEqualTo(em.getId());
        Submission submission = new Submission(phaseA,
                "comment",
                50,
                500F,
                new Date(System.currentTimeMillis()));
        Assertions.assertThat(submissionRepository.save(submission).getId()).isInstanceOf(Long.class);
    }

    @Test
    public void testGetPhase() {
        Employee em = userRepository.findByEmail("email")
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        PhaseAssignment phaseAssignment = phaseAssignmentRepository.findById((long) 1)
                .orElseThrow(() -> new RuntimeException("Error: Phase not found"));
        Assertions.assertThat(phaseAssignment.getDev().getId()).isEqualTo(em.getId());
    }

    @Test
    public void testProfile() {
        Employee em = userRepository.findById((long) 4)
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        Assertions.assertThat(em.getId()).isNotNull().isInstanceOf(Long.class);
    }

}
