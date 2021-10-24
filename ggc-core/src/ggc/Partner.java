package ggc;
import java.io.Serializable;
import java.util.ArrayList;

public class Partner implements Serializable{
    private String _id;
    private String _name;
    private String _adress;
    private int _points;
    private Status _status;
    private int _totalBought;
    private int _totalSold;
    private int _totalPaid;
    private ArrayList<String> _notifications = new ArrayList<String>();


    public Partner(String id, String name, String adress){
        _id = id;
        _name = name;
        _adress = adress;
        _points = 0;
        _status = new NormalStatus();
        _totalBought = 0;
        _totalPaid = 0;
        _totalSold = 0;
    }

    public String toString(){
        return _id + "|" + _name + "|" + _status.getStatus() + "|" + _points + "|" + _totalBought + "|" + _totalSold + "|" + _totalPaid;
    }

    public ArrayList<String> getNotifications(){
        ArrayList<String> notifications = this._notifications;
        wipeNotifications();
        return notifications;
    }

    public void wipeNotifications()
    {
        this._notifications = new ArrayList<String>();
    }

}