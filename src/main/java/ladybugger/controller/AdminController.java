package ladybugger.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ladybugger.model.Case;
import ladybugger.model.CaseType;
import ladybugger.model.Employee;
import ladybugger.model.PMAssignment;
import ladybugger.model.Phase;
import ladybugger.model.Project;
import ladybugger.payload.request.CaseTypeCreationRequest;
import ladybugger.payload.request.PMAssignmentRequest;
import ladybugger.payload.request.ProjectCreationRequest;
import ladybugger.payload.response.ProjectCases;
import ladybugger.payload.response.SimpleCase;
import ladybugger.repository.CaseTypeRepository;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PMAssignmentRepository;
import ladybugger.repository.PhaseRepository;
import ladybugger.repository.ProjectRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
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

    @PostMapping("/create-project")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerProject(@Valid @RequestBody ProjectCreationRequest projectCreationRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        // Create new project
        Employee em = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        Project project = new Project(projectCreationRequest.getName(),

                projectCreationRequest.getDescription(),
                1,
                projectCreationRequest.getStartDate(),
                projectCreationRequest.getDueDate(),
                em);
        Employee pm = userRepository.findById((long) projectCreationRequest.getPmId())
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        java.sql.Timestamp timestamp1 = new java.sql.Timestamp(System.currentTimeMillis());
        Set<PMAssignment> pmas = new HashSet<>();
        PMAssignment apm = new PMAssignment(pm, project, timestamp1);
        pmas.add(apm);

        pm.setProjects(pmas);
        project.setPms(pmas);

        projectRepository.save(project);
        pmAssignmentRepository.save(apm);
        return new ResponseEntity<String>("{\"id\": \"" + project.getId() + "\"}", HttpStatus.OK);
    }

    @GetMapping("/devs-list")
    // @PreAuthorize("hasRole('ADMIN')")
    public List<Object[]> getDevs() {
        System.out.println("Hola");

        return userRepository.findByDevRole();
    }

    @PostMapping("/create-casetype")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerCaseType(@Valid @RequestBody CaseTypeCreationRequest caseRequest) {

        CaseType caseType = new CaseType(caseRequest.getName(),
                caseRequest.getDescription(),
                1);
        Set<String> strphases = caseRequest.getPhases();
        Set<Phase> phases = new HashSet<>();
        System.out.println(strphases);
        String[] arrphases = strphases.toArray(new String[strphases.size()]);

        for(int i=0;i<arrphases.length;i++){
            Phase phase = new Phase(arrphases[i], i+1, caseType);
            phases.add(phase);
        }
        

        

        caseTypeRepository.save(caseType);
        phaseRepository.saveAll(phases);
		return new ResponseEntity<String>("{\"id\": \""+caseType.getId()+"\"}", HttpStatus.OK);
    }


    @PostMapping("/assign-project")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignProject(@Valid @RequestBody PMAssignmentRequest pmAssignmentRequest) {
        Project pr = projectRepository.findById((long)pmAssignmentRequest.getProjectId())
                .orElseThrow(() -> new RuntimeException("Error: Project not found")); 

        Employee dev = userRepository.findById((long)pmAssignmentRequest.getEmployeeId())
        .orElseThrow(() -> new RuntimeException("Error: Dev not found"));  
        java.sql.Timestamp timestamp1 = new java.sql.Timestamp(System.currentTimeMillis());      
        PMAssignment pma = new PMAssignment(dev,
                                            pr,
                                            timestamp1);
        pmAssignmentRepository.save(pma);
		return new ResponseEntity<String>("Project Manager Asignado", HttpStatus.OK);
    }  
    @GetMapping(value ="/get-projects")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProjects(Pageable pageable) {
            
            Page<Project> pr = projectRepository.findAll(pageable);
                            
            List<ProjectCases> projectsResponse=new ArrayList<ProjectCases>();
            for (Project project : pr) {
                List<SimpleCase> casesResponse=new ArrayList<SimpleCase>();
                for (Case cases : project.getCases()) {
                    casesResponse.add(new SimpleCase(cases.getId(), 
                                                    cases.getTitle(), 
                                                    cases.getDescription()));
                }
                PMAssignment pma = pmaRepository
                                .findLastManager(project.getId());
                projectsResponse.add(new ProjectCases(project.getId(), 
                                            project.getName(),
                                            pma.getEmployee().getName()+" "+pma.getEmployee().getLastName(),
                                            project.getStatus(), 
                                            project.getStartDate().toString(), 
                                            project.getDueDate().toString(), 
                                            casesResponse));
            }
           
            return ResponseEntity.ok(projectsResponse);
    }      
}
