package ggc;

import java.io.Serializable;
import java.lang.Math;

public class Batches implements Serializable, Comparable<Batches>{
    private Product _product;
    private Partner _partner;
    private int _quantity;
    private final float _price;
    
    public Batches(Product product, Partner partner, int quantity, float price){
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

    public float getPrice(){
        return _price;
    }

    @Override
    public String toString(){
        return _product.getProductId() + "|" + _partner.getPartnerId() + "|" + Math.round(_price) + "|" + _quantity + "\n";
    }

    @Override
    public int compareTo(Batches comparedBatch){
        String comparedProduct = comparedBatch.getProduct().getProductId();
        String comparedPartner = comparedBatch.getPartner().getPartnerId();
        float comparedPrice = comparedBatch.getPrice();
        int comparedQuantity = comparedBatch.getQuantity();

        if (comparedProduct == _product.getProductId()){
            if (comparedPartner == _partner.getPartnerId()){
                if (comparedPrice == _price){
                    return _quantity - comparedQuantity;
                }
                return Float.compare(_price, comparedPrice);
            }
            return _partner.getPartnerId().compareTo(comparedPartner);
        }
        return _product.getProductId().compareTo(comparedProduct);
    }
}