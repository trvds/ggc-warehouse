package ggc;

public class BuyTransaction extends Transaction{

    public BuyTransaction(int id, int paymentDate, String productId, String partnerId, int quantity, double price){
        super(id, paymentDate, productId, partnerId, quantity, price);
    }

    public String transactionType(){
        return "COMPRA";
    }

    @Override
    public String toString(){
        return transactionType() + "|" + super.toString() + getPaymentDate();
    }

}




