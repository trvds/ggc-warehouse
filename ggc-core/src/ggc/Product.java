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

    
    /** 
     * @return String
     */
    public String getProductId(){
        return _id;
    }

    
    /** 
     * @return int
     */
    public int getTotalStock() {
        return _totalStock;
    }

    
    /** 
     * @param totalStock
     */
    public void setTotalStock(int totalStock){
        _totalStock = totalStock;
    }

    
    /** 
     * @param maxPrice
     */
    public void setMaxPrice(double maxPrice){
        _maxPrice = maxPrice;
    }

    
    /** 
     * @return double
     */
    public double getMaxPrice() {
        return _maxPrice;
    }

    
    /** 
     * @param observer
     */
    public void toggleNotifications(ProductObserver observer){
        if (_observers.get(observer) == true)
            removeObserver(observer);
        else
            registerObserver(observer);
    }

    
    /** 
     * @param observer
     */
    @Override
    public void registerObserver(ProductObserver observer){
        _observers.put(observer, true);
    }

    
    /** 
     * @param observer
     */
    @Override
    public void removeObserver(ProductObserver observer){
        _observers.put(observer, false);
    }

    
    /** 
     * @param price
     * @param event
     */
    @Override
    public void notify(double price, String event){
        for (HashMap.Entry<ProductObserver, Boolean> observer : _observers.entrySet()) {
            if (observer.getValue() == true)
                observer.getKey().update(_id, price, event);
        }
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return _id + "|" + Math.round(_maxPrice) + "|" + _totalStock;
    }

}
