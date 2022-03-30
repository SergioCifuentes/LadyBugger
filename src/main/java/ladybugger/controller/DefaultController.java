package ladybugger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import ladybugger.model.Employee;
import ladybugger.model.PMAssignment;
import ladybugger.model.PhaseAssignment;
import ladybugger.model.Project;
import ladybugger.payload.response.MessageResponse;
import ladybugger.payload.response.ProfileResponse;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PMAssignmentRepository;
import ladybugger.repository.PhaseAssignmentRepository;
import ladybugger.repository.ProjectRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ladybugger")
public class DefaultController {
        @Autowired
        EmployeeRepository userRepository;
        @Autowired
        ProjectRepository projectRepository;
        @Autowired
        PhaseAssignmentRepository phaseAssignmentRepository;
        @Autowired
        PMAssignmentRepository pmaRepository;

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

        @GetMapping("/phase-job/{phase_id}")
        @PreAuthorize("hasRole('USER')")
        public String phaseJob(@PathVariable String phase_id) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                                .getPrincipal();
                Employee em = userRepository.findByEmail(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
                long id_long;
                try {
                        id_long = Long.parseLong(phase_id);
                } catch (Exception e) {
                        return "Wrong id";
                }
                PhaseAssignment pa = phaseAssignmentRepository.findById(id_long)
                .orElseThrow(() -> new RuntimeException("Error: Phase Assignment not found"));;
                PMAssignment pma = pmaRepository.findLastManager(pa.getCaseM().getProject().getId());
                
                if (pma.getEmployee().getId().equals(em.getId())) {
                        return "PM";
                } else if (pa.getDev().getId().equals(em.getId())) {
                        return "DEV";
                } else {
                        return "None";
                }

        }

}
