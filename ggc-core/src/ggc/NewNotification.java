package ggc;

public class NewNotification extends Notification{

    public NewNotification(String productId, int productPrice){
        super(productId, productPrice);
    }

    @Override
    public String toString(){
        return "NEW|" + super.toString();
    }
}