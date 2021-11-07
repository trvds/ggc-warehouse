package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    addStringField("productId", Prompt.productKey());
    addStringField("partnerId", Prompt.productKey());
  }

  @Override
  public void execute() throws CommandException {
    String productId = stringField("productId");
    String partnerId = stringField("partnerId");
    _receiver.toggleNotifications(productId, partnerId);
  }
}
