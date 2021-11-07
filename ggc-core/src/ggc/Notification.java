package ggc;
import java.io.Serializable;

public abstract class Notification implements Serializable{
    private String _productId;
    private double _productPrice;

    public Notification(String productId, double productPrice){
        _productId = productId;
        _productPrice = productPrice;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return _productId + "|" + _productPrice;
    }
}