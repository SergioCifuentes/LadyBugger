package ladybugger.payload.request;
import java.util.Set;

import javax.validation.constraints.*;
public class CaseTypeCreationRequest {
    
  @NotBlank
  @Size(max = 40)
  private String name;

  @NotBlank
  @Size(max = 500)
  private String description;


  private Set<String> phases;

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

public Set<String> getPhases() {
    return phases;
}

public void setPhases(Set<String> phases) {
    this.phases = phases;
}

  

}
