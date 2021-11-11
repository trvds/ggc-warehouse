package ggc;

import java.io.Serializable;
import java.lang.Math;

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

    
    /** 
     * @return int - id of the Transaction
     */
    public int getId(){
        return _id;
    }
    
    public String getPartnerId(){
        return _partnerId;
    }

    public String getProductId(){
        return _productId;
    }

    public int getDate(){
        return _date;
    }


    public double getBasePrice(){
        return _price;
    }

    /** 
     * @return int - date of the payment
     */
    public int getPaymentDate(){
        return _date;
    }

    public void setPaymentDate(int date){
        _date = date;
    }

    
    /** 
     * @return String - type of the transaction ("COMPRA", "VENDA", "DESAGREGAÇÃO")
     */
    public abstract String transactionType();

    
    public boolean isPaid(){
        return true;
    }

    public void setPaid() { };
    public int getDeadline() {return _date;};
    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return  _id + "|" + _partnerId + "|" + _productId + "|" + _quantity + "|" + Math.round(_price);
    }
}
