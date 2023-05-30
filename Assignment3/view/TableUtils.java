package view;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class TableUtils {
    
    /** 
     * @param objects
     * @return DefaultTableModel
     */
    public static <T> DefaultTableModel createTableModel(List<T> objects) {
        DefaultTableModel tableModel = new DefaultTableModel();

        if (objects.isEmpty()) {
            return tableModel;
        }

        T firstObject = objects.get(0);

        Field[] fields = firstObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            tableModel.addColumn(field.getName());
        }

        for (T object : objects) {
            Object[] rowData = new Object[fields.length];
            int columnIndex = 0;

            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(object);
                    rowData[columnIndex++] = value;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            tableModel.addRow(rowData);
        }

        return tableModel;
    }
}
