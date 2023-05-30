package bll;

import java.sql.SQLException;
import java.util.List;

import dao.*;
import model.*;

public class ClientBLL {

    private ClientDAO clientDAO;

    public ClientBLL() throws SQLException {
        this.clientDAO = new ClientDAO();
    }

    public void addClient(Client client) throws SQLException {
        clientDAO.addClient(client);
    }

    public void updateClient(Client client) throws SQLException {
        clientDAO.updateClient(client);
    }

    public void deleteClient(int clientId) throws SQLException {
        clientDAO.deleteClient(clientId);
    }

    public List<Client> getAllClients() throws SQLException {
        
        return clientDAO.getAllClients();
    }
}