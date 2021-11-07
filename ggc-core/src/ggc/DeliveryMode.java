package ggc;
import java.io.Serializable;

public interface DeliveryMode extends Serializable {
    Notification deliverNotification(String productId, double price, String event);
}