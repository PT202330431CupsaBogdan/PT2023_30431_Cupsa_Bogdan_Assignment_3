package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Client;

public class ClientDAO {
    
    private Connection conn;

    /**
     * Constructs a new ClientDAO object and establishes a database connection.
     *
     * @throws SQLException if a database access error occurs
     */

    public ClientDAO() throws SQLException
    {
        this.conn = ConnectionFactory.getConnection();
    }

    /**
     * Constructs a new ClientDAO object with an existing database connection.
     *
     * @param conn the existing database connection
     */
    public ClientDAO(Connection conn) {
        this.conn = conn;
    }

    
    /**
     * Adds a new client to the database.
     *
     * @param client the client to be added
     * @throws SQLException if a database access error occurs
     */
    public void addClient(Client client) throws SQLException {
        String sql = "INSERT INTO Client (id, name, address, phone) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, client.getId());
        stmt.setString(2, client.getName());
        stmt.setString(3, client.getAddress());
        stmt.setString(4, client.getPhone());
        stmt.executeUpdate();
    }

    
    /**
     * Updates an existing client in the database.
     *
     * @param client the client to be updated
     * @throws SQLException if a database access error occurs
     */
    public void updateClient(Client client) throws SQLException {
        String sql = "UPDATE Client SET name=?, address=?, phone=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, client.getName());
        stmt.setString(2, client.getAddress());
        stmt.setString(3, client.getPhone());
        stmt.setInt(4, client.getId());
        stmt.executeUpdate();
    }

    /**
     * Deletes a client from the database based on the specified ID.
     *
     * @param id the ID of the client to be deleted
     * @throws SQLException if a database access error occurs
     */
    public void deleteClient(int id) throws SQLException {
        String sql = "DELETE FROM Client WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of all clients
     * @throws SQLException if a database access error occurs
     */
    public List<Client> getAllClients() throws SQLException {
        String sql = "SELECT * FROM Client";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            Client client = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("phone"));
            clients.add(client);
        }
        return clients;
    }

    /**
     * Retrieves a client from the database based on the specified ID.
     *
     * @param id the ID of the client to be retrieved
     * @return the client with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    
    public Client getClientById(int id) throws SQLException {
        String sql = "SELECT * FROM Client WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Client client = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("phone"));
            return client;
        }
        return null;
    }
}
