package ladybugger.payload.request;

import javax.validation.constraints.NotBlank;

public class PMAssignmentRequest {
    @NotBlank
    private int projectId;

    @NotBlank
    private int employeeId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    
}
