package ladybugger.payload.response;

public class PhaseResponse {
    private String name;
    private String dev_name;
    private String startDate;
    private String dueDate;
    private Long projectId;
    private String projectName;
    private Long caseId;
    private String caseName;
    private String caseDescription;
    public PhaseResponse(String name, String dev_name, String startDate, String dueDate, Long projectId,
            String projectName, Long caseId, String caseName, String caseDescription) {
        this.name = name;
        this.dev_name = dev_name;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.projectId = projectId;
        this.projectName = projectName;
        this.caseId = caseId;
        this.caseName = caseName;
        this.caseDescription = caseDescription;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public Long getCaseId() {
        return caseId;
    }
    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
    public String getCaseName() {
        return caseName;
    }
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
    public String getCaseDescription() {
        return caseDescription;
    }
    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    

    
}
