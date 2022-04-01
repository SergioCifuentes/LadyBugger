package ladybugger.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ladybugger.exceptions.*;
import ladybugger.model.Case;
import ladybugger.model.CaseType;
import ladybugger.model.Employee;
import ladybugger.model.PMAssignment;
import ladybugger.model.Phase;
import ladybugger.model.Project;
import ladybugger.model.Role;
import ladybugger.payload.request.CaseTypeCreationRequest;
import ladybugger.payload.request.PMAssignmentRequest;
import ladybugger.payload.request.ProjectCreationRequest;
import ladybugger.payload.response.CaseResponse;
import ladybugger.payload.response.EmployeeResponse;
import ladybugger.payload.response.MessageResponse;
import ladybugger.payload.response.ProjectCases;
import ladybugger.payload.response.SimpleCase;
import ladybugger.repository.CaseRepository;
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
    @Autowired
    CaseRepository caseRepository;

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

        for (int i = 0; i < arrphases.length; i++) {
            Phase phase = new Phase(arrphases[i], i + 1, caseType);
            phases.add(phase);
        }

        caseTypeRepository.save(caseType);
        phaseRepository.saveAll(phases);
        return new ResponseEntity<String>("{\"id\": \"" + caseType.getId() + "\"}", HttpStatus.OK);
    }

    @PostMapping("/assign-project")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignProject(@Valid @RequestBody PMAssignmentRequest pmAssignmentRequest) {
        Project pr = projectRepository.findById((long) pmAssignmentRequest.getProjectId())
                .orElseThrow(() -> new RuntimeException("Error: Project not found"));

        Employee dev = userRepository.findById((long) pmAssignmentRequest.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Error: Dev not found"));
        java.sql.Timestamp timestamp1 = new java.sql.Timestamp(System.currentTimeMillis());
        PMAssignment pma = new PMAssignment(dev,
                pr,
                timestamp1);
        pmAssignmentRepository.save(pma);
        return new ResponseEntity<String>("Project Manager Asignado", HttpStatus.OK);
    }

    @GetMapping(value = "/get-projects")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProjects(Pageable pageable) {

        Page<Project> pr = projectRepository.findAll(pageable);

        List<ProjectCases> projectsResponse = new ArrayList<ProjectCases>();
        for (Project project : pr) {
            List<SimpleCase> casesResponse = new ArrayList<SimpleCase>();
            for (Case cases : project.getCases()) {
                casesResponse.add(new SimpleCase(cases.getId(),
                        cases.getTitle(),
                        cases.getDescription()));
            }
            PMAssignment pma = pmaRepository
                    .findLastManager(project.getId());
            projectsResponse.add(new ProjectCases(project.getId(),
                    project.getName(),
                    pma.getEmployee().getName() + " " + pma.getEmployee().getLastName(),
                    project.getStatus(),
                    project.getStartDate().toString(),
                    project.getDueDate().toString(),
                    casesResponse));
        }

        return ResponseEntity.ok(projectsResponse);
    }

    @PutMapping("/delete-employee/{id}")
    public ResponseEntity<?> softDeleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Employee em = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        if(em.getId().equals(employeeId)){
            return ResponseEntity
                                    .badRequest()
                                    .body(new MessageResponse("You can't delete yourself"));
        }
        Employee employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employee.setStatus(2);
        final Employee updatedEmployee = userRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping(value = "/get-cases")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCases(Pageable pageable) {

        Page<Case> pr = caseRepository.findAll(pageable);

        List<CaseResponse> casesResponse = new ArrayList<CaseResponse>();
        for (Case caseM : pr) {
            casesResponse.add(new CaseResponse(caseM.getId(),
                    caseM.getDescription(),
                    caseM.getCasetype().getName(),
                    caseM.getStatus(),
                    caseM.getProject().getName(),
                    caseM.getProject().getId(),
                    caseM.getStartDate().toString(),
                    caseM.getDueDate().toString()));
        }

        return ResponseEntity.ok(casesResponse);
    }
    @GetMapping(value = "/get-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers(Pageable pageable) {

        Page<Employee> employees = userRepository.findAll(pageable);

        List<EmployeeResponse> employeeResponse = new ArrayList<EmployeeResponse>();
        
        for (Employee employee : employees) {
        
                List<Role> roles = new ArrayList<>(employee.getRoles());
                String role;
                if (roles.size()==2){
                        role="ROLE_ADMIN";
                }else{
                        role="ROLE_USER";
                }
                employeeResponse.add(new EmployeeResponse(employee.getId(),
                                                employee.getName()+" "+employee.getLastName(),
                                                employee.getEmail(),
                                                role,
                                                employee.getStatus(),
                                                employee.getStartDate().toString()));
                
        }

        return ResponseEntity.ok(employeeResponse);
    }
}
