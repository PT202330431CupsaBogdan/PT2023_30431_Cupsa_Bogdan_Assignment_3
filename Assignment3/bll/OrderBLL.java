package bll;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.*;
import model.*;

public class OrderBLL {

    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public OrderBLL() throws SQLException {
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
    }

    /**
     * Creates a new order.
     *
     * @param order the order to be created
     * @throws SQLException if a database access error occurs
     */
    public void createOrder(Order order) throws SQLException {
        int productId = order.getProduct();
        int quantity = order.getQuantity();
        ProductDAO pDAO = new ProductDAO();

        Product product = pDAO.getProductById(productId);
        if (product != null && product.getStock() >= quantity) {
            // Sufficient stock, proceed with the order
            orderDAO.addOrder(order);
            // Update the product stock
            product.setStock(product.getStock() - quantity);
            productDAO.updateProduct(product);
        } else {
            // Insufficient stock, display an under-stock message
            JOptionPane.showMessageDialog(null, "Insufficient stock for the selected product");
        }
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }
}