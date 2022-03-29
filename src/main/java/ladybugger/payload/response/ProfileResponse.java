package ladybugger.payload.response;

public class ProfileResponse {
    private Long id;
    private String name;
    private String last_name;
    private String middle_name;
    private String email;
    private int status;
    private String start_date;
    private int phases_worked_on;


    
    public String getStart_date() {
        return start_date;
    }


    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }


    public ProfileResponse(Long id, String name, String last_name, String middle_name, String email, int status,
            String start_date, int phases_worked_on) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.email = email;
        this.status = status;
        this.start_date = start_date;
        this.phases_worked_on = phases_worked_on;
    }


    public int getPhases_worked_on() {
        return phases_worked_on;
    }


    public void setPhases_worked_on(int phases_worked_on) {
        this.phases_worked_on = phases_worked_on;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getMiddle_name() {
        return middle_name;
    }
    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }



    
}
