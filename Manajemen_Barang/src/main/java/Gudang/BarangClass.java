package Gudang;
import Database.DatabaseConnection;
import java.sql.*;

public class BarangClass extends DatabaseConnection{
    public int id_barang;
    public String nama_barang;
    public String deskripsi;
    public int jumlah_barang;
    public String karyawan_id;
    
        // Setter untuk id_barang
    public void setIdBarang(int id_barang) {
        this.id_barang = id_barang;
    }
    
    // Setter untuk deskripsi
    public void setNamaBarang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    // Setter untuk deskripsi
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    // Setter untuk jumlah_barang
    public void setJumlahBarang(int jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }
    
    // Setter untuk karyawan ID
    public void setKaryawanID(String karyawan_id) {
        this.karyawan_id = karyawan_id;
    }
    
    
    public boolean createBarang() {
            boolean isOperationSuccess = false;

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = this.getConnection();
                String sql = "INSERT INTO barang (id_barang, nama_barang, deskripsi, jumlah_barang, karyawan_id_karyawan) VALUES (?, ?, ?, ?,?)";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, this.id_barang);
                preparedStatement.setString(2, this.nama_barang);
                preparedStatement.setString(3, this.deskripsi);
                preparedStatement.setInt(4, this.jumlah_barang);
                preparedStatement.setString(5, this.karyawan_id);

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
    
    
    public boolean updateBarang() {
        boolean isOperationSuccess = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();
            String sql = "UPDATE barang SET deskripsi = ?, jumlah_barang = ? WHERE id_barang = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, this.deskripsi);
            preparedStatement.setInt(2, this.jumlah_barang);
            preparedStatement.setInt(3, this.id_barang);

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
    
   
    
    //delete satu2
//    public boolean deleteBarang() {
//    boolean isOperationSuccess = false;
//
//    try {
//        Connection connection = this.getConnection();
//        String sql = "DELETE FROM barang WHERE id_barang = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setInt(1, this.id_barang);
//
//        int result = preparedStatement.executeUpdate();
//        isOperationSuccess = result > 0;
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    } finally {
//        // Tutup sumber daya
//    }
//
//    return isOperationSuccess;
//}
    

    
    //cascade
    
    public boolean deleteBarang() {
    boolean isOperationSuccess = false;

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = this.getConnection();
        String sql = "DELETE FROM barang WHERE id_barang = ?";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, this.id_barang);

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

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //create mengambil data id yang berhasil login
    
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











