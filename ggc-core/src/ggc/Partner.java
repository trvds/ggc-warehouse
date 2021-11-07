package ggc;
import java.io.Serializable;
import java.util.ArrayList;

public class Partner implements Serializable, ProductObserver{
    private String _id;
    private String _name;
    private String _adress;
    private int _points;
    private Status _status;
    private int _totalBought;
    private int _totalSold;
    private int _totalPaid;
    private ArrayList<Notification> _notifications = new ArrayList<Notification>();
    private DeliveryMode _deliveryMode = new DefaultDeliveryMode();


    public Partner(String id, String name, String adress){
        _id = id;
        _name = name;
        _adress = adress;
        _points = 0;
        _status = new NormalStatus(this, 0);
        _totalBought = 0;
        _totalPaid = 0;
        _totalSold = 0;
    }

    public String getPartnerId(){
        return _id;
    }

    @Override
    public void update(String productId, double price, String event) {
        _notifications.add(_deliveryMode.deliverNotification(productId, price, event));
    }

    @Override
    public String toString(){
        return _id + "|" + _name + "|" + _adress + "|" + _status.getStatus() + "|" + _points + "|" + _totalBought + "|" + _totalSold + "|" + _totalPaid;
    }

    public ArrayList<String> getNotifications(){
        ArrayList<String> returnList = new ArrayList<String>();
        for(Notification notification: _notifications){
            returnList.add(notification.toString());
        }
        wipeNotifications();
        return returnList;
    }

    public void wipeNotifications()
    {
        this._notifications = new ArrayList<Notification>();
    }

    public void setStatus(Status status){
        _status = status;
    }

    public void updateStatus(){
        _status.updateStatus();
    }
}