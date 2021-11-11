package ggc;
import java.lang.Math;

public class BreakdownTransaction extends Transaction {
    private double _paymentPrice;
    private String _recipe;


    public BreakdownTransaction(int id, int date, String productId, String partnerId, int quantity, double price, double paymentPrice, String recipe){
        super(id, date, productId, partnerId, quantity, price);
        _recipe = recipe; 
        _paymentPrice = paymentPrice;
    }

    
    /** 
     * @return String
     */
    public String transactionType(){
        return "DESAGREGAÇÃO";
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return transactionType() + "|" + super.toString() + "|" + Math.round(_paymentPrice) + "|" + getDate()  + "|" + _recipe;
    }

}