package ladybugger.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ladybugger.model.Employee;
import ladybugger.model.Project;
import ladybugger.payload.request.ProjectCreationRequest;
import ladybugger.payload.request.SignupRequest;
import ladybugger.payload.response.MessageResponse;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.ProjectRepository;
import ladybugger.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/admin")
public class AdminController {
    EmployeeRepository userRepository;
    ProjectRepository projectRepository;
    @PostMapping("/create-project")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerProject(@Valid @RequestBody ProjectCreationRequest projectCreationRequest) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();

                        
		// Create new project
        System.out.println(userDetails.getUsername());
		Project project = new Project(projectCreationRequest.getName(),
							 
                            projectCreationRequest.getDescription(),
							 1,
                             projectCreationRequest.getStartDate(),
							 projectCreationRequest.getDueDate(),
                             userRepository.findByEmail(userDetails.getUsername()).get() 
                             );
		
			
			;
		
		
		projectRepository.save(project);
		return ResponseEntity.ok(new MessageResponse("Project registered successfully!"));
	}
}
