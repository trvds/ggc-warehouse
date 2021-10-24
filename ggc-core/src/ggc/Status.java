package ggc;
import java.io.Serializable;

public abstract class Status implements Serializable{
    public void promoteStatus(Partner partner) { };
    public void demoteStatus(Partner partner) { };
    public String getStatus() {return "STATUS";};
}