package ggc;

import java.lang.Math;

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


    /**
     * Calculates the price of the product for payment and how many points to deduct from the partner for missing payment deadline
     * @param date - current date
     * @param n - interval for payment windows
     * @param deadline - payment deadline
     * @param price - base price
     */
    @Override
    public double calculatePrice(int date, int n, int deadline, double price){
        int p1 = deadline - n;
        int p4 = deadline + n;
        
        if(date <= p1){
            price *= 0.9; // desconto 10%
        }
        else if(date > p1 && date <= deadline){
            if (date <= (deadline - 2))
                price *= 0.95; // desconto 5% por dois dias antes da data limite
            // sem desconto se o pagamento ocorrer entre a deadline e dois dias antes
        }
        else if(date <= p4 && date > deadline){
            if (date > (deadline + 1))
                price *= (1 + 0.02*(date - deadline)); // multa 2% por dia após 1 dia   
        }
        else if(date >= p4){
            price *= (1 + 0.05*(date - deadline)); // multa 5% por dia
        }
        
        if(date >= (deadline + 2)){
            setPoints((int)(getPoints() * 0.10)); // remover 90% dos pontos por atraso de 2 dias;
            demoteStatus(); // dar demote do partner;
        } else updateStatus();
        return price;
    }
}