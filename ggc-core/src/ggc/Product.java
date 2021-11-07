package ggc;
import java.io.Serializable;
import java.util.HashMap;
import java.lang.Math;

public class Product implements Serializable, ObservableProduct{
    private String _id;
    private int _totalStock;
    private double _maxPrice;
    private HashMap<ProductObserver, Boolean> _observers = new HashMap<ProductObserver, Boolean>();

    public Product(String id, double maxPrice, int totalStock){
        _id = id;
        _totalStock = totalStock;
        _maxPrice = maxPrice;
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

    public void toggleNotifications(ProductObserver observer){
        if (_observers.get(observer) == true)
            removeObserver(observer);
        else
            registerObserver(observer);
    }

    @Override
    public void registerObserver(ProductObserver observer){
        _observers.put(observer, true);
    }

    @Override
    public void removeObserver(ProductObserver observer){
        _observers.put(observer, false);
    }

    @Override
    public void notify(double price, String event){
        for (HashMap.Entry<ProductObserver, Boolean> observer : _observers.entrySet()) {
            if (observer.getValue() == true)
                observer.getKey().update(_id, price, event);
        }
    }

    @Override
    public String toString(){
        return _id + "|" + Math.round(_maxPrice) + "|" + _totalStock;
    }

}
