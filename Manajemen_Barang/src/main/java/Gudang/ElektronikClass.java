package Gudang;
import java.sql.*;


public class ElektronikClass extends BarangClass{
    public int id_barang;
    public String model;
    public String merk;
    
    
        public void setIdBarang(int id_barang) {
            this.id_barang = id_barang;
        }
        
        public void setModel(String model) {
            this.model = model;
        }
        
        public void setMerk(String merk) {
            this.merk = merk;
        }
    
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
    
}
