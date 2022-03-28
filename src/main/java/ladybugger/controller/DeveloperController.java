package ladybugger.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import ladybugger.model.Employee;
import ladybugger.model.PhaseAssignment;
import ladybugger.model.Submission;
import ladybugger.payload.request.SubmissionRequest;
import ladybugger.payload.response.MessageResponse;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PhaseAssignmentRepository;

import ladybugger.repository.SubmissionRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/developer")
public class DeveloperController {
    @Autowired
    EmployeeRepository userRepository; 
    @Autowired
    PhaseAssignmentRepository phaseAssignmentRepository; 
    @Autowired
    SubmissionRepository submissionRepository;


    @PostMapping("/submit")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> submit(@Valid @RequestBody SubmissionRequest submissionRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Employee em = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

        PhaseAssignment phaseA = phaseAssignmentRepository.findById((long)submissionRequest.getPhaseAssignmentId())
                .orElseThrow(() -> new RuntimeException("Error: Project not found"));
        // System.out.println(pmaRepository.findLastManager(pr.getId()));  
       
        if(!phaseA.getDev().getId().equals(em.getId())){
            return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: You are not the developer of this phase"));
        }
                 
        Submission submission = new Submission(phaseA,
                                                submissionRequest.getComment(),
                                                submissionRequest.getHours(),
                                                submissionRequest.getCost(),
                                                submissionRequest.getDate());

        submissionRepository.save(submission);
		return new ResponseEntity<String>("{\"id\": \""+submission.getId()+"\"}", HttpStatus.OK);
    }

}
