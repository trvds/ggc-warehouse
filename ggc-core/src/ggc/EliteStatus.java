package ggc;

public class EliteStatus extends Status{
    
    public EliteStatus(Partner partner, int points){
        super(partner, points);
    }

    public void updateStatus(){
        if(getPoints() <= 25000){
            demoteStatus();
        }
    }

    
    /** 
     * @param demoteStatus(
     */
    public void promoteStatus(){ }

    public void demoteStatus(){
        getPartner().setStatus(new NormalStatus(getPartner(), getPoints()));
    }

    
    /** 
     * @return String
     */
    @Override
    public String getStatus(){
        return "ELITE";
    }
}