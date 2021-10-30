package ggc;
import java.io.Serializable;

public abstract class Status implements Serializable{
    private Partner _partner;
    private int _points;

    public Status(Partner partner, int points){
        _partner = partner;
        _points = points;
    }

    public Partner getPartner(){
        return _partner;
    }

    public int getPoints(){
        return _points;
    }

    public void setPoints(int points){
        _points = points;
    }

    public abstract void promoteStatus();
    public abstract void demoteStatus();
    public abstract void updateStatus();
    public abstract String getStatus();
}