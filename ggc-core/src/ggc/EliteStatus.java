package ggc;

import java.lang.Math;

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
            price *= 0.9; // desconto 10%
        }
        else if(date <= p4 && date > deadline){
            price *= 0.95; // desconto 5%
        }
        else if(date >= p4){
            // sem multa
        }

        if(date >= (deadline + 15)){
            setPoints((int)(getPoints() * 0.75)); // remover 75% dos pontos por atraso de 2 dias;
            demoteStatus(); // dar demote do partner;
        } else updateStatus();
        return price;
    }
}