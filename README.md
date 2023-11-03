# Manajemen Penyimpanan Barang
PA PBO_Kelompok 21

#### Nama :
- Abdulah Faiz Tedjo Putro (2209116026)
- Farren Lyrananda Rachman (2209116035)

#### Deskripsi :
Aplikasi Manajemen Penyimpanan Gudang adalah sebuah aplikasi sederhana yang saya buat dengan menggunakan Java. Aplikasi ini dirancang untuk membantu pengguna atau karyawan dalam mengelola barang-barang yang disimpan dalam sebuah gudang atau ruang penyimpanan. Dengan antarmuka yang intuitif dan mudah digunakan, aplikasi ini memberikan kemudahan mengelola dalam mencatat, mengupdate, dan menampilkan barang-barang yang tersimpan dalam gudang.

#### Flowchart :
![FCGUDANG drawio](https://github.com/kelompok-21-PA-PBO/Gudang/assets/121870536/ca25630e-87ca-4bde-b636-c27c5230e683)

#### ERD :
![image](https://github.com/kelompok-21-PA-PBO/Gudang/assets/121870536/9fe9e789-41fe-4526-8e9a-dcb5b0d7cdad)

#### Hirarki Kelas :
![HirarkiGudang drawio](https://github.com/kelompok-21-PA-PBO/Gudang/assets/121870536/66d31235-538f-4307-95e7-49013d157341)

#### Screeshot kodingan berserta penjelasan

Pada program saya terdapat 3 package yaitu Database, GUI dan Gudang.


##### Database.java
      package Database;

      import java.sql.*;

      public class DatabaseConnection {
    
    public static Connection connection = null;
    public Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    private final static String dbHost = "localhost";
    private final static String dbName = "gudang";
    private final static String dbUser = "root";
    private final static String dbPass = "";

    public DatabaseConnection() {
        try {
            String url = "jdbc:mysql://" + dbHost + "/" + dbName;
            this.connection = DriverManager.getConnection(url, dbUser, dbPass);
            System.out.println("Database Connected!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     
    public Connection getConnection(){
        return connection;
    }
    
    public void disconnected(){
        try {
            connection.close();
            System.out.println("Database Closed");
        }catch (SQLException ex) {
            System.out.println("Database Failed to Close");
        }
    }

    }

##### BarangClass.java













