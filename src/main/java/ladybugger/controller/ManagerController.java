package ladybugger.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import ladybugger.model.CaseType;
import ladybugger.model.Employee;
import ladybugger.model.PMAssignment;
import ladybugger.model.Project;
import ladybugger.model.Case;
import ladybugger.payload.request.CaseCreationRequest;
import ladybugger.payload.response.MessageResponse;
import ladybugger.repository.CaseRepository;
import ladybugger.repository.CaseTypeRepository;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PMAssignmentRepository;
import ladybugger.repository.ProjectRepository;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CaseTypeRepository caseTypeRepository;
    @Autowired
    CaseRepository caseRepository;    
    @Autowired
    EmployeeRepository userRepository;  
    @Autowired
    PMAssignmentRepository pmaRepository;    


    @PostMapping("/create-case")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> registerCaseType(@Valid @RequestBody CaseCreationRequest caseRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Employee em = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

        Project pr = projectRepository.findById((long)caseRequest.getProjectId())
                .orElseThrow(() -> new RuntimeException("Error: Project not found"));
        // System.out.println(pmaRepository.findLastManager(pr.getId()));  
        PMAssignment pma = pmaRepository.findLastManager(pr.getId());
        if(!pma.getEmployee().getId().equals(em.getId())){
            return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: You are not the project Manager"));
        }
         
        CaseType ct = caseTypeRepository.findById((long)caseRequest.getCaseTypeId())
                .orElseThrow(() -> new RuntimeException("Error: CaseType not found"));        
        Case newCase = new Case(caseRequest.getTitle(), 
                                caseRequest.getDescription(), 
                                ct, 
                                1, 
                                pr, 
                                caseRequest.getStartDate(), 
                                caseRequest.getDueDate());

        caseRepository.save(newCase);
		return new ResponseEntity<String>("{\"id\": \""+newCase.getId()+"\"}", HttpStatus.OK);
    }
    
}