package ladybugger.payload.request;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

public class PhaseAssignmentRequest {
  
    @NotBlank
    private int devId;

    @NotBlank
    private int phaseId;

    @NotBlank
    private int caseId;

    @NotBlank
    private String description;

    @NotBlank
    private Date startDate;
  
    @NotBlank
    private Date dueDate;

    public int getDevId() {
        return devId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
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
