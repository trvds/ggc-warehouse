package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.exceptions.PartnerUnknownKeyException;
import ggc.app.exceptions.UnknownPartnerKeyException;
/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerAcquisitions extends Command<WarehouseManager> {

  DoShowPartnerAcquisitions(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_ACQUISITIONS, receiver);
    addStringField("partnerId", Prompt.partnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    try{
      _display.popup(_receiver.getPartnerBuyTransactions(partnerId));
    } catch (PartnerUnknownKeyException e){
      throw new UnknownPartnerKeyException(e.getId());
    }
  }
}
