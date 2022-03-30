package ladybugger.model;


import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "pmassignment")
public class PMAssignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    private java.sql.Timestamp date;


    

    public PMAssignment() {
    }

    public PMAssignment(Employee employee, Project project, Timestamp date) {
        this.employee = employee;
        this.project = project;
        this.date = date;
    }

    public java.sql.Timestamp getDate() {
        return date;
    }

    public void setDate(java.sql.Timestamp date) {
        this.date = date;
    }

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
