package ggc;

public class SellTransaction extends Transaction{
    private int _paymentDeadline;
    private float _paymentPrice;
    private boolean _payed = false;

    public SellTransaction(int id, int date, String productId, String partnerId, int quantity, float price, int paymentDeadline) {
        super(id, date, productId, partnerId, quantity, price);
        _paymentDeadline = paymentDeadline;
        _paymentPrice = price;
    }

    @Override
    public String toString(){
        return "VENDA|" + super.toString() + "|" + _paymentPrice + "|" + _paymentDeadline + "|" + getPaymentDate();
    }

}
