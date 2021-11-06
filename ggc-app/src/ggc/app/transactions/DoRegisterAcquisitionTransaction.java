package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.ArrayList;
import ggc.RecipeComponent;
import ggc.WarehouseManager;
import ggc.exceptions.PartnerUnknownKeyException;
import ggc.exceptions.ProductUnknownKeyException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.app.exceptions.UnknownProductKeyException;
import ggc.Product;
import pt.tecnico.uilib.forms.Form;
/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    addStringField("partnerId", Prompt.partnerKey());
    addStringField("productId", Prompt.productKey());
    addRealField("price", Prompt.price());
    addIntegerField("quantity", Prompt.amount());
  }

  @Override
  public final void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    String productId = stringField("productId");
    double price = realField("price");
    int quantity = integerField("quantity");

    ArrayList<RecipeComponent> recipe = new ArrayList<RecipeComponent>();
    Product product = _receiver.getProduct(productId);
    boolean addRecipe = false;
    try{
      _receiver.registerBuyTransaction(partnerId, productId, price, quantity);
    } catch( PartnerUnknownKeyException e){
      throw new UnknownPartnerKeyException(e.getId());
    } catch (ProductUnknownKeyException e){
      addRecipe = Form.confirm(Prompt.addRecipe());
    }

    if (addRecipe == true){
      int numberOfComponents = Form.requestInteger(Prompt.numberOfComponents());

      for (int i = 0; i < numberOfComponents; i++){
        Product productrecipe = _receiver.getProduct(Form.requestString(Prompt.productKey()));
        int productquantity = Form.requestInteger(Prompt.amount());
        recipe.add(new RecipeComponent(productrecipe, productquantity));
      }

      double alpha = Form.requestReal(Prompt.alpha());
      _receiver.registerProduct(productId, quantity, price, alpha, recipe);
    }
    else
      _receiver.registerProduct(productId, quantity, price);

    try{      
      _receiver.registerBuyTransaction(partnerId, productId, price, quantity);
    }catch (ProductUnknownKeyException e){
      throw new UnknownProductKeyException(e.getId());
    }catch (PartnerUnknownKeyException e){
      throw new UnknownPartnerKeyException(e.getId());
    }

  }
}
