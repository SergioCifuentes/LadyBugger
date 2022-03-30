package ladybugger.payload.request;

import java.sql.Date;

import javax.validation.constraints.*;

public class RevisionCreationRequest {
    
    @NotBlank
    private boolean isAccepted; 

    @NotBlank
    private int submissionId;  

    @NotBlank
    private Date date;
  
    @Size(max = 500)
    private String rejectReason;

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
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
