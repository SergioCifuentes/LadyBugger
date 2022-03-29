package ladybugger.payload.response;

public class SimpleProject {

    private Long id;
    private String pm_name;
    private String name;
    private String due_date;
    private int status;
    public SimpleProject(Long id, String pm_name, String name, String due_date, int status) {
        this.id = id;
        this.pm_name = pm_name;
        this.name = name;
        this.due_date = due_date;
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPm_name() {
        return pm_name;
    }
    public void setPm_name(String pm_name) {
        this.pm_name = pm_name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDue_date() {
        return due_date;
    }
    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    
    
}
