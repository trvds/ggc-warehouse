package ggc;

public class Batches{
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


}