package ggc;

import java.lang.Math;

public class SellTransaction extends Transaction{
    private int _paymentDeadline;
    private double _paymentPrice;
    private boolean _paid = false;

    public SellTransaction(int id, int date, String productId, String partnerId, int quantity, double price, int paymentDeadline) {
        super(id, date, productId, partnerId, quantity, price);
        _paymentDeadline = paymentDeadline;
        _paymentPrice = price;
    }

    
    /** 
     * @return String
     */
    public String transactionType(){
        return "VENDA";
    }

    @Override
    public boolean isPaid(){
        return _paid;
    }

    @Override
    public int getDeadline(){
        return _paymentDeadline;
    }

    @Override
    public void setPaid(){
        _paid = true;
    }
    /** 
     * @return String
     */
    @Override
    public String toString(){
        if (_paid)
            return transactionType() + "|" + super.toString() + "|" + Math.round(_paymentPrice) + "|" + getDeadline() + "|" + getPaymentDate();
        return transactionType() + "|" + super.toString() + "|" + Math.round(_paymentPrice) + "|" + getDeadline();
    }

}
