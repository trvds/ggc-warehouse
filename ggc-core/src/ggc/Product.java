package ggc;
import java.util.HashMap;
import java.lang.Math;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
import ggc.Batches;
import ggc.exceptions.ProductUnavailableException;

public class Product implements ObservableProduct {
    private String _id;
    private int _totalStock;
    private double _maxPrice;
    private double _maxHistoricPrice; //TODO

    private HashMap<ProductObserver, Boolean> _observers = new HashMap<ProductObserver, Boolean>();

    public Product(String id, double maxPrice, int totalStock){
        _id = id;
        _totalStock = totalStock;
        _maxPrice = maxPrice;
    }

    public double dummyDispatchProduct(int amount, double totalPrice, Map<String, TreeSet<Batches>> batches) throws ProductUnavailableException {
        TreeSet<Batches> productBatches = batches.get(this.getProductId());
        Set<Batches> orderedByPrice = new TreeSet<Batches>(Batches.PRICE_COMPARATOR);
        orderedByPrice.addAll(productBatches);

        int fulfilledAmount = 0;

        for (Batches b: orderedByPrice) {
            if (b.getQuantity() > amount - fulfilledAmount) { //More than we need to complete
                productBatches.remove(b); // Remove original batch
                fulfilledAmount += amount - fulfilledAmount; // <=> fullfilledAmount = amount ; we're done here
                b.withdraw(amount - fulfilledAmount);
                totalPrice += b.getPrice() / (amount - fulfilledAmount);
                productBatches.add(b); //Insert our modified batch, replacing OG
                break;
            }
            else if (b.getQuantity() == amount - fulfilledAmount) { //Just what we need - consume, destroy and leave
                fulfilledAmount = amount;
                totalPrice += b.getPrice();
                productBatches.remove(b);
                break;
            }
            else { //Not enough quantity in this batch - consume all, destroy and continue
                fulfilledAmount += b.getQuantity();
                totalPrice += b.getPrice();
                productBatches.remove(b);
            }
        }
        if (fulfilledAmount != amount) { //What we had was not enough for a simple product, throw Exception
            throw new ProductUnavailableException(getProductId(), amount, fulfilledAmount);
        }
        return totalPrice;
    }

    public double dispatchProduct(int amount, double totalPrice, Map<String, TreeSet<Batches>> batches) {
        TreeSet<Batches> productBatches = batches.get(this.getProductId());
        Set<Batches> orderedByPrice = new TreeSet<Batches>(Batches.PRICE_COMPARATOR);
        orderedByPrice.addAll(productBatches);

        int haveAmount = 0;
        for (Batches b: orderedByPrice) {
                if (b.getQuantity() > amount - haveAmount) {
                    //We have more in stock than we need - we're done here
                    b.withdraw(amount-haveAmount);
                    haveAmount += b.getQuantity();
                    totalPrice += b.getPrice() / (amount - haveAmount);
                    return totalPrice;
                }
                else if (b.getQuantity() <= amount - haveAmount) { //FIXME unnecessary condition?
                    //We have less than we need, destroy batch and move on to next batch
                    haveAmount += b.getQuantity();
                    totalPrice += b.getPrice();
                    productBatches.remove(b);
                } 
            }
        return totalPrice;
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
