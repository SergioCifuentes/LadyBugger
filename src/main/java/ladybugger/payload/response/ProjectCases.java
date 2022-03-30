package ladybugger.payload.response;
import java.util.List;

public class ProjectCases {
    private Long id;
    private String name;
    private String pm_name;
    private int status;
    private String start_date;
    private String due_date;
    private int cases_amount;
    private List<SimpleCase> cases;
    
    public ProjectCases(Long id, String name, String pm_name, int status, String start_date, String due_date,
            List<SimpleCase> cases) {
        this.id = id;
        this.name = name;
        this.pm_name = pm_name;
        this.status = status;
        this.start_date = start_date;
        this.due_date = due_date;
        this.cases = cases;
        cases_amount=cases.size();
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
    public String getPm_name() {
        return pm_name;
    }
    public void setPm_name(String pm_name) {
        this.pm_name = pm_name;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getStart_date() {
        return start_date;
    }
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
    public String getDue_date() {
        return due_date;
    }
    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
    public int getCases_amount() {
        return cases_amount;
    }
    public void setCases_amount(int cases_amount) {
        this.cases_amount = cases_amount;
    }
    public List<SimpleCase> getCases() {
        return cases;
    }
    public void setCases(List<SimpleCase> cases) {
        this.cases = cases;
    }
    
    
}
