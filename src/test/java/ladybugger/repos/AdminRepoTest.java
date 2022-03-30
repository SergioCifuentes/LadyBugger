package ladybugger.repos;

import ladybugger.model.*;
import ladybugger.payload.response.ProjectCases;
import ladybugger.payload.response.SimpleCase;
import ladybugger.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminRepoTest {
    @Autowired
    EmployeeRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    PMAssignmentRepository pmAssignmentRepository;
    @Autowired
    CaseTypeRepository caseTypeRepository;
    @Autowired
    PhaseRepository phaseRepository;
    @Autowired
    PMAssignmentRepository pmaRepository;
    @Autowired
    CaseRepository caseRepository;

    @Test
    public void testCreateProject() {
        Employee em = userRepository.findByEmail("email")
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        Project project = new Project("name project",
                "description",
                1,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()+25),
                em);
        Employee pm = userRepository.findById((long) 1)
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        Set<PMAssignment> pmas = new HashSet<>();
        PMAssignment apm = new PMAssignment(pm, project, new Timestamp(System.currentTimeMillis()));
        pmas.add(apm);
        pm.setProjects(pmas);
        project.setPms(pmas);
        projectRepository.save(project);
        pmAssignmentRepository.save(apm);
        Assertions.assertThat(project.getId()).isInstanceOf(Long.class);
        Assertions.assertThat(apm.getDate()).isInstanceOf(Timestamp.class);
    }

    @Test
    public void testDevList() {
        Assertions.assertThat(userRepository.findByDevRole()).isInstanceOf(List.class);
    }

    @Test
    public void testCreateCaseType() {
        CaseType caseType = new CaseType("Case type",
                "description",
                1);
        String[] arrphases = {"phase 1", "phase 2", "phase 3"};
        Set<Phase> phases = new HashSet<>();
        for(int i=0;i<arrphases.length;i++){
            Phase phase = new Phase(arrphases[i], i+1, caseType);
            phases.add(phase);
        }
        caseTypeRepository.save(caseType);
        Assertions.assertThat(phaseRepository.saveAll(phases).size()==2);
        Assertions.assertThat(caseType.getId()).isInstanceOf(Long.class);
    }

    @Test
    public void testAssignProject() {
        Project pr = projectRepository.findById((long)4)
                .orElseThrow(() -> new RuntimeException("Error: Project not found"));
        Employee dev = userRepository.findById((long)1)
                .orElseThrow(() -> new RuntimeException("Error: Dev not found"));
        PMAssignment pma = new PMAssignment(dev,
                pr,
                new java.sql.Timestamp(System.currentTimeMillis()));
        Assertions.assertThat(pmAssignmentRepository.save(pma)).isNotNull().isInstanceOf(PMAssignment.class);
    }

    @Test
    public void testGetProjects() {
        Page<Project> pr = projectRepository.findAll(Pageable.ofSize(5));
        Assertions.assertThat(pr).isNotNull();
        Assertions.assertThat(pr.getSize()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(5);
        for (Project project : pr) {
            PMAssignment pma = pmaRepository
                    .findLastManager(project.getId());
            Assertions.assertThat(pma).isNotNull();
        }
    }

    @Test
    public void testGetCases() {
        Assertions.assertThat(caseRepository.findAll(Pageable.ofSize(5)))
                .isNotNull();
        Assertions.assertThat(caseRepository.findAll(Pageable.ofSize(5)).getSize())
                .isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(5);
    }

}
