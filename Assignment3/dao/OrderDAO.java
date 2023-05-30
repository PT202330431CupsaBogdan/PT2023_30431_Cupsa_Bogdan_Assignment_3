package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.*;

public class OrderDAO {
    
    private Connection conn;
    
    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Constructs a new OrderDAO object and establishes a database connection.
     *
     * @throws SQLException if a database access error occurs
     */
    public OrderDAO() throws SQLException
    {
        this.conn = ConnectionFactory.getConnection();
    }
    
    
    /**
     * Retrieves all orders from the database.
     *
     * @return a list of all orders
     * @throws SQLException if a database access error occurs
     */
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        
        String sql = "SELECT * FROM OrdersTable";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                int clientId = rs.getInt("client_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                String date = rs.getString("date");
                
                Order order = new Order(id, clientId, productId, quantity, date);
                orders.add(order);
            }
        }
        
        return orders;
    }
    
    /**
     * Retrieves an order from the database based on the specified ID.
     *
     * @param id the ID of the order to be retrieved
     * @return the order with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Order getOrderById(int id) throws SQLException {
        Order order = null;
        
        String sql = "SELECT * FROM OrdersTable WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int clientId = rs.getInt("client_id");
                    int productId = rs.getInt("product_id");
                    int quantity = rs.getInt("quantity");
                    String date = rs.getString("date");
                    
                    Client client = new ClientDAO(conn).getClientById(clientId);
                    Product product = new ProductDAO(conn).getProductById(productId);
                    
                    order = new Order(id, client.getId(), product.getId(), quantity, date);
                }
            }
        }
        
        return order;
    }
    
    /**
     * Adds a new order to the database.
     *
     * @param order the order to be added
     * @throws SQLException if a database access error occurs
     */
    public void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO OrdersTable (client_id, product_id, quantity, date) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getClient());
            pstmt.setInt(2, order.getProduct());
            pstmt.setInt(3, order.getQuantity());
            pstmt.setString(4, order.getDate());
            
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Updates an existing order in the database.
     *
     * @param order the order to be updated
     * @throws SQLException if a database access error occurs
     */
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE OrdersTable SET client_id = ?, product_id = ?, quantity = ?, date = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getClient());
            pstmt.setInt(2, order.getProduct());
            pstmt.setInt(3, order.getQuantity());
            pstmt.setString(4, order.getDate());
            pstmt.setInt(5, order.getId());
            
            pstmt.executeUpdate();
        }
    }
    
    /**
     * Deletes an order from the database based on the specified ID.
     *
     * @param id the ID of the order to be deleted
     * @throws SQLException if a database access error occurs
     */
    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM OrdersTable WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
        }
    }
}
