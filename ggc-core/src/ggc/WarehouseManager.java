package ggc;
import ggc.exceptions.*;
import java.io.*;
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

  
  /** 
   * @param days
   * @throws NoSuchDateException
   */
  public void advanceDate(int days) throws NoSuchDateException{
    _warehouse.advanceDate(days);
  }

  
  /** 
   * @return int
   */
  public int showDate(){
    return _warehouse.showDate();
  }

  
  /** 
   * @return double
   */
  public double getBalance(){
    return _warehouse.getBalance();
  }


  /**
   * 
   * @return double
   */
  public double getAccountingBalance(){
    return _warehouse.getAccountingBalance();
  }

  
  /** 
   * @param id
   * @param name
   * @param adress
   * @throws PartnerDuplicateKeyException
   */
  public void registerPartner(String id, String name, String adress) throws PartnerDuplicateKeyException{
    _warehouse.registerPartner(id, name, adress);
  }

  
  /** 
   * @param id
   * @return String
   * @throws PartnerUnknownKeyException
   */
  public String getPartner(String id) throws PartnerUnknownKeyException{
    return _warehouse.getPartner(id);
  }

  
  /** 
   * @return String
   */
  public String getPartners(){
    return _warehouse.getPartners();
  }

  
  /** 
   * @return String
   */
  public String getAllProducts(){
    return _warehouse.getAllProducts();
  }

  
  /** 
   * @return String
   */
  public String getAllBatches(){
    return _warehouse.getAllBatches();
  }

  
  /** 
   * @param id
   * @return String
   * @throws ProductUnknownKeyException
   */
  public String getBatchesByProduct(String id) throws ProductUnknownKeyException{
    return _warehouse.getBatchesByProduct(id);
  }
  
  
  /** 
   * @param id
   * @return String
   * @throws PartnerUnknownKeyException
   */
  public String getBatchesByPartner(String id) throws PartnerUnknownKeyException{
    return _warehouse.getBatchesByPartner(id);
  }

  
  /** 
   * @param id
   * @return String
   * @throws TransactionUnknownKeyException
   */
  public String getTransaction(int id) throws TransactionUnknownKeyException{
    return _warehouse.getTransaction(id);
  }

  
  /** 
   * @param partnerId
   * @param productId
   * @param price
   * @param quantity
   * @throws PartnerUnknownKeyException
   * @throws ProductUnknownKeyException
   */
  public void registerBuyTransaction(String partnerId, String productId, double price, int quantity) throws PartnerUnknownKeyException, ProductUnknownKeyException{
    _warehouse.registerBuyTransaction(partnerId, productId, price, quantity);
  }

  
  /** 
   * @param productId
   * @return Product
   */
  //partnerId, productId, paymentDeadline, amount);
  public void registerSaleTransaction(String partnerId, String productId, int paymentDeadline, int amount) throws PartnerUnknownKeyException, ProductUnknownKeyException, ProductUnavailableException {
    _warehouse.registerSaleTransaction(partnerId, productId, paymentDeadline, amount);
  }

  public Product getProduct(String productId){
    return _warehouse.getProduct(productId);
  }

  
  /** 
   * @param productId
   * @param totalStock
   * @param maxPrice
   */
  public void registerProduct(String productId, int totalStock, double maxPrice){
    _warehouse.registerProduct(productId, totalStock, maxPrice);
  }

  
  /** 
   * @param productId
   * @param totalStock
   * @param maxPrice
   * @param alpha
   * @param recipe
   */
  public void registerProduct(String productId, int totalStock, double maxPrice, double alpha, ArrayList<RecipeComponent> recipe){
    _warehouse.registerProduct(productId, totalStock, maxPrice, alpha, recipe);
  }

  
  /** 
   * @param productId
   * @param partnerId
   * @throws PartnerUnknownKeyException
   */
  public void toggleNotifications(String productId, String partnerId) throws PartnerUnknownKeyException, ProductUnknownKeyException{
    _warehouse.toggleNotifications(productId, partnerId);
  }

  
  /** 
   * @param partnerId
   * @return String
   * @throws PartnerUnknownKeyException
   */
  public String getPartnerBuyTransactions(String partnerId) throws PartnerUnknownKeyException{
    return _warehouse.getPartnerBuyTransactions(partnerId);
  }

    /** 
   * @param partnerId
   * @return String
   * @throws PartnerUnknownKeyException
   */
  public String getPartnerSellBreakdownTransactions(String partnerId) throws PartnerUnknownKeyException{
    return _warehouse.getPartnerSellBreakdownTransactions(partnerId);
  }

  public String getBatchesByPrice(double price){
    return _warehouse.getBatchesByPrice(price);
  }
}
