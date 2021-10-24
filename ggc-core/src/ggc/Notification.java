package ggc;
import java.io.Serializable;

public abstract class Notification implements Serializable{
    private String _productId;
    private int _productPrice;

    public Notification(String productId, int productPrice){
        _productId = productId;
        _productPrice = productPrice;
    }

    @Override
    public String toString(){
        return _productId + "|" + _productPrice;
    }
}