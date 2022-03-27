package ladybugger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "phase", uniqueConstraints = {
		
})
public class Phase {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank
	@Size(max = 50)
	private String name;

    private int number;

    @ManyToOne
    @JoinColumn(name="casetype", nullable=false)
    private CaseType casetype;

    
    public Phase(@NotBlank @Size(max = 50) String name, int number, CaseType casetype) {
        this.name = name;
        this.number = number;
        this.casetype = casetype;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CaseType getCasetype() {
        return casetype;
    }

    public void setCasetype(CaseType casetype) {
        this.casetype = casetype;
    }
    

    
}
