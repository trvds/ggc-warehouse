package ggc;

public class BargainNotification extends Notification{

    public BargainNotification(String productId, double productPrice){
        super(productId, productPrice);
    }

    @Override
    public String toString(){
        return "BARGAIN|" + super.toString();
    }
}