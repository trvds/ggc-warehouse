package ggc;
//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import ggc.exceptions.*;
import java.io.*;
import ggc.RecipeComponent;
import java.util.ArrayList;
/** Façade for access. */
public class WarehouseManager {

  /** Name of file storing current store. */
  private String _filename = "";
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
    ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
    ous.writeObject(_warehouse);
    ous.close();

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
    _warehouse.advanceDate(days);
  }

  public int showDate(){
    return _warehouse.showDate();
  }

  public double getBalance(){
    return _warehouse.getBalance();
  }

  public void registerPartner(String id, String name, String adress) throws PartnerDuplicateKeyException{
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

  public String getBatchesByProduct(String id) throws ProductUnknownKeyException{
    return _warehouse.getBatchesByProduct(id);
  }
  
  public String getBatchesByPartner(String id) throws PartnerUnknownKeyException{
    return _warehouse.getBatchesByPartner(id);
  }

  public String getTransaction(int id) throws TransactionUnknownKeyException{
    return _warehouse.getTransaction(id);
  }

  public void registerBuyTransaction(String partnerId, String productId, double price, int quantity) throws PartnerUnknownKeyException, ProductUnknownKeyException{
    _warehouse.registerBuyTransaction(partnerId, productId, price, quantity);
  }

  public Product getProduct(String productId){
    return _warehouse.getProduct(productId);
  }

  public void registerProduct(String productId, int totalStock, double maxPrice){
    _warehouse.registerProduct(productId, totalStock, maxPrice);
  }

  public void registerProduct(String productId, int totalStock, double maxPrice, double alpha, ArrayList<RecipeComponent> recipe){
    _warehouse.registerProduct(productId, totalStock, maxPrice, alpha, recipe);
  }

  public void toggleNotifications(String productId, String partnerId){
    _warehouse.toggleNotifications(productId, partnerId);
  }
}
