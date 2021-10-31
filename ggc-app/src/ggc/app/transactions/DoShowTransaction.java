package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnknownTransactionKeyException;
import ggc.exceptions.TransactionUnknownKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    addIntegerField("id", Prompt.transactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    try{
      int id = integerField("id");
      _display.popup(_receiver.getTransaction(id));
    } catch (TransactionUnknownKeyException e){
      new UnknownTransactionKeyException(e.getId());
    }
  }

}
