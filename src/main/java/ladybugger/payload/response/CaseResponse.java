package ladybugger.payload.response;

public class CaseResponse {
    private Long id;
    private String description;
    private String caseTypeName;
    private int status;
    private String projectName;
    private Long projectId;
    private String start_date;
    private String due_date;
    public CaseResponse(Long id, String description, String caseTypeName, int status, String projectName,
    Long projectId, String start_date, String due_date) {
        this.id = id;
        this.description = description;
        this.caseTypeName = caseTypeName;
        this.status = status;
        this.projectName = projectName;
        this.projectId = projectId;
        this.start_date = start_date;
        this.due_date = due_date;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCaseTypeName() {
        return caseTypeName;
    }
    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    
}
