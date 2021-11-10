package ggc;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Partner implements ProductObserver {
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
    private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();


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
        _notifications.add(_deliveryMode.deliverNotification(productId, price, event));
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return _id + "|" + _name + "|" + _adress + "|" + _status.getStatus() + "|" + _points + "|" + _totalBought + "|" + _totalSold + "|" + _totalPaid;
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

    
    /** 
     * @param id
     * @return Transaction
     */
    public Transaction getTransaction(int id){
        return _transactions.get(id);
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
}