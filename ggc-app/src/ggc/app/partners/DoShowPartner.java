package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.exceptions.PartnerUnknownKeyException;

import ggc.WarehouseManager;
//FIXME import classes

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    addStringField("id", Prompt.partnerKey());
  }

  @Override
  public void execute() throws CommandException {
    try{
      String id = stringField("id");
      _display.popup(Message.showPartner(_receiver.getPartner(id)));
    } catch (PartnerUnknownKeyException e){
      throw new UnknownPartnerKeyException(e.getId());
    }
  }

}
