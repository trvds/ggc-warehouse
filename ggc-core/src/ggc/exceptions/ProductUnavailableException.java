package ggc.exceptions;

/** Exception for product unavailability */
public class ProductUnavailableException extends Exception{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109091821L;

  /**  */
  private final String _id;
  private final int _amount;
  private final int _totalStock;

  /** @param id unknown key to report. */
  public ProductUnavailableException(String id, int amount, int totalStock) {
    _id = id;
    _amount = amount;
    _totalStock = totalStock;
   }

   /**
   * @return the id
   */
  public String getId() {
    return _id;
  }

  public int getAmount(){
      return _amount;
  }

  public int getTotalStock(){
      return _totalStock;
  }

    
}
