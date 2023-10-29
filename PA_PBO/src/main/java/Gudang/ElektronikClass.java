/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gudang;
import Database.DatabaseConnection;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ElektronikClass extends DatabaseConnection{
    public int id_barang;
    public String model;
    public String merk;
    
        public boolean createElektronik() {
        boolean isOperationSuccess = false;

        try {
            Connection connection = this.getConnection();
            String sql = "INSERT INTO elektronik (id_barang, model, merk) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, this.id_barang);
            preparedStatement.setString(2, this.model);
            preparedStatement.setString(3, this.merk);

            int result = preparedStatement.executeUpdate();
            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }

        return isOperationSuccess;
    }

        public void displayData(JTable table) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            try {
                Connection connection = this.getConnection();
                String sql = "SELECT * FROM elektronik";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id_barang = resultSet.getInt("id_barang");
                    String elektronikModel = resultSet.getString("model");
                    String merk = resultSet.getString("merk");

                    model.addRow(new Object[]{id_barang, model, merk});
                }

                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
}
