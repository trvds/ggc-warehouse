package ggc;
// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import ggc.exceptions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;
  /
  private int _date = 0;
  private double _balance = 0;
  private int _transactionCounter = 0;

  /** Agregations of the Classes */
  private Map<String,Partner> _partners = new TreeMap<String,Partner>(String.CASE_INSENSITIVE_ORDER);
  private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);
  private ArrayList<Batches> _batches = new ArrayList<Batches>();

  // FIXME define contructor(s)
  public Warehouse(){
  }

  // FIXME define methods

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException, PartnerUnknownKeyException {
    try (BufferedReader in = new BufferedReader(new FileReader(txtfile))) {
      String buffer;
      while ((buffer = in.readLine()) != null) {
        String line = new String (buffer.getBytes(), "UTF-8");
        if (line.charAt(0) == '#') { //Commented import lines
          continue;
        }
        String[] fields = line.split("\\|");
        switch (fields[0]) {
          case "PARTNER" -> registerPartner(fields[1], fields[2], fields[3]);// PARTNER|id|nome|endereço
          case "BATCH_S" -> registerSimpleBatches(fields); // BATCH_S|idProduto|idParceiro|preço|stock-actual
          case "BATCH_M" -> registerComposedBatches(fields); // BATCH_M|idProduto|idParceiro|preço|stock-actual|agravamento|componente-1:quantidade-1#...#componente-n:quantidade-n
          default -> throw new BadEntryException(fields[0]);
        }
      }
    }
    catch (PartnerDuplicateKeyException e) {
      e.printStackTrace();
    }

  }
  
  //public void registerSimpleBatch(String productId, String partnerId, float price, int quantity) throws PartnerUnknownKeyException{
  public void registerComposedBatches(String[] fields) throws PartnerUnknownKeyException {
    // BATCH_M|idProduto|idParceiro|preço|stock-actual|agravamento|componente-1:quantidade-1#...#componente-n:quantidade-n
    String productId = fields[1];
    String partnerId = fields[2];
    float price = Float.valueOf(fields[3]);
    int quantity = Integer.valueOf(fields[4]);
    float alpha = Float.valueOf(fields[5]);
    ArrayList<RecipeComponent> recipe = defineRecipe(fields[6]);

    Product product = _products.get(productId);
    Partner partner = _partners.get(partnerId);
    if(partner == null)
      throw new PartnerUnknownKeyException(partnerId);
    if(product == null){
      product = new DerivedProduct(productId, recipe, alpha);
      product.setTotalStock(quantity);
      product.setMaxPrice(price);
      _products.put(productId, product);
    }
    else
    {
      if (product.getMaxPrice() < price){
        product.setMaxPrice(price);
      }
      product.setTotalStock(product.getTotalStock() + quantity);
    }

    Batches batch = new Batches(product, partner, quantity, price);
    _batches.add(batch);

  }

  public void registerSimpleBatches(String[] fields) throws PartnerUnknownKeyException {
    String productId = fields[1];
    String partnerId = fields[2];
    float price = Float.valueOf(fields[3]);
    int quantity = Integer.valueOf(fields[4]);

    Product product = _products.get(productId);
    Partner partner = _partners.get(partnerId);
    if(partner == null)
      throw new PartnerUnknownKeyException(partnerId);
    if(product == null){
      product = new Product(productId);
      product.setTotalStock(quantity);
      product.setMaxPrice(price);
      _products.put(productId, product);
    }
    else
    {
      if(product.getMaxPrice() < price){
        product.setMaxPrice(price);
      }
      product.setTotalStock(product.getTotalStock() + quantity);
    }
    
    Batches batch = new Batches(product, partner, quantity, price);
    _batches.add(batch);
  }

  /**
   * Function to advance the warehouse date
   * @param days - days to advance
   * @throws NoSuchDateException
   */
  public void advanceDate(int days) throws NoSuchDateException{
    if (days <= 0)
      throw new NoSuchDateException(days);
    this._date += days;
  }

  /**
   * Function to get the warehouse date
   * @return _date - returns the warehouse date
   */
  public int showDate(){
    return this._date;
  }

  /**
   * Function to get the current balance of the warehouse
   * @return _balance - returns the warehouse current balance
   */
  public double getBalance(){
    return _balance;
  }

  /**
   * Function to register a Partner in the warehouse
   * @param id - id of the Partner
   * @param name - name of the Partner
   * @param adress - adress of the Partner
   * @throws PartnerDuplicateKeyException
   */
  public void registerPartner(String id, String name, String adress) throws PartnerDuplicateKeyException{
    if(_partners.get(id) != null)
      throw new PartnerDuplicateKeyException(id);
  
    Partner partner = new Partner(id, name, adress);
    _partners.put(id, partner);
  }

/**
 * Function to get a specific Partner in the warehouse
 * @param id - id of the Partner
 * @return returnString - String of the Partner
 * @throws PartnerUnknownKeyException
 */
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

  /**
   * Function to get All the Partners in the warehouse
   * @return returnString - String of all the Partners
   */
  public String getPartners(){
      String returnString = "";
      for(Map.Entry<String,Partner> entry : _partners.entrySet()){
        returnString += entry.getValue().toString();
        returnString += "\n";
      }

      return returnString;
  }

  /**
   * Function to get All the Products in the warehouse
   * @return returnString - String of all the Products
   */
  public String getAllProducts(){
    String returnString = "";
    for(Map.Entry<String,Product> entry : _products.entrySet()){
      returnString += entry.getValue().toString();
      returnString += "\n";
    }
    return returnString;
  }

  /**
   * Function to get All Batches in the warehouse
   * @return returnString - String of all the Batches
   */
  public String getAllBatches(){
    String returnString = "";
    Collections.sort(_batches);
    for(Batches batch: _batches){
      returnString += batch.toString();
    }
    return returnString;
  }

  public ArrayList<RecipeComponent> defineRecipe(String recipeDescription) throws ProductUnknownKey{
    ArrayList<RecipeComponent> recipe = new ArrayList<RecipeComponent>();
    String[] components = recipeDescription.split("#");

    for(String component: components){
      String[] fields = component.split(":");
      Product product = _products.get(fields[0]);
      if (product == null)
        throw new ProductUnknownKey(fields[0]);
      int quantity = Integer.parseInt(fields[1]);
      RecipeComponent recipeComponent = new RecipeComponent(product, quantity);
      recipe.add(recipeComponent);
    }
    return recipe;
  }
}