package ggc;

public class SellTransaction extends Transaction{
    private int _dateDeadline;
    private int _paymentPrice;
    private boolean _payed = false;

    public SellTransaction(int id, int paymentDate, String productId, String partnerId, int quantity, int price){
        super(id, paymentDate, productId, partnerId, quantity, price);
    }

}
