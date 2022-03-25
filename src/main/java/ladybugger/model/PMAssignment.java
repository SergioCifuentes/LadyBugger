package ladybugger.model;


import javax.persistence.*;

@Entity
public class PMAssignment {
    
    @EmbeddedId
    PMAssignmentKey id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    Project project;

    //private java.sql.Timestamp date;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    // public java.sql.Timestamp getDate() {
    //     return date;
    // }

    // public void setDate(java.sql.Timestamp date) {
    //     this.date = date;
    // }


    
}
