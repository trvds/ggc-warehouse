package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {

  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
<<<<<<< Updated upstream
    //FIXME maybe add command fields 
=======
    addStringField("partnerId", Prompt.partnerKey());
    addRealField("paymentDeadline", Prompt.paymentDeadline());
    addIntegerField("transactionAmount", Prompt.amount());
>>>>>>> Stashed changes
  }

  @Override
  public final void execute() throws CommandException {
    /*TODO
    Para registar uma venda, é pedido o identificador do parceiro,
     a data limite para o pagamento (Prompt.paymentDeadline()), o identificador do produto a vender 
     e a respectiva quantidade (Prompt.amount()). Se a quantidade for superior às existências actuais,
      deve ser lançada a excepção UnavailableProductException (não se realiza a venda).

 */
    String partnerId = stringField("partnerId");
    double paymentDeadline = realField("paymentDeadline");
    int amount = integerField("transactionAmount");
    _receiver.registerSaleTransaction(partnerId, paymentDeadline, amount);

  }

}
