package ggc;

public class SellTransaction extends Transaction{
    private int _paymentDeadline;
    private double _paymentPrice;
    private boolean _paid = false;

    public SellTransaction(int id, int date, String productId, String partnerId, int quantity, double price, int paymentDeadline) {
        super(id, date, productId, partnerId, quantity, price);
        _paymentDeadline = paymentDeadline;
        _paymentPrice = price;
    }

    public String transactionType(){
        return "VENDA";
    }

    @Override
    public String toString(){
        return transactionType() + "|" + super.toString() + "|" + _paymentPrice + "|" + _paymentDeadline + "|" + getPaymentDate();
    }

}
