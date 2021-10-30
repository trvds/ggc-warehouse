package ggc;

public class BreakdownTransaction extends Transaction{
    private int _paymentPrice;

    public BreakdownTransaction(int id, int paymentDate, String productId, String partnerId, int quantity, int price){
        super(id, paymentDate, productId, partnerId, quantity, price);
    }

}