package ladybugger.repos;

import ladybugger.model.Case;
import ladybugger.model.CaseType;
import ladybugger.model.Employee;
import ladybugger.model.Project;
import ladybugger.repository.CaseRepository;
import ladybugger.repository.CaseTypeRepository;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.ProjectRepository;
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

    @Test
    public void createCaseType() {
        int i = 4;
        Project pr = projectRepository.findById((long) i)
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

}
