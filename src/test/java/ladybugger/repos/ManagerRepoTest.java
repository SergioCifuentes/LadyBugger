package ladybugger.repos;

import ladybugger.model.*;
import ladybugger.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDateTime;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ManagerRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    CaseRepository caseRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CaseTypeRepository caseTypeRepository;
    @Autowired
    PhaseRepository phaseRepository;
    @Autowired
    EmployeeRepository userRepository;
    @Autowired
    PhaseAssignmentRepository phaseAssignmentRepository;

    @Test
    public void createCaseType() {
        Project pr = projectRepository.findById((long) 4)
                .orElseThrow(() -> new RuntimeException("Error: Project not found"));
        CaseType ct = caseTypeRepository.findById((long) 1)
                .orElseThrow(() -> new RuntimeException("Error: CaseType not found"));
        Case newCase = new Case("title",
                "description",
                ct,
                1,
                pr,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()+25),
                1);
        caseRepository.save(newCase);
        caseRepository.flush();
        Assertions.assertThat(newCase.getId()).isInstanceOf(Long.class);
    }

    @Test
    public void assignPhase() {
        Employee em = userRepository.findByEmail("email")
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

        Phase phase = phaseRepository.findById((long) 1)
                .orElseThrow(() -> new RuntimeException("Error: Phase not found"));

        Case caseM = caseRepository.findById((long) 1)
                .orElseThrow(() -> new RuntimeException("Error: Case not found"));
        PhaseAssignment phaseAssignment = new PhaseAssignment(
                em,
                phase,
                caseM,
                "description",
                1,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()+25));
        phaseAssignmentRepository.save(phaseAssignment);
        phaseAssignmentRepository.flush();
        System.out.println(phaseAssignment.getId());
        Assertions.assertThat(phaseAssignment.getId()).isInstanceOf(Long.class);
    }

}
