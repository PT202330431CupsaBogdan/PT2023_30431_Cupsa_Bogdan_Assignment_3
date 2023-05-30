package bll;

import java.sql.SQLException;
import java.util.List;

import dao.*;
import model.Product;

public class ProductBLL {
    
    private ProductDAO productDAO;

    public ProductBLL() throws SQLException {
        this.productDAO = new ProductDAO();
    }

    
    /** 
     * @param product
     * @throws SQLException
     */
    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    
    /** 
     * @param product
     * @throws SQLException
     */
    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteProduct(productId);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }
}