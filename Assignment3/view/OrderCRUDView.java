package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;

public class OrderCRUDView extends JFrame {

    private JComboBox<String> productComboBox;
    private JComboBox<String> clientComboBox;
    private JTextField quantityTextField;
    private JButton createOrderButton;
    private JTable orderTable;
    private DefaultTableModel tableModel;

    private OrderBLL orderBLL;

    public OrderCRUDView() throws SQLException {
        productComboBox = new JComboBox<>();
        clientComboBox = new JComboBox<>();
        quantityTextField = new JTextField(20);
        createOrderButton = new JButton("Create Order");
        tableModel = new DefaultTableModel();

        orderBLL = new OrderBLL();

        initializeUI();
        setCreateOrderButtonListener();
        loadOrders();
    }

    private void initializeUI() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Product:"));
        inputPanel.add(productComboBox);
        inputPanel.add(new JLabel("Client:"));
        inputPanel.add(clientComboBox);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityTextField);

        try {
            ClientBLL clientBLL = new ClientBLL();
            ProductBLL productBLL = new ProductBLL();

            for (Client c : clientBLL.getAllClients()) {
                clientComboBox.addItem(c.getName());
            }

            for (Product p : productBLL.getAllProducts()) {
                productComboBox.addItem(p.getName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createOrderButton);

        orderTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(orderTable);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadOrders() {
        try {
            List<Order> orders = orderBLL.getAllOrders();
            tableModel = TableUtils.createTableModel(orders);
            orderTable.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * @return int
     */
    public int getSelectedProductId() {
        int selectedIndex = productComboBox.getSelectedIndex();
        // adjust the index to match the actual product ID
        return selectedIndex + 1;
    }

    
    /** 
     * @return int
     */
    public int getSelectedClientId() {
        int selectedIndex = clientComboBox.getSelectedIndex();
        // adjust the index to match the actual client ID
        return selectedIndex + 1;
    }

    public int getOrderQuantity() {
        return Integer.parseInt(quantityTextField.getText());
    }

    public void setProductComboBoxItems(String[] items) {
        productComboBox.setModel(new DefaultComboBoxModel<>(items));
    }

    public void setCreateOrderButtonListener() {
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OrderBLL orderBLL = new OrderBLL();
                    ClientBLL clientBLL = new ClientBLL();
                    ProductBLL productBLL = new ProductBLL();
                    orderBLL = new OrderBLL();

                    List<Client> clients = clientBLL.getAllClients();
                    int clientId = clients.get(clientComboBox.getSelectedIndex()).getId();

                    List<Product> products = productBLL.getAllProducts();
                    int productId = products.get(productComboBox.getSelectedIndex()).getId();
                    System.out.println(productId);

                    int id = orderBLL.getAllOrders().size();
                    orderBLL.createOrder(new Order(id, clientId, productId, getOrderQuantity(), "08-02-2001"));
                    loadOrders();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
    }
}
