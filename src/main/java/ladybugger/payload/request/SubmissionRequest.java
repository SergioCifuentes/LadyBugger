package ladybugger.payload.request;

import java.sql.Date;

import javax.validation.constraints.*;
public class SubmissionRequest {
    
    @NotBlank
    private int phaseAssignmentId;    
  
    @NotBlank
    @Size(max = 500)
    private String comment;
  
    @NotBlank
    private int hours; 
  
    @NotBlank
    private Float cost;
  
    @NotBlank
    private Date date;

    public int getPhaseAssignmentId() {
        return phaseAssignmentId;
    }

    public void setPhaseAssignmentId(int phaseAssignmentId) {
        this.phaseAssignmentId = phaseAssignmentId;
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
