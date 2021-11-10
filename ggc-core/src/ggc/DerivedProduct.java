package ggc;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
import ggc.exceptions.ProductUnavailableException;

public class DerivedProduct extends Product {
    private ArrayList<RecipeComponent> _recipe;
    private double _alpha;

    public DerivedProduct(String name, double maxPrice, int totalStock, ArrayList<RecipeComponent> recipe, double alpha){
        super(name, maxPrice, totalStock);
        _recipe = recipe;
        _alpha = alpha;
    }
    
    /**
     * @return String
     */
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
    public double dummyDispatchProduct(int amount, double totalPrice, Map<String, TreeSet<Batches>> batches) throws ProductUnavailableException {
        TreeSet<Batches> productBatches = batches.get(this.getProductId());
        Set<Batches> orderedByPrice = new TreeSet<Batches>(Batches.PRICE_COMPARATOR);
        orderedByPrice.addAll(productBatches);

        int fulfilledAmount = 0;

        for (Batches b: orderedByPrice) {
            if (b.getQuantity() > amount - fulfilledAmount) { //More than we need to complete
                productBatches.remove(b); //Remove OG batch
                fulfilledAmount += amount - fulfilledAmount; // <=> fullfilledAmount = amount ; we're done here
                b.withdraw(amount - fulfilledAmount);
                totalPrice += b.getPrice() / (amount - fulfilledAmount);
                productBatches.add(b); //Add our modified batch - replacing the OG one
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
            int neededAmount = amount - fulfilledAmount;
            double recipeBasePrice = 1.0 + _alpha;
            double recipePrice = 0; //: PH2O=(1+α)×(2×PH+PO)
            //
            for (RecipeComponent component: _recipe) {
                Product componentProduct = component.getProduct();
                int componentProductQuantity = component.getProductQuantity();
                int havecomponentAmount = componentProduct.getTotalStock();
                int neededComponentAmount = (neededAmount * componentProductQuantity) - havecomponentAmount;

                double componentPrice = dummyDispatchProduct(neededComponentAmount, 0, batches);

                recipePrice += componentProductQuantity * componentPrice;
            }
            totalPrice += recipeBasePrice * recipePrice;
        }
        return totalPrice;
    }


    /** 
     * @return String
     */
    @Override
    public String toString(){
        return super.toString() + "|" + _alpha + "|" + getAllComponents();
    }
}