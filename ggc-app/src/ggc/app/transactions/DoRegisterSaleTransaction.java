package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.exceptions.PartnerUnknownKeyException;
import ggc.exceptions.ProductUnavailableException;
import ggc.exceptions.ProductUnknownKeyException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.app.exceptions.UnknownProductKeyException;
import ggc.app.exceptions.UnavailableProductException;
/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {

  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    addStringField("partnerId", Prompt.partnerKey());
    addIntegerField("paymentDeadline", Prompt.paymentDeadline());
    addStringField("productId", Prompt.productKey());
    addIntegerField("transactionAmount", Prompt.amount());
  }

  @Override
  public final void execute() throws CommandException {

    String partnerId = stringField("partnerId");
    int paymentDeadline = integerField("paymentDeadline");
    String productId = stringField("productId");
    int amount = integerField("transactionAmount");
    try {
      _receiver.registerSaleTransaction(partnerId, productId, paymentDeadline, amount);

    }
    catch (ProductUnknownKeyException e){
      throw new UnknownProductKeyException(e.getId());
    }
    catch (PartnerUnknownKeyException e) {
      throw new UnknownPartnerKeyException(e.getId());
    }
    catch (ProductUnavailableException e) {
      throw new UnavailableProductException(e.getId(), e.getAmount(), e.getTotalStock());
    } 
  }

}
