package ggc.exceptions;

/** Exception for Partner key related problems. */
public class ProductUnknownKey extends Exception {
    
  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109091821L;

  /** The unknown key */
  private final String _id;

  /** @param id unknown key to report. */
  public ProductUnknownKey(String id) {
    _id = id;
   }

   /**
   * @return the id
   */
  public String getId() {
    return _id;
  }

  
}