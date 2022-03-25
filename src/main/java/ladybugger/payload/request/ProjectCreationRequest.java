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
  private Timestamp startDate;

  @NotBlank
  private Timestamp dueDate;

  
public Timestamp getStartDate() {
    return startDate;
}

public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
}

public Timestamp getDueDate() {
    return dueDate;
}

public void setDueDate(Timestamp dueDate) {
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
