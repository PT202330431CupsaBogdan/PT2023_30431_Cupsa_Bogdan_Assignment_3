import java.sql.SQLException;
import view.*;

public class Main {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        MainView startPage = new MainView();

        startPage.setClientButtonListener(e -> {
            try {
                new ClientCRUDView();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        startPage.setProductButtonListener(e -> {
            try {
                new ProductCRUDView();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        startPage.setOrderButtonListener(e -> {
            try {
                new OrderCRUDView();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }
}
