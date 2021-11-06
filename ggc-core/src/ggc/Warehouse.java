package ggc;
import ggc.exceptions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  /** Date of the warehouse */
  private int _date = 0;
  /** Balance of the warehouse */
  private double _balance = 0;
  /** Number of transactions that have happened in the warehouse */
  private int _transactionCounter = 0;

  /** Partners of the warehouse */
  private Map<String,Partner> _partners = new TreeMap<String,Partner>(String.CASE_INSENSITIVE_ORDER);
  /** Products in the warehouse */
  private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);
  /** Batches of products in the warehouse */
  private Map<String, TreeSet<Batches>> _batches = new TreeMap<String, TreeSet<Batches>>();
  /** Transaction List of the warehouse */
  private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();

  /**
   *  Constructor
   */
  public Warehouse(){
  }

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
          case "PARTNER" -> registerPartner(fields[1], fields[2], fields[3]); // PARTNER|id|nome|endereço
          case "BATCH_S" -> {
                              registerProduct(fields[1], Integer.valueOf(fields[4]), Double.valueOf(fields[3]));
                              registerBatches(fields[1], fields[2], Double.valueOf(fields[3]), Integer.valueOf(fields[4]));
                            } // BATCH_S|idProduto|idParceiro|preço|stock-actual
          case "BATCH_M" -> {
                              registerProduct(fields[1], Integer.valueOf(fields[4]), Double.valueOf(fields[3]), Double.valueOf(fields[5]), defineRecipe(fields[6]));
                              registerBatches(fields[1], fields[2], Double.valueOf(fields[3]), Integer.valueOf(fields[4]));
                            } // BATCH_M|idProduto|idParceiro|preço|stock-actual|agravamento|componente-1:quantidade-1#...#componente-n:quantidade-n

           default -> throw new BadEntryException(fields[0]);
        }
      }
    }
    catch (PartnerDuplicateKeyException e) {
      e.printStackTrace();
    }
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
    
    for(Map.Entry<String, TreeSet<Batches>> entry : _batches.entrySet()){
      TreeSet<Batches> productSet = entry.getValue();
      for(Batches batch: productSet){
        returnString += batch.toString();
      }
    }
    return returnString;
  }

  /**
   * Function to register a Batch in the warehouse
   * @param productId id of the product of the batch
   * @param partnerId id of the partner of the batch
   * @param price price of the batch
   * @param quantity quantity of stock of the batch
   * @param alpha aggravation tax on the recipe of the product
   * @param recipe recipe of the product
   * @throws PartnerUnknownKeyException
   */
  public void registerBatches(String productId, String partnerId, double price, int quantity) throws PartnerUnknownKeyException {

    Product product = _products.get(productId);
    Partner partner = _partners.get(partnerId);
    
    if(partner == null)
      throw new PartnerUnknownKeyException(partnerId);
        
    TreeSet<Batches> productSet = _batches.get(productId);
    
    if (productSet == null)
      productSet = new TreeSet<Batches>();
    Batches batch = new Batches(product, partner, quantity, price);

    productSet.add(batch);
    _batches.put(productId, productSet);

  }

  public void registerProduct(String productId, int totalStock, double maxPrice){
    if (_products.get(productId) == null){
      Product product = new Product(productId);
      product.setTotalStock(totalStock);
      product.setMaxPrice(maxPrice);

      _products.put(productId, product);
    }
    else{
      Product product = _products.get(productId);
      if (product.getMaxPrice() < maxPrice)
        product.setMaxPrice(maxPrice);
      product.setTotalStock(product.getTotalStock() + totalStock);
    }
  }

  public void registerProduct(String productId, int totalStock, float maxPrice, float alpha, ArrayList<RecipeComponent> recipe){
    if (_products.get(productId) == null){
      DerivedProduct product = new DerivedProduct(productId, recipe, alpha);
      product.setTotalStock(totalStock);
      product.setMaxPrice(maxPrice);
      _products.put(productId, product);
    }
    else{
      Product product = _products.get(productId);
      if (product.getMaxPrice() < maxPrice)
        product.setMaxPrice(maxPrice);
      product.setTotalStock(product.getTotalStock() + totalStock);
    }
  }

  /**
   * Function to translate a string describing a recipe of a product into a array of RecipeComponents
   * @param recipeDescription string with the description of the recipe
   * @return array of RecipeComponents of the recipe
   */
  public ArrayList<RecipeComponent> defineRecipe(String recipeDescription){ //throws ProductUnknownKeyException{
    ArrayList<RecipeComponent> recipe = new ArrayList<RecipeComponent>();
    String[] components = recipeDescription.split("#");

    for(String component: components){
      String[] fields = component.split(":");
      Product product = _products.get(fields[0]);
      //if (product == null)
      //  throw new ProductUnknownKey(fields[0]);
      int quantity = Integer.parseInt(fields[1]);
      RecipeComponent recipeComponent = new RecipeComponent(product, quantity);
      recipe.add(recipeComponent);
    }
    return recipe;
  }

  /**
   * Function to get the batches of a given partner
   * @param partnerId
   * @return String of batches by partner
   */
  public String getBatchesByPartner(String partnerId) throws PartnerUnknownKeyException{
    Partner partner = _partners.get(partnerId);
    if (partner == null)
      throw new PartnerUnknownKeyException(partnerId);
    String returnString = "";
    
    for(Map.Entry<String, TreeSet<Batches>> entry : _batches.entrySet()){
      TreeSet<Batches> productSet = entry.getValue();
      for(Batches batch: productSet){
        if (batch.getPartner() == partner)
          returnString += batch.toString();
      }
    }
    return returnString;
  }

  /**
   * Function to get the batches of a given product
   * @param productId
   * @return String of batches by product
   */
  public String getBatchesByProduct(String productId) throws ProductUnknownKeyException{
    Product product = _products.get(productId);
    if (product == null)
      throw new ProductUnknownKeyException(productId);
    String returnString = "";
    
    TreeSet<Batches> productSet = _batches.get(productId);
    
    for(Batches batch: productSet){
      returnString += batch.toString();
    }
    return returnString;
  }

  public void registerBuyTransaction(String partnerId, String productId, double price, int quantity) throws PartnerUnknownKeyException, ProductUnknownKeyException{
      Partner partner = _partners.get(partnerId);
      Product product = _products.get(productId);

      if (partner == null) {
        throw new PartnerUnknownKeyException(partnerId);
      }
      if (product == null) {
        throw new ProductUnknownKeyException(productId);
      }

      Transaction transaction = new BuyTransaction(_transactionCounter, _date, productId, partnerId, quantity, price);
      _transactionCounter += 1;
      _transactions.put(_transactionCounter, transaction);
      registerBatches(productId, partnerId, price, quantity);
  }

  public void registerSaleTransaction(String partnerId, double price, int amount) throws PartnerUnknownKeyException, ProductUnknownKeyException{
    //TODO everything
  }


  public String getTransaction(int id) throws TransactionUnknownKeyException{
    Transaction transaction = _transactions.get(id);
    if (transaction == null){
      throw new TransactionUnknownKeyException(id);
    }
    return transaction.toString();
  }

  public void registerSellTransaction(String partnerId, int paymentDeadline, String productId, int quantity)  {
    Partner partner = _partners.get(partnerId);
    Product product = _products.get(productId);

    if (partner == null) {
      //TODO lançar exceção
    }
    if (product == null) {
      //TODO lançar exceção
    }

    if (product.getTotalStock() > quantity) {
      // Lançar exceção UnavailableProductException
    }



}

}