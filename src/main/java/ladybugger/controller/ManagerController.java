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
import ladybugger.model.Phase;
import ladybugger.model.PhaseAssignment;
import ladybugger.model.Project;
import ladybugger.model.Revision;
import ladybugger.model.Submission;
import ladybugger.model.Case;
import ladybugger.payload.request.CaseCreationRequest;
import ladybugger.payload.request.PhaseAssignmentRequest;
import ladybugger.payload.request.RevisionCreationRequest;
import ladybugger.payload.response.MessageResponse;
import ladybugger.repository.CaseRepository;
import ladybugger.repository.CaseTypeRepository;
import ladybugger.repository.EmployeeRepository;
import ladybugger.repository.PMAssignmentRepository;
import ladybugger.repository.PhaseAssignmentRepository;
import ladybugger.repository.PhaseRepository;
import ladybugger.repository.ProjectRepository;
import ladybugger.repository.RevisionRepository;
import ladybugger.repository.SubmissionRepository;




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
    @Autowired
    PhaseRepository phaseRepository;   
    @Autowired
    PhaseAssignmentRepository phaseAssignmentRepository; 
    @Autowired
    SubmissionRepository submissionRepository;   
    @Autowired
    RevisionRepository revisionRepository;

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

    @PostMapping("/assign-phase")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> assignPhase(@Valid @RequestBody PhaseAssignmentRequest assignmentRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Employee em = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

        Phase phase = phaseRepository.findById((long)assignmentRequest.getPhaseId())
                .orElseThrow(() -> new RuntimeException("Error: Phase not found"));

        Case caseM = caseRepository.findById((long)assignmentRequest.getCaseId())
                .orElseThrow(() -> new RuntimeException("Error: Case not found"));        
        // System.out.println(pmaRepository.findLastManager(pr.getId()));  
        PMAssignment pma = pmaRepository.findLastManager(caseM.getProject().getId());
        if(!pma.getEmployee().getId().equals(em.getId())){
            return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: You are not the project manager"));
        }
        Employee dev = userRepository.findById((long)assignmentRequest.getDevId())
                .orElseThrow(() -> new RuntimeException("Error: Dev not found"));
        PhaseAssignment phaseAssignment= new PhaseAssignment(dev, 
                                            phase, 
                                            caseM,
                                            assignmentRequest.getDescription(),
                                            1, 
                                            assignmentRequest.getStartDate(), 
                                            assignmentRequest.getDueDate());
       

        phaseAssignmentRepository.save(phaseAssignment);
		return new ResponseEntity<String>("{\"id\": \""+phaseAssignment.getId()+"\"}", HttpStatus.OK);
    }

    @PostMapping("/revision")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> revision(@Valid @RequestBody RevisionCreationRequest revisionCreationRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Employee em = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: Employee not found"));

        Submission submission = submissionRepository.findById((long)revisionCreationRequest.getSubmissionId())
                .orElseThrow(() -> new RuntimeException("Error: Submission not found"));
        PMAssignment pma = pmaRepository.findLastManager(submission.getPhaseAssignment().getCaseM().getProject().getId());
        if(!pma.getEmployee().getId().equals(em.getId())){
            return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: You are not the Project Manager of this phase"));
        }
                 
        Revision revision = new Revision(     revisionCreationRequest.isAccepted(),
                                                submission,
                                                revisionCreationRequest.getDate(),
                                                revisionCreationRequest.getRejectReason());

        revisionRepository.save(revision);
		return new ResponseEntity<String>("{\"id\": \""+revision.getId()+"\"}", HttpStatus.OK);
    }
    
}
