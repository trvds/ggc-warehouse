package ggc;
import java.util.HashMap;
import java.lang.Math;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;

import ggc.Batches;
import ggc.exceptions.ProductUnavailableException;
import java.util.ArrayList;

public class Product implements ObservableProduct {
    private String _id;
    private int _totalStock;
    private double _maxPrice;
    private int _n = 5;

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


    public double getMaxPrice() {
        return _maxPrice;
    }


    public void setMaxPrice(double maxPrice){
        _maxPrice = maxPrice;
    }


    public String getAllComponents() {
        return "";
    }


    public int getN(){
        return _n;
    }


    protected void setN(int n){
        _n = n;
    }
    

    public ArrayList<RecipeComponent> getRecipe() {
        ArrayList<RecipeComponent> emptyRecipe = new ArrayList<RecipeComponent>();
        return emptyRecipe;
    }


    public void canDispatchProduct(int amount, Map<String, Integer> productsStock) throws ProductUnavailableException{
        int totalStock = productsStock.get(getProductId());
        
        if (totalStock < amount)
            throw new ProductUnavailableException(getProductId(), amount, totalStock);
        
        productsStock.remove(getProductId());
        productsStock.put(getProductId(), totalStock - amount);
    }


    public double doDispatchProduct(int amount, double totalPrice, Map<String, TreeSet<Batches>> batches) throws ProductUnavailableException {
        TreeSet<Batches> productBatches = batches.get(this.getProductId());
        Set<Batches> orderedByPrice = new TreeSet<Batches>(Batches.PRICE_COMPARATOR);
        orderedByPrice.addAll(productBatches);

        int fulfilledAmount = 0;

        for (Batches b: orderedByPrice) {
            int takeAmount = amount - fulfilledAmount;
            
            if (b.getQuantity() > takeAmount) { //More than we need to complete
                totalPrice += b.getPrice() * takeAmount;
                fulfilledAmount = amount; // <=> fulfilledAmount += takeAmount ; we're done here
                b.withdraw(takeAmount);
                setTotalStock(getTotalStock() - takeAmount);
                break;

            } else if (b.getQuantity() == takeAmount) { //Just what we need - consume, destroy and leave
                fulfilledAmount = amount;
                totalPrice += b.getPrice() * b.getQuantity();
                productBatches.remove(b);
                setTotalStock(getTotalStock() - b.getQuantity());
                break;
            
            }else { //Not enough quantity in this batch - consume all, destroy and continue
                fulfilledAmount += b.getQuantity();
                totalPrice += b.getPrice() * b.getQuantity();
                productBatches.remove(b);
                setTotalStock(getTotalStock() - b.getQuantity());
            }
        }

        if (fulfilledAmount != amount) { //What we had was not enough for a simple product, throw Exception
            throw new ProductUnavailableException(getProductId(), amount, fulfilledAmount);
        }
        return totalPrice;
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