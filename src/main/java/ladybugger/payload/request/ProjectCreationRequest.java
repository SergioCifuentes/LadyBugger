package ladybugger.payload.request;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.*;
public class ProjectCreationRequest {
    




  @NotBlank
  @Size(max = 40)
  private String name;

  @NotBlank
  @Size(max = 500)
  private String description;

  @NotBlank
  private int pmId;

  @NotBlank
  private Date startDate;

  @NotBlank
  private Date dueDate;

  
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

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public int getPmId() {
    return pmId;
}

public void setPmId(int pmId) {
    this.pmId = pmId;
}









}
