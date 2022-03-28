package ladybugger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Table(name = "phase_assignment", uniqueConstraints = {
		
})
public class PhaseAssignment {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
    @JoinColumn(name="dev", nullable=false)
    private Employee dev;

    @ManyToOne
    @JoinColumn(name="phase", nullable=false)
    private Phase phase;
    
    @ManyToOne
    @JoinColumn(name="caseM", nullable=false)
    private Case caseM;

    @NotBlank
	@Size(max = 500)
	private String description;

    private int status;

    @NotBlank
    private Date startDate;

    private Date dueDate;

    

    public PhaseAssignment() {
    }








    public PhaseAssignment(Employee dev, Phase phase, Case caseM, @NotBlank @Size(max = 500) String description,
            int status, @NotBlank Date startDate, Date dueDate) {
        this.dev = dev;
        this.phase = phase;
        this.caseM = caseM;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }








    public String getDescription() {
        return description;
    }








    public void setDescription(String description) {
        this.description = description;
    }








    public Case getCaseM() {
        return caseM;
    }


    public void setCaseM(Case caseM) {
        this.caseM = caseM;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getDev() {
        return dev;
    }

    public void setDev(Employee dev) {
        this.dev = dev;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    
}
