package ggc;

import java.io.Serializable;
import java.lang.Math;
import java.text.Collator;

public class Batches implements Serializable, Comparable<Batches>{
    private Product _product;
    private Partner _partner;
    private int _quantity;
    private final double _price;
    
    public Batches(Product product, Partner partner, int quantity, double price){
        _product = product;
        _partner = partner;
        _quantity = quantity;
        _price = price;
    }

    public Product getProduct(){
        return _product;
    }

    public Partner getPartner(){
        return _partner;
    }

    public int getQuantity(){
        return _quantity;
    }

    public double getPrice(){
        return _price;
    }

    @Override
    public String toString(){
        return _product.getProductId() + "|" + _partner.getPartnerId() + "|" + Math.round(_price) + "|" + _quantity + "\n";
    }

    @Override
    public int compareTo(Batches comparedBatch){
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
}