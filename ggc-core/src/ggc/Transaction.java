package ggc;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    private int _id;
    private int _paymentDate;
    private String _productId;
    private String _partnerId;
    private int _quantity;
    private double _price;

    public Transaction(int id, int paymentDate, String productId, String partnerId, int quantity, double price){
        _id = id;
        _paymentDate = paymentDate;
        _productId = productId;
        _partnerId = partnerId;
        _quantity = quantity;
        _price = price;
    }

    public int getPaymentDate(){
        return _paymentDate;
    }

    @Override
    public String toString(){
        return  _id + "|" + _partnerId + "|" + _productId + "|" + _quantity + "|" + _price;
    }
}
