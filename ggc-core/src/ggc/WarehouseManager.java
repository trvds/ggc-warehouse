package ggc;
//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import ggc.exceptions.*;
import java.io.*;

/** Façade for access. */
public class WarehouseManager {

  /** Name of file storing current store. */
  private String _filename = "";
  /** State flag to ensure Serialization idempotency.*/
  private boolean _pendingWrite = false;
  /** The warehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  //FIXME define other attributes
  //FIXME define constructor(s)
  //FIXME define other methods

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
    if (_filename.equals("")) {
      throw new MissingFileAssociationException();
    }
    if (_pendingWrite == true) {
      ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
      ous.writeObject(_warehouse);
      ous.close();
      _pendingWrite = false;
    }
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException, FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
    _warehouse = (Warehouse) ois.readObject();
    ois.close();
    _filename = filename;

  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
	    _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException | PartnerUnknownKeyException e) {
	    throw new ImportFileException(textfile);
    }
  }

  public void advanceDate(int days) throws NoSuchDateException{
    _pendingWrite = true;
    _warehouse.advanceDate(days);
  }

  public int showDate(){
    return _warehouse.showDate();
  }

  public double getBalance(){
    return _warehouse.getBalance();
  }

  public void registerPartner(String id, String name, String adress) throws PartnerDuplicateKeyException{
    _pendingWrite = true;
    _warehouse.registerPartner(id, name, adress);
  }

  public String getPartner(String id) throws PartnerUnknownKeyException{
    return _warehouse.getPartner(id);
  }

  public String getPartners(){
    return _warehouse.getPartners();
  }

  public String getAllProducts(){
    return _warehouse.getAllProducts();
  }

  public String getAllBatches(){
    return _warehouse.getAllBatches();
  }

}
