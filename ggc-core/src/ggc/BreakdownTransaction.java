package ggc;

public class BreakdownTransaction extends Transaction{
    private double _paymentPrice;
    private int _dateDeadline;
    private String _recipe;


    public BreakdownTransaction(int id, int paymentDate, String productId, String partnerId, int quantity, double price, String recipe){
        super(id, paymentDate, productId, partnerId, quantity, price);
        _recipe = recipe; 
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
        return transactionType() + "|" + super.toString() + "|" + _paymentPrice + "|" + _dateDeadline + "|" + getPaymentDate() + "|" + _recipe;
    }

}