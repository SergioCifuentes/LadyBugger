package ladybugger.payload.response;

import java.util.List;

public class CaseView {
    private Long id;
    private String name;

    private String start_date;
    private String due_date;
    private String description;
    private int status;
    private List<PhaseView> phases;
    private int actual;

    
    public long getId() {
        return id;
    }

    

    public CaseView(Long id, String name, String start_date, String due_date, String description, int status,
            List<PhaseView> phases,int actual) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.due_date = due_date;
        this.description = description;
        this.status = status;
        this.phases = phases;
        this.actual = actual;
    }

    
    public void setId(Long id) {
        this.id = id;
    }



    public List<PhaseView> getPhases() {
        return phases;
    }



    public void setPhases(List<PhaseView> phases) {
        this.phases = phases;
    }



    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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



    public int getActual() {
        return actual;
    }



    public void setActual(int actual) {
        this.actual = actual;
    }

    
    
}
