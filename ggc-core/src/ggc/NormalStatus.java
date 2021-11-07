package ggc;

public class NormalStatus extends Status{
    
    public NormalStatus(Partner partner, int points){
        super(partner, points);
    }

    public void updateStatus(){
        if (getPoints() > 2000){
            promoteStatus();
        }
    }

    public void promoteStatus(){
        getPartner().setStatus(new SelectionStatus(getPartner(), getPoints()));
    }

    
    /** 
     * @param getStatus(
     */
    public void demoteStatus(){ }
    
    
    /** 
     * @return String
     */
    @Override
    public String getStatus(){
        return "NORMAL";
    }
}