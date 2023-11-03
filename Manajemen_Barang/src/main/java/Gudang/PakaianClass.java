package Gudang;

import java.sql.*;


public class PakaianClass extends BarangClass{
    public int id_barang;
    public String ukuran;
    public String warna;
    public String bahan;

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }
    
        public boolean createPakaian() {
        boolean isOperationSuccess = false;

        try {
            Connection connection = this.getConnection();
            String sql = "INSERT INTO pakaian (id_barang, ukuran, warna, bahan) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, this.id_barang);
            preparedStatement.setString(2, this.ukuran);
            preparedStatement.setString(3, this.warna);
            preparedStatement.setString(4, this.bahan);

            int result = preparedStatement.executeUpdate();
            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }

        return isOperationSuccess;
    }
     
}
