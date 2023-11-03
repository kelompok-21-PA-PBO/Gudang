package Gudang;

import Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DataBarangClass extends DatabaseConnection{
    public String jumlah_barang;
    public String id_barang;
    
    public void setJumlahBarang(String jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }
    
    public void setIdBarang(String id_barang) {
        this.id_barang = id_barang;
    }
    
    public boolean createDataBarang() {
            boolean isOperationSuccess = false;

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = this.getConnection();
                String sql = "INSERT INTO data_barang_masuk (jumlah_barang_masuk, barang_id_barang) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, this.jumlah_barang);
                preparedStatement.setString(2, this.id_barang);

                int result = preparedStatement.executeUpdate();
                isOperationSuccess = result > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
      
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
    
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            return isOperationSuccess;
        }
    
}
