package ggc;
import java.util.ArrayList;

public class DerivedProduct extends Product{
    private ArrayList<RecipeComponent> _recipe;
    private float _alpha;

    public DerivedProduct(String name, ArrayList<RecipeComponent> recipe, float alpha){
        super(name);
        _recipe = recipe;
        _alpha = alpha;
    }
}