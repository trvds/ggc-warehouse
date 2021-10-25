package ggc;
import java.io.Serializable;

public class Product implements Serializable{
    private String _id;
    private int _totalStock;
    private float _maxPrice;

    public Product(String id){
        _id = id;
    }

    public String getProductId(){
        return _id;
    }

    public void setTotalStock(int totalStock){
        _totalStock = totalStock;
    }

    public void setMaxPrice(float maxPrice){
        _maxPrice = maxPrice;
    }

    @Override
    public String toString(){
        return _id + "|" + _totalStock + "|" + _maxPrice;
    }

}
