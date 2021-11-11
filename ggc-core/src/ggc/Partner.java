package ggc;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.lang.Math;

public class Partner implements ProductObserver {
    private String _id;
    private String _name;
    private String _adress;
    private int _points;
    private Status _status;
    private double _totalBought;
    private double _totalSold;
    private double _totalPaid;
    private ArrayList<Notification> _notifications = new ArrayList<Notification>();
    private DeliveryMode _deliveryMode = new DefaultDeliveryMode();
    private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();


    public Partner(String id, String name, String adress){
        _id = id;
        _name = name;
        _adress = adress;
        _status = new NormalStatus(this, 0);
        _totalBought = 0;
        _totalPaid = 0;
        _totalSold = 0;
    }

    
    /** 
     * @return String
     */
    public String getPartnerId(){
        return _id;
    }

    
    /** 
     * @param productId
     * @param price
     * @param event
     */
    @Override
    public void update(String productId, double price, String event) {
        _notifications.add(_deliveryMode.sendNotification(productId, price, event));
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return _id + "|" + _name + "|" + _adress + "|" + _status.getStatus() + "|" + getPoints() + "|" + Math.round(_totalBought) + "|" + Math.round(_totalSold) + "|" + Math.round(_totalPaid);
    }

    
    /** 
     * @return ArrayList<String>
     */
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

    
    /** 
     * @param status
     */
    public void setStatus(Status status){
        _status = status;
    }

    public void updateStatus(){
        _status.updateStatus();
    }

    
    /** 
     * @param transaction
     */
    public void registerTransaction(Transaction transaction){
        _transactions.put(transaction.getId(), transaction);
    }

    public int getPoints(){
        return _status.getPoints();
    }

    /** 
     * @param id
     * @return Transaction
     */
    public Transaction getTransaction(int id){
        return _transactions.get(id);
    }

    public void addTotalBought(double totalBought){
        _totalBought += totalBought;
    }
    
    /** 
     * @return ArrayList<Transaction>
     */
    public ArrayList<Transaction> getBuyTransactions(){
        ArrayList<Transaction> returnList = new ArrayList<Transaction>();
        
        for(Map.Entry<Integer,Transaction> entry : _transactions.entrySet()){
            Transaction transaction = entry.getValue();
            if (transaction.transactionType() == "COMPRA")
                returnList.add(transaction);   
        }

        return returnList;
    }

    /**
     * 
     * @return ArrayList<Transaction>
     */
    public ArrayList<Transaction> getSellBreakdownTransactions(){
        ArrayList<Transaction> returnList = new ArrayList<Transaction>();
        
        for(Map.Entry<Integer,Transaction> entry : _transactions.entrySet()){
            Transaction transaction = entry.getValue();
            if (transaction.transactionType() == "VENDA" || transaction.transactionType() == "DESAGREGAÇÃO")
                returnList.add(transaction);   
        }

        return returnList;
    }


    public ArrayList<Transaction> getPaidTransactions(){
        ArrayList<Transaction> returnList = new ArrayList<Transaction>();
        
        for(Map.Entry<Integer,Transaction> entry : _transactions.entrySet()){
            Transaction transaction = entry.getValue();
            if (transaction.transactionType() == "VENDA" && transaction.isPaid())
                returnList.add(transaction);   
        }

        return returnList;
    }


    public void updatePaid(double paid){
        _totalPaid += paid;
    }

    public double calculatePrice(int date, int n, int deadline, double price){
        return _status.calculatePrice(date, n, deadline, price);
    }
}