package ggc;

public class SelectionStatus extends Status{
    
    public SelectionStatus(Partner partner, int points){
        super(partner, points);
    }

    public void updateStatus(){
        if (getPoints() > 25000){
            promoteStatus();
        }
        else if(getPoints() <= 2000){
            demoteStatus();
        }
    }

    public void promoteStatus(){
        getPartner().setStatus(new EliteStatus(getPartner(), getPoints()));
    }

    public void demoteStatus(){
        getPartner().setStatus(new NormalStatus(getPartner(), getPoints()));
    }
      
    
    
    /** 
     * @return String
     */
    @Override
    public String getStatus(){
        return "SELECTION";
    }

}