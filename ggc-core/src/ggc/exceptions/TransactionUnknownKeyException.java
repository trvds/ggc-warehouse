package ggc.exceptions;

public class TransactionUnknownKeyException extends Exception{
        
  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109091821L;

  /** The unknown key */
  private final int _id;

  /** @param id unknown key to report. */
  public TransactionUnknownKeyException(int id) {
    _id = id;
   }

   /**
   * @return the id
   */
  public int getId() {
    return _id;
  }

  
}
