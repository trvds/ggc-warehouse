package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.exceptions.ProductUnknownKeyException;
import ggc.app.exceptions.UnknownProductKeyException;

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {

  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);
    addStringField("id", Prompt.productKey());
  }

  @Override
  public final void execute() throws CommandException {
    try{
      String id = stringField("id");
      _display.popup(_receiver.getBatchesByProduct(id));
    } catch(ProductUnknownKeyException e) {
      throw new UnknownProductKeyException(e.getId());
    }
  }

}
