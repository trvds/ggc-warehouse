package ggc;

public class BuyTransaction extends Transaction{

    public BuyTransaction(int id, int paymentDate, String productId, String partnerId, int quantity, double price){
        super(id, paymentDate, productId, partnerId, quantity, price);
    }

    @Override
    public String toString(){
        return "COMPRA|" + super.toString() + getPaymentDate();
    }

}




