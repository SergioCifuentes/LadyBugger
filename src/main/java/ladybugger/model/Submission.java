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
@Table(name = "submission", uniqueConstraints = {
		
})
public class Submission {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @ManyToOne
    @JoinColumn(name="phaseAssignment", nullable=false)
    private PhaseAssignment phaseAssignment;

    @NotBlank
	@Size(max = 500)
	private String comment;

    
    @NotBlank
	private int hours;

    @NotBlank
	private Float cost;

    @NotBlank
    private Date date;


    
    public Submission() {
    }

    public Submission(PhaseAssignment phaseAssignment, @NotBlank @Size(max = 500) String comment, @NotBlank int hours,
            @NotBlank Float cost, @NotBlank Date date) {
        this.phaseAssignment = phaseAssignment;
        this.comment = comment;
        this.hours = hours;
        this.cost = cost;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhaseAssignment getPhaseAssignment() {
        return phaseAssignment;
    }

    public void setPhaseAssignment(PhaseAssignment phaseAssignment) {
        this.phaseAssignment = phaseAssignment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    

}
