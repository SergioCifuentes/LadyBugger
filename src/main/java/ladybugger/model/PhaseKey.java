package ladybugger.model;

import java.io.Serializable;

public class PhaseKey implements Serializable {
    private int number;
    private CaseType casetype;
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