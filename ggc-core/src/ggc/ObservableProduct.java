package ggc;
import java.io.Serializable;

public interface ObservableProduct extends Serializable {
    void registerObserver(ProductObserver observer);
    void removeObserver(ProductObserver observer);
    void notify(double price, String event);
}