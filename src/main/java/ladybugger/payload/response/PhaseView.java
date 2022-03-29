package ladybugger.payload.response;

public class PhaseView {
    private Long id;
    private String developer;
    private Long user_id;
    private int number;
    private int status;

    private String start_date;
    private String due_date;


    


    public PhaseView(Long id, String developer, Long user_id, int number, int status, String start_date,
            String due_date) {
        this.id = id;
        this.developer = developer;
        this.user_id = user_id;
        this.number = number;
        this.status = status;
        this.start_date = start_date;
        this.due_date = due_date;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDeveloper() {
        return developer;
    }
    public void setDeveloper(String developer) {
        this.developer = developer;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
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
    
    
}
