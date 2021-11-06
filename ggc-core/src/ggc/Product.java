package ggc;
import java.io.Serializable;
import java.lang.Math;

public class Product implements Serializable{
    private String _id;
    private int _totalStock;
    private double _maxPrice;

    public Product(String id){
        _id = id;
    }

    public String getProductId(){
        return _id;
    }

    public int getTotalStock() {
        return _totalStock;
    }

    public void setTotalStock(int totalStock){
        _totalStock = totalStock;
    }

    public void setMaxPrice(double maxPrice){
        _maxPrice = maxPrice;
    }

    public double getMaxPrice() {
        return _maxPrice;
    }

    @Override
    public String toString(){
        return _id + "|" + Math.round(_maxPrice) + "|" + _totalStock;
    }

}
