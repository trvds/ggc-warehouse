package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exceptions.DuplicatePartnerKeyException;
import ggc.exceptions.PartnerDuplicateKeyException;

import ggc.WarehouseManager;


/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    addStringField("id", Prompt.partnerKey());
    addStringField("name", Prompt.partnerName());
    addStringField("adress", Prompt.partnerAddress());
  }

  @Override
  public void execute() throws CommandException {
    try{
      String id = stringField("id");
      String name = stringField("name");
      String adress = stringField("adress");
      _receiver.registerPartner(id, name, adress);
    } catch (PartnerDuplicateKeyException e){
      throw new DuplicatePartnerKeyException(e.getId());
    }
  }

}
