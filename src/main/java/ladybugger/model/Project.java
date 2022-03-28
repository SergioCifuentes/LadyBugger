package ladybugger.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.*;

import java.sql.Date;
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
	
    private Date startDate;

    private Date dueDate;

    @ManyToOne
    @JoinColumn(name="admin", nullable=false)
    private Employee admin;

    @OneToMany(mappedBy = "project")
	Set<Case> cases;
	


    @OneToMany(mappedBy = "project")
    Set<PMAssignment> pms;





	public Project(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 500) String description, int status,
    Date startDate, Date dueDate, Employee admin) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.admin = admin;
    }


    public Project() {
    }


    public Date getStartDate() {
        return startDate;
    }

    public void addPm(PMAssignment pm){
        
        pms.add(pm);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getDueDate() {
        return dueDate;
    }


    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }





    public Set<PMAssignment> getPms() {
        return pms;
    }


    public void setPms(Set<PMAssignment> pms) {
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
