package ggc;
import java.io.Serializable;

public interface DeliveryMode extends Serializable {
    Notification sendNotification(String productId, double price, String event);
}