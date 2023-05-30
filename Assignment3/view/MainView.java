package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;

    public MainView() {
        clientButton = new JButton("Client Operations");
        productButton = new JButton("Product Operations");
        orderButton = new JButton("Order Operations");

        initializeUI();
    }

    private void initializeUI() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(clientButton);
        buttonPanel.add(productButton);
        buttonPanel.add(orderButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);

        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    /** 
     * @param listener
     */
    public void setClientButtonListener(ActionListener listener) {
        clientButton.addActionListener(listener);
    }

    
    /** 
     * @param listener
     */
    public void setProductButtonListener(ActionListener listener) {
        productButton.addActionListener(listener);
    }

    public void setOrderButtonListener(ActionListener listener) {
        orderButton.addActionListener(listener);
    }
}
