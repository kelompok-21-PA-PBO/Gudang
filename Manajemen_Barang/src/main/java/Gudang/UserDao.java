package Gudang;

import java.sql.*;


public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }


    public boolean registerUser(String id_karyawan, String username, String password) {
    String insertUserSQL = "INSERT INTO karyawan (id_karyawan, username, password, karyawan_id_karyawan) VALUES (?, ?, ?, ?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
        preparedStatement.setString(1, id_karyawan);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, id_karyawan); // Menyamakan karyawan_id_karyawan dengan id_karyawan

        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    

    public boolean loginUser(String username, String password) {
    String loginSQL = "SELECT * FROM karyawan WHERE username = ? AND password = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(loginSQL)) {
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean isUsernameExists(String id_karyawan, String username) {
    String query = "SELECT COUNT(*) FROM karyawan WHERE id_karyawan = ? OR username = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, id_karyawan);
        preparedStatement.setString(2, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    

    public String loginUserAndGetId(String username, String password) {
        String loginSQL = "SELECT * FROM karyawan WHERE username = ? AND password = ?";
        String id_karyawan = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(loginSQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id_karyawan = resultSet.getString("id_karyawan");
            }
            
            return id_karyawan;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id_karyawan;
    }

    private static class db {

        private static Connection getConnection() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public db() {
        }
    }

}
