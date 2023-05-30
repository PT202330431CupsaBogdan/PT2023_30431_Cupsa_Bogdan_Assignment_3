package model;

/*
 * @author Cupsa Bogdan
 */

public class Order {
    
    private int id;
    private int client;
    private int product;
    private int quantity;
    private String date;
   
   
    public Order(int id, int client, int product, int quantity, String date) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
    }


    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }


    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    
    /** 
     * @return int
     */
    public int getClient() {
        return client;
    }


    
    /** 
     * @param client
     */
    public void setClient(int client) {
        this.client = client;
    }


    
    /** 
     * @return int
     */
    public int getProduct() {
        return product;
    }


    
    /** 
     * @param product
     */
    public void setProduct(int product) {
        this.product = product;
    }


    
    /** 
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }


    
    /** 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    
    /** 
     * @return String
     */
    public String getDate() {
        return date;
    }


    
    /** 
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }


}
