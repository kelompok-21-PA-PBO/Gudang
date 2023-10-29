package Gudang;
import Database.DatabaseConnection;
import java.sql.*;

public class BarangClass extends DatabaseConnection{
    public int id_barang;
    public String nama_barang;
    public String deskripsi;
    public int jumlah_barang;
    
    
    public boolean createBarang() {
            boolean isOperationSuccess = false;

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = this.getConnection();
                String sql = "INSERT INTO barang (id_barang, nama_barang, deskripsi, jumlah_barang) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, this.id_barang);
                preparedStatement.setString(2, this.nama_barang);
                preparedStatement.setString(3, this.deskripsi);
                preparedStatement.setInt(4, this.jumlah_barang);

                int result = preparedStatement.executeUpdate();
                isOperationSuccess = result > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Menutup preparedStatement terlebih dahulu
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                // Kemudian tutup koneksi
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
    
    
    
    
    
    
    
    
//public boolean createBarang(int id_karyawan) {
//    boolean isOperationSuccess = false;
//
//    Connection connection = null;
//    PreparedStatement preparedStatement = null;
//
//    try {
//        connection = this.getConnection();
//        String sql = "INSERT INTO barang (id_barang, nama_barang, deskripsi, jumlah_barang, karyawan_id_karyawan) VALUES (?, ?, ?, ?, ?)";
//        preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setInt(1, this.id_barang);
//        preparedStatement.setString(2, this.nama_barang);
//        preparedStatement.setString(3, this.deskripsi);
//        preparedStatement.setInt(4, this.jumlah_barang);
//        preparedStatement.setInt(5, id_karyawan); // Menggunakan id_karyawan yang telah disimpan dalam sesi
//
//        int result = preparedStatement.executeUpdate();
//        isOperationSuccess = result > 0;
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    } finally {
//        // Tutup preparedStatement dan koneksi seperti sebelumnya
//    }
//
//    return isOperationSuccess;
//}











}