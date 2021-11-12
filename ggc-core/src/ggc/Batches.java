package ggc;

import java.io.Serializable;
import java.lang.Math;
import java.text.Collator;
import java.util.Comparator;

public class Batches implements Serializable, Comparable<Batches>{
    private Product _product;
    private Partner _partner;
    private int _quantity;
    private final double _price;
    
    public final static Comparator<Batches> PRICE_COMPARATOR = new PriceComparator();

    public Batches(Product product, Partner partner, int quantity, double price){
        _product = product;
        _partner = partner;
        _quantity = quantity;
        _price = price;
    }

    
    /** 
     * @return Product
     */
    public Product getProduct(){
        return _product;
    }

    
    /** 
     * @return Partner
     */
    public Partner getPartner(){
        return _partner;
    }

    
    /** 
     * @return int
     */
    public int getQuantity(){
        return _quantity;
    }

    /**
     * @return double
     */
    public double getPrice() {
        return _price;
    }


    /** 
     * @return double
     */
    public void withdraw(int amount) {
        if (amount <= _quantity) {
            _quantity -= amount;
        }
        else {
            //Undefined behaviour
        }
    }


    /** 
     * @return String
     */
    @Override
    public String toString() {
        return _product.getProductId() + "|" + _partner.getPartnerId() + "|" + Math.round(_price) + "|" + _quantity + "\n";
    }

    
    /** 
     * @param comparedBatch
     * @return int
     */
    @Override
    public int compareTo(Batches comparedBatch) {
        Collator myCollator = Collator.getInstance();
        String comparedProduct = comparedBatch.getProduct().getProductId();
        String comparedPartner = comparedBatch.getPartner().getPartnerId();
        double comparedPrice = comparedBatch.getPrice();
        int comparedQuantity = comparedBatch.getQuantity();

        if (comparedProduct == _product.getProductId()){
            if (comparedPartner == _partner.getPartnerId()){
                if (comparedPrice == _price){
                    return _quantity - comparedQuantity;
                }
                return Double.compare(_price, comparedPrice);
            }
            return myCollator.compare(_partner.getPartnerId(), comparedPartner);
        }
        return myCollator.compare(_product.getProductId(), comparedProduct);
    }
    
    private static class PriceComparator implements Comparator<Batches> {
        public int compare(Batches batch1, Batches batch2) {
            int delta = (int) Math.round(batch1.getPrice() - batch2.getPrice());
            return delta;
       }
    }
}


