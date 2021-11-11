package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.exceptions.PartnerUnknownKeyException;

/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {

  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    addStringField("partnerId", Prompt.partnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    try{
      _display.popup(_receiver.getPartnerPaidTransactions(partnerId));
    } catch (PartnerUnknownKeyException e){
      throw new UnknownPartnerKeyException(e.getId());
    }
  }

}
