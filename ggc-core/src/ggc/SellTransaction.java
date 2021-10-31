package ggc;

public class SellTransaction extends Transaction{
    private int _dateDeadline;
    private float _paymentPrice;
    private boolean _payed = false;

    public SellTransaction(int id, int paymentDate, String productId, String partnerId, int quantity, int price){
        super(id, paymentDate, productId, partnerId, quantity, price);
    }

    @Override
    public String toString(){
        return "VENDA|" + super.toString() + "|" + _paymentPrice + "|" + _dateDeadline + "|" + getPaymentDate();
    }

}
