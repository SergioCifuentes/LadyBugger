package ladybugger.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Table(name = "revision", uniqueConstraints = {
		
})
public class Revision {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @NotBlank
    private Boolean isAccepted;

    @ManyToOne
    @JoinColumn(name="submission", nullable=false)
    private Submission submission;

    @NotBlank
    private Date date;

    @NotBlank
    private String rejectReason;


    public Revision(@NotBlank Boolean isAccepted, Submission submission, @NotBlank Date date,
            @NotBlank String rejectReason) {
        this.isAccepted = isAccepted;
        this.submission = submission;
        this.date = date;
        this.rejectReason = rejectReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    


}
