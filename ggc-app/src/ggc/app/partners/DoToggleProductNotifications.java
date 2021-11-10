package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.exceptions.PartnerUnknownKeyException;
import ggc.exceptions.ProductUnknownKeyException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.app.exceptions.UnknownProductKeyException;

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    addStringField("partnerId", Prompt.partnerKey());
    addStringField("productId", Prompt.productKey());
  }

  @Override
  public void execute() throws CommandException {
    String productId = stringField("productId");
    String partnerId = stringField("partnerId");
    try{
      _receiver.toggleNotifications(productId, partnerId);
    } catch (PartnerUnknownKeyException e){
      throw new UnknownPartnerKeyException(e.getId());
    } catch (ProductUnknownKeyException e){
      throw new UnknownProductKeyException(e.getId());
    }
  }
}
