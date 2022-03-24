package ladybugger.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "project",
	uniqueConstraints = { 
	
})

public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank
	@Size(max = 50)
	private String name;

	@NotBlank
	@Size(max = 500)
	private String description;

   
	private int status;
	
    private java.sql.Timestamp startDate;

    private java.sql.Timestamp dueDate;

    @ManyToOne
    @JoinColumn(name="admin", nullable=false)
    private Employee admin;
	


    @OneToMany(mappedBy = "employee")
    Set<Employee> pms;



	





	public Project(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 500) String description, int status,
            Timestamp startDate, Timestamp dueDate, Employee admin) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.admin = admin;
    }


    public java.sql.Timestamp getStartDate() {
        return startDate;
    }


    public void setStartDate(java.sql.Timestamp startDate) {
        this.startDate = startDate;
    }


    public java.sql.Timestamp getDueDate() {
        return dueDate;
    }


    public void setDueDate(java.sql.Timestamp dueDate) {
        this.dueDate = dueDate;
    }


    public Set<Employee> getPms() {
        return pms;
    }


    public void setPms(Set<Employee> pms) {
        this.pms = pms;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public Employee getAdmin() {
        return admin;
    }


    public void setAdmin(Employee admin) {
        this.admin = admin;
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
	
}
