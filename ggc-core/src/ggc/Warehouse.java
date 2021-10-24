package ggc;
// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import ggc.exceptions.*;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  // FIXME define attributes
  private int _date = 0;
  private double _balance = 0;
  private int _transactionCounter = 0;
  
  // Maps estão a ser usados como placeholders para outras estruturas
  private Map<String,Partner> _partners = new TreeMap<String,Partner>();
  private Map<String, Product> _products = new TreeMap<String, Product>();
  private Map<String, Product> _batches = new TreeMap<String, Product>();


  // FIXME define contructor(s)
  public Warehouse(){

  }

  // FIXME define methods

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

  public void advanceDate(int days) throws NoSuchDateException{
    if (days < 0)
      throw new NoSuchDateException(days);
    this._date += days;
  }

  public int showDate(){
    return this._date;
  }

  public void registerPartner(String id, String name, String adress) throws PartnerDuplicateKeyException{
    if(_partners.get(id) != null)
      throw new PartnerDuplicateKeyException(id);
  
    Partner partner = new Partner(id, name, adress);
    _partners.put(id, partner);
  }

  public String getPartner(String id) throws PartnerUnknownKeyException{
    String returnString = "";
    Partner partner = _partners.get(id);
    
    if(partner == null)
      throw new PartnerUnknownKeyException(id);

    returnString += partner.toString() + "\n";
    
    
    for(String notification: partner.getNotifications())
      returnString += notification + "\n";

    return returnString;
  }

  public String getPartners(){
      String returnString = "";
      for(Map.Entry<String,Partner> entry : _partners.entrySet())
      {
        returnString += entry.getValue().toString();
        returnString += "\n";
      }

      return returnString;
  }

} 
