package ggc;

public class BreakdownTransaction extends Transaction{
    private float _paymentPrice;
    private int _dateDeadline;
    private String _recipe;

    public BreakdownTransaction(int id, int paymentDate, String productId, String partnerId, int quantity, int price, String recipe){       
        super(id, paymentDate, productId, partnerId, quantity, price);
        _recipe = recipe; 
    }

    @Override
    public String toString(){
        return "DESAGREGAÇÃO|" + super.toString() + "|" + _paymentPrice + "|" + _dateDeadline + "|" + getPaymentDate() + "|" + _recipe;
    }

}