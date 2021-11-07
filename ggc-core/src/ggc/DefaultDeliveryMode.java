package ggc;

public class DefaultDeliveryMode implements DeliveryMode{
    private static final long serialVersionUID = 202012040059L;


    
    /** 
     * @param productId
     * @param price
     * @param event
     * @return Notification
     */
    @Override
    public Notification deliverNotification(String productId, double price, String event) {
        Notification notification = null;
        if(event == "NEW")
            notification = new NewNotification(productId, price);
        if(event == "BARGAIN")
            notification = new BargainNotification(productId, price);
        return notification;
    }
}
