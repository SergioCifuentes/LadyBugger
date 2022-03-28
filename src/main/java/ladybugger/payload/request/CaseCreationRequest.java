package ladybugger.payload.request;

import java.sql.Date;
import javax.validation.constraints.*;
public class CaseCreationRequest {
    
    @NotBlank
    @Size(max = 40)
    private String title;
  
    @NotBlank
    private Date startDate;
  
    @NotBlank
    private Date dueDate;

    @NotBlank
    @Size(max = 500)
    private String description;

    @NotBlank
    private int caseTypeId;

    @NotBlank
    private int projectId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCaseTypeId() {
        return caseTypeId;
    }

    public void setCaseTypeId(int caseTypeId) {
        this.caseTypeId = caseTypeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    
  
  

}
