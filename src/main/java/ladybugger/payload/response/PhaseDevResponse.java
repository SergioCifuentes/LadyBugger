package ladybugger.payload.response;

public class PhaseDevResponse {
    private Long id;
    private String name;
    private String projectName;
    private String caseName;
    private String dev_name;
    private String startDate;
    private String dueDate;
    private int status;
    public PhaseDevResponse(Long id, String name, String projectName, String caseName, String dev_name,
            String startDate, String dueDate, int status) {
        this.id = id;
        this.name = name;
        this.projectName = projectName;
        this.caseName = caseName;
        this.dev_name = dev_name;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
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
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getCaseName() {
        return caseName;
    }
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
    public String getDev_name() {
        return dev_name;
    }
    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    
}
