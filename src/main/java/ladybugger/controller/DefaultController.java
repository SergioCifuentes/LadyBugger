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
import ladybugger.payload.response.ProfileResponse;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PhaseAssignmentRepository;

import ladybugger.repository.SubmissionRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ladybugger")
public class DefaultController {
    @Autowired
    EmployeeRepository userRepository;

    // @GetMapping("/profile/{id}")
    // public ResponseEntity<?> profile(@PathVariable String id) {
    //         long id_long;
    //         try {
    //                 id_long = Long.parseLong(id);
    //         } catch (Exception e) {
    //                 return ResponseEntity
    //                                 .badRequest()
    //                                 .body(new MessageResponse("Wrong id"));
    //         }
    //         Employee em = userRepository.findById(id_long)
    //                         .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

            
    //         return ResponseEntity.ok(new ProfileResponse(em.getId(), 
    //         name, last_name, middle_name, email, status, projects_work_on);
    // }
    
}
