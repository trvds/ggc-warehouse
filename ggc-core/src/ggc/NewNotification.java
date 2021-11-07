package ggc;

public class NewNotification extends Notification{

    public NewNotification(String productId, double productPrice){
        super(productId, productPrice);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "NEW|" + super.toString();
    }
}