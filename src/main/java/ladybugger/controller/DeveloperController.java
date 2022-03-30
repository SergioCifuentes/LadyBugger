package ladybugger.controller;

import java.util.ArrayList;
import java.util.List;

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
import ladybugger.payload.response.PhaseDevResponse;
import ladybugger.payload.response.PhaseResponse;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PhaseAssignmentRepository;
import ladybugger.repository.PhaseRepository;
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
        @Autowired
        PhaseRepository phaseRepository;

        @PostMapping("/submit")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<?> submit(@Valid @RequestBody SubmissionRequest submissionRequest) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                                .getPrincipal();
                Employee em = userRepository.findByEmail(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

                PhaseAssignment phaseA = phaseAssignmentRepository
                                .findById((long) submissionRequest.getPhaseAssignmentId())
                                .orElseThrow(() -> new RuntimeException("Error: Project not found"));
                // System.out.println(pmaRepository.findLastManager(pr.getId()));

                if (!phaseA.getDev().getId().equals(em.getId())) {
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
                return new ResponseEntity<String>("{\"id\": \"" + submission.getId() + "\"}", HttpStatus.OK);
        }

        @GetMapping("/get-phase/{id}")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<?> getPhase(@PathVariable String id) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                                .getPrincipal();
                Employee em = userRepository.findByEmail(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

                long id_long;
                try {
                        id_long = Long.parseLong(id);
                } catch (Exception e) {
                        return ResponseEntity
                                        .badRequest()
                                        .body(new MessageResponse("Wrong id"));
                }
                PhaseAssignment phaseAssignment = phaseAssignmentRepository.findById(id_long)
                                .orElseThrow(() -> new RuntimeException("Error: Phase not found"));
                if (!phaseAssignment.getDev().getId().equals(em.getId())) {
                        return ResponseEntity
                                        .badRequest()
                                        .body(new MessageResponse(
                                                        "Error: You are not the developer of this phase"));
                }

                return ResponseEntity.ok(new PhaseResponse(phaseAssignment.getPhase().getName(),
                                phaseAssignment.getDev().getName() + " " + phaseAssignment.getDev().getLastName(),
                                phaseAssignment.getStartDate().toString(),
                                phaseAssignment.getDueDate().toString(),
                                phaseAssignment.getCaseM().getProject().getId(),
                                phaseAssignment.getCaseM().getProject().getName(),
                                phaseAssignment.getCaseM().getId(),
                                phaseAssignment.getCaseM().getTitle(),
                                phaseAssignment.getCaseM().getDescription()));
        }

        @GetMapping("/get-phases")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<?> getPhases() {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                                .getPrincipal();
                Employee em = userRepository.findByEmail(userDetails.getUsername())
                                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));
                List<PhaseDevResponse> phasesResponse = new ArrayList<PhaseDevResponse>();
                List<PhaseAssignment> phaseAssignments = phaseAssignmentRepository.findPhasesByDev(em.getId());
                for (PhaseAssignment phaseAs : phaseAssignments) {
                        phasesResponse.add(new PhaseDevResponse(phaseAs.getPhase().getId(),
                                        phaseAs.getPhase().getName(),
                                        phaseAs.getCaseM().getProject().getName(),
                                        phaseAs.getCaseM().getTitle(),
                                        em.getName() + " " + em.getLastName(),
                                        phaseAs.getStartDate().toString(),
                                        phaseAs.getDueDate().toString(),
                                        phaseAs.getStatus()));
                }
                return ResponseEntity.ok(phasesResponse);
        }

}
