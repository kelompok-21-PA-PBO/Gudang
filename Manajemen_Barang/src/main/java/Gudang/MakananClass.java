package Gudang;

import Database.DatabaseConnection;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MakananClass extends BarangClass{
    public int id_barang;
    public String tanggal_kadaluarsa;
    public String jenis_makanan;

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public void setTanggal_kadaluarsa(String tanggal_kadaluarsa) {
        this.tanggal_kadaluarsa = tanggal_kadaluarsa;
    }

    public void setJenis_makanan(String jenis_makanan) {
        this.jenis_makanan = jenis_makanan;
    }
    
    
  
    
        public boolean createMakanan() {
        boolean isOperationSuccess = false;

        try {
            Connection connection = this.getConnection();
            String sql = "INSERT INTO makanan (id_barang, tanggal_kadaluarsa, jenis_makanan) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, this.id_barang);
            preparedStatement.setString(2, this.tanggal_kadaluarsa);
            preparedStatement.setString(3, this.jenis_makanan);

            int result = preparedStatement.executeUpdate();
            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }

        return isOperationSuccess;
    }
    
    
    
}
