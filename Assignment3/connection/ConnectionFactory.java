package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DRIVER_CLASS = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:E:/FACULTATE/An3Sem2/PT/Assignment3/database.db"; 

    
    /** 
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);

        } catch (SQLException e) {
            System.out.println("cupsa");
        }

        return connection;
    }
}
