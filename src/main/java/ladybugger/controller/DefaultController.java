package ladybugger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ladybugger.model.Employee;
import ladybugger.payload.response.MessageResponse;
import ladybugger.payload.response.ProfileResponse;
import ladybugger.repository.EmployeeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ladybugger")
public class DefaultController {
    @Autowired
    EmployeeRepository userRepository;

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> profile(@PathVariable String id) {
            long id_long;
            try {
                    id_long = Long.parseLong(id);
            } catch (Exception e) {
                    return ResponseEntity
                                    .badRequest()
                                    .body(new MessageResponse("Wrong id"));
            }
            Employee em = userRepository.findById(id_long)
                            .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

            
            return ResponseEntity.ok(new ProfileResponse(em.getId(), 
                                                        em.getName(), 
                                                        em.getLastName(), 
                                                        em.getMiddleName(), 
                                                        em.getEmail(), 
                                                        1,
                                                        em.getStartDate().toString(), 
                                                        em.getPhases().size()));
    }
    
}