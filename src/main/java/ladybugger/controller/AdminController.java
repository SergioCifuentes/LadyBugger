package ladybugger.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ladybugger.model.Employee;
import ladybugger.model.PMAssignment;
import ladybugger.model.Project;
import ladybugger.payload.request.ProjectCreationRequest;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PMAssignmentRepository;
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
    @PostMapping("/create-project")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerProject(@Valid @RequestBody ProjectCreationRequest projectCreationRequest) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();
                        
		// Create new project
        Employee em =userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("Error: Employee not found"));
		Project project = new Project(projectCreationRequest.getName(),
							 
                            projectCreationRequest.getDescription(),
							 1,
                             projectCreationRequest.getStartDate(),
							 projectCreationRequest.getDueDate(),
                             em
                             );
        Employee pm =userRepository.findById((long)projectCreationRequest.getPmId()).orElseThrow(() -> new RuntimeException("Error: Employee not found"));
        java.sql.Timestamp timestamp1 = new java.sql.Timestamp(System.currentTimeMillis());
        Set<PMAssignment> pmas = new HashSet<>();
        PMAssignment apm = new PMAssignment(pm,project,timestamp1);
        pmas.add(apm);
        
        pm.setProjects(pmas);
        project.setPms(pmas);
        
        projectRepository.save(project);
        pmAssignmentRepository.save(apm);
		return new ResponseEntity<String>("{\"id\": \""+project.getId()+"\"}", HttpStatus.OK);
	}

    @GetMapping("/devs-list")
    //@PreAuthorize("hasRole('ADMIN')")
    public List<Object[]> getDevs() {
        System.out.println("Hola");

		return userRepository.findByDevRole();
	}
}
