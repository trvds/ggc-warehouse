package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnavailableProductException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.app.exceptions.UnknownProductKeyException;
import ggc.exceptions.PartnerUnknownKeyException;
import ggc.exceptions.ProductUnavailableException;
import ggc.exceptions.ProductUnknownKeyException;

/**
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {

  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    addStringField("partnerId", Prompt.partnerKey());
    addStringField("productId", Prompt.productKey());
    addIntegerField("quantity", Prompt.amount());
  }

  @Override
  public final void execute() throws CommandException, UnavailableProductException {
    String partnerId = stringField("partnerId");
    String productId = stringField("productId");
    int quantity = integerField("quantity");

    try {
      _receiver.registerBreakdownTransaction(partnerId, productId, quantity);
    }
    catch (PartnerUnknownKeyException e) {
      throw new UnknownPartnerKeyException(e.getId());
    }
    catch (ProductUnknownKeyException e) {
      throw new UnknownProductKeyException(e.getId());
    }
    catch (ProductUnavailableException e) {
      throw new UnavailableProductException(e.getId(), e.getAmount(), e.getTotalStock());
    }

  }

}
