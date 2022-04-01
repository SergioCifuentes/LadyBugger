package ladybugger.payload.response;
public class EmployeeResponse {
    private Long id;
    private String full_name;
    private String email;
    private String roles;
    private int status;
    private String creation_date;

    public EmployeeResponse(Long id, String full_name, String email, String roles, int status,
            String creation_date) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.roles = roles;
        this.status = status;
        this.creation_date = creation_date;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCreation_date() {
        return creation_date;
    }
    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    
}
