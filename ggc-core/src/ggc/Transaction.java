package ggc;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    private int _id;
    private int _date;
    private String _productId;
    private String _partnerId;
    private int _quantity;
    private double _price;

    public Transaction(int id, int date, String productId, String partnerId, int quantity, double price){
        _id = id;
        _date = date;
        _productId = productId;
        _partnerId = partnerId;
        _quantity = quantity;
        _price = price;
    }

    public int getPaymentDate(){
        return _date;
    }

    @Override
    public String toString(){
        return  _id + "|" + _partnerId + "|" + _productId + "|" + _quantity + "|" + _price;
    }
}
