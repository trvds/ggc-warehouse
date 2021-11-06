package ggc;
import java.util.ArrayList;

public class DerivedProduct extends Product{
    private ArrayList<RecipeComponent> _recipe;
    private double _alpha;

    public DerivedProduct(String name, ArrayList<RecipeComponent> recipe, double alpha){
        super(name);
        _recipe = recipe;
        _alpha = alpha;
    }

    public String getAllComponents(){
        String returnString = "";

        for(int i = 0; i < _recipe.size() - 1; i++){
            returnString += _recipe.get(i).toString();
            returnString += "#";
        }
        returnString += _recipe.get(_recipe.size() - 1).toString();

        return returnString;
    }

    @Override
    public String toString(){
        return super.toString() + getAllComponents();
    }
}