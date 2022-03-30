package ladybugger.repos;

import ladybugger.model.*;
import ladybugger.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ManagerRepoTest {

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
    @Autowired
    SubmissionRepository submissionRepository;
    @Autowired
    RevisionRepository revisionRepository;
    @Autowired
    PMAssignmentRepository pmaRepository;

    @Test
    public void testCreateCaseType() {
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
        Assertions.assertThat(newCase.getId()).isInstanceOf(Long.class);
    }

    @Test
    public void testAssignPhase() {
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

    @Test
    public void testRevision() {
        Submission submission = submissionRepository.findById((long) 1)
                .orElseThrow(() -> new RuntimeException("Error: Submission not found"));
        Revision revision = new Revision(true,
                submission,
                new Date(System.currentTimeMillis()),
                "Reject Reason");

        revisionRepository.save(revision);
        phaseAssignmentRepository.flush();
        System.out.println(revision.getId());
        Assertions.assertThat(revision.getId()).isInstanceOf(Long.class);
    }

    @Test
    public void testGetAssignedProject() {
        Employee em = userRepository.findByEmail("email")
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        List<Long> projectIds = pmaRepository.findProjects(em.getId());
        Assertions.assertThat(projectIds).isNotNull();
        for (Long id : projectIds) {
            Project pr = projectRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error: Project not found"));
            System.out.println(pr.getId());
            Assertions.assertThat(pr).isNotNull();
        }
    }

    @Test
    public void testGetProject() {
        Project pr = projectRepository.findById((long) 4)
                .orElseThrow(() -> new RuntimeException("Error: Project not found"));
        PMAssignment pma = pmaRepository.findLastManager((long) 4);
        Assertions.assertThat(pr).isNotNull();
        Assertions.assertThat(pma).isNotNull();
        Set<Case> pr_cases=pr.getCases();
        for (int i = 0; i < pr_cases.size(); i++) {
            Case ca =pr_cases.iterator().next();
            Set<Phase> pr_phase=ca.getCasetype().getPhases();
            for (int j = 0; j < pr_phase.size(); j++) {
                Phase ph =pr_phase.iterator().next();
                phaseAssignmentRepository.findDev(ca.getId(), ph.getId());
            }
        }
    }

}
