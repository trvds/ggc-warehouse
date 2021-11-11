package ggc;

public class StandardDeliveryMode implements DeliveryMode{
    /** 
     * @param productId
     * @param price
     * @param event
     * @return Notification
     */
    @Override
    public Notification sendNotification(String productId, double price, String event) {
        Notification notification = null;
        if(event == "NEW")
            notification = new NewNotification(productId, price);
        if(event == "BARGAIN")
            notification = new BargainNotification(productId, price);
        return notification;
    }
}
