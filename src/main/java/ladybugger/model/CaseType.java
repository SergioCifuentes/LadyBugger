package ladybugger.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "casetype", uniqueConstraints = {
		
})
public class CaseType {
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

    @OneToMany(mappedBy = "casetype")
	Set<Phase> phases;

    
    public CaseType() {
    }

    public CaseType(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String description, int status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    
    public Set<Phase> getPhases() {
        return phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
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
    

}
