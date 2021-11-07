package ggc;
import java.io.Serializable;

public interface ProductObserver extends Serializable{
    void update(String productId, double price, String event);
}
