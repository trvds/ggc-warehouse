package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.app.exceptions.UnknownTransactionKeyException;
import ggc.exceptions.TransactionUnknownKeyException;

/**
 * Receive payment for sale transaction.
 */
public class DoReceivePayment extends Command<WarehouseManager> {

  public DoReceivePayment(WarehouseManager receiver) {
    super(Label.RECEIVE_PAYMENT, receiver);
    addIntegerField("id", Prompt.transactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    int id = integerField("id");
    try{
      _receiver.payTransaction(id);
    }catch (TransactionUnknownKeyException e){
      throw new UnknownTransactionKeyException(e.getId());
    }

  }

}
