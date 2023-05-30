package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Product;

public class ProductDAO {

    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Constructs a new ProductDAO object and establishes a database connection.
     *
     * @throws SQLException if a database access error occurs
     */

    public ProductDAO() throws SQLException {
        this.conn = ConnectionFactory.getConnection();
    }

    
    /**
     * Adds a new product to the database.
     *
     * @param product the product to be added
     * @throws SQLException if a database access error occurs
     */

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product(name, description, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.executeUpdate();
        }
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product the product to be updated
     * @throws SQLException if a database access error occurs
     */
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET name=?, description=?, price=?, stock=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setInt(5, product.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * Deletes a product from the database based on the specified ID.
     *
     * @param id the ID of the product to be deleted
     * @throws SQLException if a database access error occurs
     */
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves a product from the database based on the specified ID.
     *
     * @param id the ID of the product to be retrieved
     * @return the product with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Product getProductById(int id) throws SQLException {
        Connection con2 = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM Product WHERE id=?";
        try (PreparedStatement pstmt = con2.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                return new Product(id, name, description, price, stock);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con2 != null) {
                con2.close(); // Close the connection in the finally block
            }
        }
        return null;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products
     * @throws SQLException if a database access error occurs
     */
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM 'Product'";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                products.add(new Product(id, name, description, price, stock));
            }
            return products;
        }
    }
}
