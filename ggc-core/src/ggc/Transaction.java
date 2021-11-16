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
    

    
    /** 
     * @return String
     */
    public String getPartnerId(){
        return _partnerId;
    }


    
    /** 
     * @return String
     */
    public String getProductId(){
        return _productId;
    }


    
    /** 
     * @return int
     */
    public int getDate(){
        return _date;
    }


    
    /** 
     * @return double
     */
    public double getBasePrice(){
        return _price;
    }


    
    /** 
     * @return int
     */
    public int getPaymentDate(){
        return _date;
    }


    
    /** 
     * @param date
     */
    public void setPaymentDate(int date){
        _date = date;
    }


    
    
    
    /** 
     * @param isPaid(
     * @return String
     */
    /** 
     * @param isPaid(
     * @return String
     */
    /** 
     * @param isPaid(
     * @return String
     */
    public abstract String transactionType();

    
    
    /** 
     * @return boolean
     */
    public boolean isPaid(){
        return true;
    }

    
    /** 
     * @param getDeadline(
     */
    public void setPaid() { }


    
    /** 
     * @return int
     */
    public int getDeadline() {
        return _date;
    }
    

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return  _id + "|" + _partnerId + "|" + _productId + "|" + _quantity + "|" + Math.round(_price);
    }
}
