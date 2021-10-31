package ggc;

public class BreakdownTransaction extends Transaction{
    private float _paymentPrice;
    private int _dateDeadline;

    public BreakdownTransaction(int id, int paymentDate, String productId, String partnerId, int quantity, int price){
        super(id, paymentDate, productId, partnerId, quantity, price);
    }

    @Override
    public String toString(){
        return "DESAGREGAÇÃO|" + super.toString() + "|" + _paymentPrice + "|" + _dateDeadline + "|" + getPaymentDate();
    }

}