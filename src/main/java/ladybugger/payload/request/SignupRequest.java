package ladybugger.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  @NotBlank
  @Size(max = 40)
  private String name;

  @NotBlank
  @Size(max = 40)
  private String middle_name;

  @NotBlank
  @Size(max = 40)
  private String last_name;




  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMiddleName() {
    return middle_name;
  }

  public void setMiddleName(String middleName) {
    this.middle_name = middle_name;
  }

  public String getLastName() {
    return last_name;
  }

  public void setLastName(String lastName) {
    this.last_name = last_name;
  }
}