package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import bll.ProductBLL;
import model.Product;

public class ProductCRUDView extends JFrame {

    private JTextField nameTextField;
    private JTextField priceTextField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable productTable;
    private DefaultTableModel tableModel;

    private ProductBLL productBLL;

    public ProductCRUDView() throws SQLException {
        nameTextField = new JTextField(20);
        priceTextField = new JTextField(20);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        tableModel = new DefaultTableModel();

        productBLL = new ProductBLL();

        initializeUI();
        setAddButtonListener();
        setUpdateButtonListener();
        setDeleteButtonListener();
        loadProducts();
    }

    private void initializeUI() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        productTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(productTable);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadProducts() {
        try {
            List<Product> products = productBLL.getAllProducts();
            tableModel = TableUtils.createTableModel(products);
            productTable.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * @return String
     */
    public String getProductName() {
        return nameTextField.getText();
    }

    
    /** 
     * @return double
     */
    public double getProductPrice() {
        return Double.parseDouble(priceTextField.getText());
    }

    public void setAddButtonListener() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    ProductBLL productBLL;
                    try {
                        productBLL = new ProductBLL();
                        int id = productBLL.getAllProducts().size();
                        productBLL.addProduct(new Product(id, getProductName(), getProductPrice(), 100)); 
                        loadProducts();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } 
                
            }});
    }

    public static Object[] getSelectedRowData(JTable table) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int numColumns = model.getColumnCount();
            Object[] rowData = new Object[numColumns];

            for (int i = 0; i < numColumns; i++) {
                rowData[i] = model.getValueAt(selectedRow, i);
            }

            return rowData;
        }

        return null;
    }

    public void setUpdateButtonListener() {
        updateButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)getSelectedRowData(productTable)[0];
                
                System.out.print(id);
                ProductBLL productBLL;
                try {
                    productBLL = new ProductBLL();
                    productBLL.updateProduct(new Product(id, getProductName(), getProductPrice(), (int)getSelectedRowData(productTable)[4])); 
                    loadProducts();
                    
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } 
            }});
    }

    public void setDeleteButtonListener() {
        deleteButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)getSelectedRowData(productTable)[0];
                ProductBLL productBLL;
                try {
                    productBLL = new ProductBLL();
                    productBLL.deleteProduct(id); 
                    loadProducts();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } 
            }});
    }
}

