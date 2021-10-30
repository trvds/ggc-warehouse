package ggc;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    private int _id;
    private int _paymentDate;
    private String _productId;
    private String _partnerId;
    private int _quantity;
    private float _price;

    public Transaction(int id, int paymentDate, String productId, String partnerId, int quantity, int price){
        _id = id;
        _paymentDate = paymentDate;
        _productId = productId;
        _partnerId = partnerId;
        _quantity = quantity;
        _price = price;
    }

}
