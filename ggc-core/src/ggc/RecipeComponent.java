package ggc;
import java.io.Serializable;

public class RecipeComponent implements Serializable{
    private Product _product;
    private int _productQuantity;

    public RecipeComponent(Product product, int productQuantity){
        _product = product;
        _productQuantity = productQuantity;
    }
}