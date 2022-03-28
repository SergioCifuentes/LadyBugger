package ladybugger.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Table(name = "caseM", uniqueConstraints = {
		
})
public class Case {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank
	@Size(max = 50)
	private String title;

    @NotBlank
	@Size(max = 500)
	private String description;

    @ManyToOne
    @JoinColumn(name="casetype", nullable=false)
    private CaseType casetype;

    private int status;

    @ManyToOne
    @JoinColumn(name="project", nullable=false)
    private Project project;

    @NotBlank
    private Date startDate;

    private Date dueDate;


    
    public Case() {
    }

    public Case(@NotBlank @Size(max = 50) String title, @NotBlank @Size(max = 500) String description,
            CaseType casetype, int status, Project project, @NotBlank Date startDate, Date dueDate) {
        this.title = title;
        this.description = description;
        this.casetype = casetype;
        this.status = status;
        this.project = project;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CaseType getCasetype() {
        return casetype;
    }

    public void setCasetype(CaseType casetype) {
        this.casetype = casetype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getStartDate() {
        return startDate;
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


    
}
