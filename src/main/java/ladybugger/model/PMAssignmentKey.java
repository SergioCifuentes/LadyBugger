package ladybugger.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PMAssignmentKey implements Serializable { 
    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "project_id")
    Long project_idId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation



}