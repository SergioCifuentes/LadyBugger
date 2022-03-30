package ladybugger.payload.response;

import java.util.List;

public class ProjectView {
    private Long id;
    private String name;
    private String description;
    private String pm_name;
    private Long project_manager_id; 
    private String start_date;
    private String due_date;
    private int status;
    private List<CaseView> cases;




    
    public ProjectView(Long id, String name, String description, String pm_name, Long project_manager_id,
            String start_date, String due_date, int status, List<CaseView> cases) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pm_name = pm_name;
        this.project_manager_id = project_manager_id;
        this.start_date = start_date;
        this.due_date = due_date;
        this.status = status;
        this.cases = cases;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPm_name() {
        return pm_name;
    }
    public void setPm_name(String pm_name) {
        this.pm_name = pm_name;
    }
    public Long getProject_manager_id() {
        return project_manager_id;
    }
    public void setProject_manager_id(Long project_manager_id) {
        this.project_manager_id = project_manager_id;
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
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public List<CaseView> getCases() {
        return cases;
    }
    public void setCases(List<CaseView> cases) {
        this.cases = cases;
    }

    


}
