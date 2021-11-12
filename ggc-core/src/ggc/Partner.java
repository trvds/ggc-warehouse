package ggc;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.lang.Math;

public class Partner implements ProductObserver {
    private String _id;
    private String _name;
    private String _adress;
    private Status _status;
    private double _totalBought;
    private double _totalSold;
    private double _totalPaid;
    private ArrayList<Notification> _notifications = new ArrayList<Notification>();
    private StandardDeliveryMode _deliveryMode = new StandardDeliveryMode();
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

    
    /** 
     * @param transaction
     */
    public void registerTransaction(Transaction transaction){
        _transactions.put(transaction.getId(), transaction);
    }


    public void updateBought(double bought){
        _totalBought += bought;
    }


    public void updateSold(double sold){
        _totalSold += sold;
    }


    public void updatePaid(double paid){
        _totalPaid += paid;
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


    public void updateStatus(int points){
        _status.setPoints(points);
        _status.updateStatus();
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


    public ArrayList<Transaction> getPaidTransactions(){
        ArrayList<Transaction> returnList = new ArrayList<Transaction>();
        
        for(Map.Entry<Integer,Transaction> entry : _transactions.entrySet()){
            Transaction transaction = entry.getValue();
            if (transaction.transactionType() == "VENDA" && transaction.isPaid())
                returnList.add(transaction);   
        }

        return returnList;
    }


    public ArrayList<Transaction> getSellBreakdownTransactions(){
        ArrayList<Transaction> returnList = new ArrayList<Transaction>();
        
        for(Map.Entry<Integer,Transaction> entry : _transactions.entrySet()){
            Transaction transaction = entry.getValue();
            if (transaction.transactionType() == "VENDA" || transaction.transactionType() == "DESAGREGAÇÃO")
                returnList.add(transaction);   
        }

        return returnList;
    }


    public double calculatePrice(int date, int n, int deadline, double price){
        return _status.calculatePrice(date, n, deadline, price);
    }
}