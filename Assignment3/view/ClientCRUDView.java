package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import bll.ClientBLL;
import model.Client;

public class ClientCRUDView extends JFrame {
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable clientTable;
    private DefaultTableModel tableModel;

    private ClientBLL clientBLL;

    public ClientCRUDView() throws SQLException {
        nameTextField = new JTextField(20);
        addressTextField = new JTextField(20);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        tableModel = new DefaultTableModel();

        clientBLL = new ClientBLL();

        initializeUI();
        setAddButtonListener();
        setUpdateButtonListener();
        setDeleteButtonListener();
        loadClients();
    }

    private void initializeUI() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Address"));
        inputPanel.add(addressTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        clientTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(clientTable);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadClients() {
        try {
            List<Client> clients = clientBLL.getAllClients();
            tableModel = TableUtils.createTableModel(clients);
            clientTable.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * @return String
     */
    public String getClientName() {
        return nameTextField.getText();
    }

    
    /** 
     * @return String
     */
    public String getClientAddress() {
        return addressTextField.getText();
    }

    public void setAddButtonListener() {
        addButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientBLL clientBLL;
                try {
                    clientBLL = new ClientBLL();
                    int id = clientBLL.getAllClients().size();
                    clientBLL.addClient(new Client(id, getClientName(), getClientAddress(), "0758544132")); 
                    loadClients();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } 
            }});
    }

    public void setUpdateButtonListener() {
        updateButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = clientTable.getSelectedRow();
                System.out.print(id);
                ClientBLL clientBLL;
                try {
                    clientBLL = new ClientBLL();
                    clientBLL.updateClient(new Client(id, getClientName(), getClientAddress(), "0758544133")); 
                    loadClients();
                    
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } 
            }});
    }

    public void setDeleteButtonListener() {
        deleteButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = clientTable.getSelectedRow();
                ClientBLL clientBLL;
                try {
                    clientBLL = new ClientBLL();
                    clientBLL.deleteClient(id); 
                    loadClients();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } 
            }});
    }
}
