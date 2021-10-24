package ggc.exceptions;

/** Exception for date-related problems. */
public class NoSuchDateException extends Exception {
    
  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109091821L;

  /** The incorrect date */
  private final int _date;

  /** @param date bad date to report. */
  public NoSuchDateException(int date) {
    _date = date;
   }

   /**
   * @return the date
   */
  public int getDate() {
    return _date;
  }
}
