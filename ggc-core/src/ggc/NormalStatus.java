package ggc;

import java.lang.Math;

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

    @Override
    /**
     * Calculates the price of the product for payment and how many points to deduct from the partner for missing payment deadline
     * @param date - current date
     * @param n - interval for payment windows
     * @param deadline - payment deadline
     * @param price - base price
     */
    public double calculatePrice(int date, int n, int deadline, double price){
        int p1 = deadline - n;
        int p4 = deadline + n;
        
        if(date <= p1){
            price *= 0.9; // desconto 10%
        }
        else if(date > p1 && date <= deadline){
            // sem desconto
        }
        else if(date <= p4 && date > deadline){
            price *= (1 + 0.05*(date - deadline)); //multa de 5% por dia
        }
        else if(date >= p4){
            price *= (1 + 0.1*(date - deadline)); //multa de 10% por dia
        }
        
        if(date >= deadline){
            setPoints(0); // remover todos pontos por atraso;
        }
        return price;
    }
}