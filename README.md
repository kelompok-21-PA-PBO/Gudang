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


##### DatabaseConnection.java
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
Import Statement: Kode mengimpor pustaka yang diperlukan dari paket java.sql untuk mengakses dan mengelola database.

Deklarasi Variabel: Kode ini mendefinisikan berbagai variabel yang akan digunakan dalam kelas, termasuk connection, statement, preparedStatement, dan resultSet. Variabel connection adalah koneksi ke database.

Informasi Koneksi Database: Informasi yang diperlukan untuk menghubungkan ke database MySQL disimpan dalam variabel statis seperti dbHost (alamat host database), dbName (nama database), dbUser (nama pengguna database), dan dbPass (kata sandi database).

Konstruktor DatabaseConnection: Ini adalah konstruktor kelas yang akan dijalankan saat objek DatabaseConnection dibuat. Di dalamnya, koneksi ke database MySQL dibuat dengan menggunakan informasi yang telah ditentukan sebelumnya. Jika koneksi berhasil, pesan "Database Connected!" akan dicetak ke konsol.

Metode getConnection(): Metode ini digunakan untuk mengambil objek koneksi ke database. Koneksi ini dapat digunakan untuk menjalankan perintah SQL.

Metode disconnected(): Metode ini digunakan untuk menutup koneksi ke database. Jika penutupan berhasil, pesan "Database Closed" dicetak ke konsol. Jika terjadi kesalahan saat menutup koneksi, pesan "Database Failed to Close" dicetak.

Kelas DatabaseConnection ini dirancang untuk memudahkan koneksi dan pemutusan koneksi ke database MySQL dengan parameter koneksi yang telah ditentukan sebelumnya.





##### BarangClass.java
      package Gudang;
      import Database.DatabaseConnection;
      import java.sql.*;

            public class BarangClass extends DatabaseConnection{
                public int id_barang;
                public String nama_barang;
                public String deskripsi;
                public int jumlah_barang;
                public String karyawan_id;
                
             
                public void setIdBarang(int id_barang) {
                    this.id_barang = id_barang;
                }
                
                
                public void setNamaBarang(String nama_barang) {
                    this.nama_barang = nama_barang;
                }
            
               
                public void setDeskripsi(String deskripsi) {
                    this.deskripsi = deskripsi;
                }
            
              
                public void setJumlahBarang(int jumlah_barang) {
                    this.jumlah_barang = jumlah_barang;
                }
                
               
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

Meng-extend Kelas DatabaseConnection: Kelas BarangClass merupakan subkelas dari DatabaseConnection, yang berarti ia dapat menggunakan koneksi database yang telah diatur dalam kelas induknya.

Variabel Anggota: Kelas ini memiliki beberapa variabel anggota untuk menyimpan data barang, seperti id_barang, nama_barang, deskripsi, jumlah_barang, dan karyawan_id.

Setter: Terdapat metode-metode setter untuk mengatur nilai-nilai variabel anggota di atas.

createBarang(): Metode ini digunakan untuk menambahkan data barang ke database. Ia menggunakan parameter yang telah diatur sebelumnya dan melakukan operasi INSERT SQL untuk memasukkan data baru ke tabel barang. Hasil operasi (berhasil atau tidak) dikembalikan sebagai nilai boolean.

updateBarang(): Metode ini digunakan untuk memperbarui data barang dalam database. Ia mengambil parameter yang telah diatur dan menjalankan operasi UPDATE SQL untuk mengubah deskripsi dan jumlah barang berdasarkan ID barang tertentu. Hasil operasi (berhasil atau tidak) dikembalikan sebagai nilai boolean.

deleteBarang(): Metode ini digunakan untuk menghapus data barang dari database berdasarkan ID barang tertentu. Ia melakukan operasi DELETE SQL untuk menghapus data sesuai ID barang yang ditentukan. Hasil operasi (berhasil atau tidak) dikembalikan sebagai nilai boolean.

Selain itu, dalam setiap metode, koneksi database dibuka, operasi SQL dijalankan, dan koneksi ditutup dalam blok try-catch-finally untuk menangani pengecualian dan memastikan bahwa sumber daya koneksi dibersihkan dengan benar.

##### ElektronikClass.java

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
kelas Java ElektronikClass yang merupakan subkelas dari BarangClass. Kelas ini digunakan untuk mengelola data elektronik dan melakukan operasi tertentu terkait elektronik di dalam database. Berikut penjelasan singkatnya:

Variabel Anggota: Kelas ini memiliki variabel anggota seperti id_barang, model, dan merk untuk menyimpan data terkait elektronik.

Setter: Terdapat metode-metode setter untuk mengatur nilai variabel anggota di atas.

createElektronik(): Metode ini digunakan untuk menambahkan data elektronik ke database. Ia menggunakan parameter yang telah diatur sebelumnya dan melakukan operasi INSERT SQL untuk memasukkan data baru ke tabel "elektronik." Hasil operasi (berhasil atau tidak) dikembalikan sebagai nilai boolean.

Dalam metode createElektronik(), koneksi database dibuka, operasi SQL dijalankan, dan koneksi ditutup dalam blok try-catch-finally untuk menangani pengecualian. Setelah operasi SQL dijalankan, variabel isOperationSuccess akan menunjukkan apakah operasi tersebut berhasil atau tidak.

Perlu diperhatikan bahwa kelas ini mewarisi variabel id_barang dari kelas BarangClass. Namun, jika id_barang dari ElektronikClass digunakan, maka nilai id_barang dalam kelas ini akan menggantikan nilai id_barang dari kelas BarangClass.

##### MakananClass.java

            package Gudang;
            
            
            import java.sql.*;
            
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



kelas Java MakananClass yang juga merupakan subkelas dari BarangClass. Kelas ini digunakan untuk mengelola data makanan dan melakukan operasi tertentu terkait makanan di dalam database. Berikut penjelasan singkatnya:

Variabel Anggota: Kelas ini memiliki variabel anggota seperti id_barang, tanggal_kadaluarsa, dan jenis_makanan untuk menyimpan data terkait makanan.

Setter: Terdapat metode-metode setter untuk mengatur nilai variabel anggota di atas.

createMakanan(): Metode ini digunakan untuk menambahkan data makanan ke database. Ia menggunakan parameter yang telah diatur sebelumnya dan melakukan operasi INSERT SQL untuk memasukkan data baru ke tabel "makanan." Hasil operasi (berhasil atau tidak) dikembalikan sebagai nilai boolean.

Dalam metode createMakanan(), koneksi database dibuka, operasi SQL dijalankan, dan koneksi ditutup dalam blok try-catch-finally untuk menangani pengecualian. Setelah operasi SQL dijalankan, variabel isOperationSuccess akan menunjukkan apakah operasi tersebut berhasil atau tidak.

Perlu diperhatikan bahwa kelas ini juga mewarisi variabel id_barang dari kelas BarangClass. Namun, jika id_barang dari MakananClass digunakan, maka nilai id_barang dalam kelas ini akan menggantikan nilai id_barang dari kelas BarangClass.


##### PakaianClass.java

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

kelas Java PakaianClass, yang juga merupakan subkelas dari BarangClass. Kelas ini digunakan untuk mengelola data pakaian dan melakukan operasi tertentu terkait pakaian di dalam database. Berikut penjelasan singkatnya:

Variabel Anggota: Kelas ini memiliki variabel anggota seperti id_barang, ukuran, warna, dan bahan untuk menyimpan data terkait pakaian.

Setter: Terdapat metode-metode setter untuk mengatur nilai variabel anggota di atas.

createPakaian(): Metode ini digunakan untuk menambahkan data pakaian ke database. Ia menggunakan parameter yang telah diatur sebelumnya dan melakukan operasi INSERT SQL untuk memasukkan data baru ke tabel "pakaian." Hasil operasi (berhasil atau tidak) dikembalikan sebagai nilai boolean.

Dalam metode createPakaian(), koneksi database dibuka, operasi SQL dijalankan, dan koneksi ditutup dalam blok try-catch-finally untuk menangani pengecualian. Setelah operasi SQL dijalankan, variabel isOperationSuccess akan menunjukkan apakah operasi tersebut berhasil atau tidak.

Perlu diperhatikan bahwa kelas ini juga mewarisi variabel id_barang dari kelas BarangClass. Namun, jika id_barang dari PakaianClass digunakan, maka nilai id_barang dalam kelas ini akan menggantikan nilai id_barang dari kelas BarangClass.

##### DataBarangClass.java

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

kelas Java DataBarangClass yang digunakan untuk mengelola data terkait barang masuk dalam database. Berikut penjelasan singkatnya:

Variabel Anggota: Kelas ini memiliki variabel anggota jumlah_barang dan id_barang untuk menyimpan informasi tentang jumlah barang masuk dan ID barang yang terkait.

Setter: Terdapat metode-metode setter untuk mengatur nilai variabel anggota di atas.

createDataBarang(): Metode ini digunakan untuk menambahkan data barang masuk ke dalam database. Ia menggunakan parameter yang telah diatur sebelumnya dan menjalankan operasi INSERT SQL untuk memasukkan data baru ke dalam tabel "data_barang_masuk." Hasil operasi (berhasil atau tidak) dikembalikan sebagai nilai boolean.

Dalam metode createDataBarang(), koneksi database dibuka, operasi SQL dijalankan, dan koneksi ditutup dalam blok try-catch-finally untuk menangani pengecualian. Setelah operasi SQL dijalankan, variabel isOperationSuccess akan menunjukkan apakah operasi tersebut berhasil atau tidak.

##### UserDao.java

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

kelas Java UserDao yang bertanggung jawab untuk berinteraksi dengan tabel "karyawan" dalam database. Berikut penjelasan singkatnya:

Konstruktor UserDao: Kelas ini memiliki sebuah konstruktor yang menerima objek Connection sebagai argumen. Konstruktor ini digunakan untuk menginisialisasi koneksi database yang akan digunakan oleh objek UserDao.

Metode registerUser: Metode ini digunakan untuk mendaftarkan pengguna baru ke dalam database. Ia menerima tiga parameter, yaitu id_karyawan, username, dan password, dan melakukan operasi INSERT SQL untuk memasukkan data pengguna baru ke dalam tabel "karyawan." Hasil operasi (berhasil atau tidak) dikembalikan sebagai nilai boolean.

Metode loginUser: Metode ini digunakan untuk memeriksa apakah pengguna dengan username dan password tertentu ada dalam database. Ia menjalankan operasi SELECT SQL dengan parameter username dan password yang diberikan. Jika pengguna ditemukan, metode ini mengembalikan true; jika tidak, mengembalikan false.

Metode isUsernameExists: Metode ini digunakan untuk memeriksa apakah id_karyawan atau username tertentu sudah ada dalam database. Ia menjalankan operasi SELECT SQL untuk menghitung jumlah entri dengan id_karyawan atau username yang sesuai. Jika jumlahnya lebih dari 0, berarti ada yang sudah menggunakan id_karyawan atau username tersebut.

Metode loginUserAndGetId: Metode ini mirip dengan loginUser, tetapi selain memeriksa apakah pengguna ada, ia juga mengembalikan id_karyawan dari pengguna yang berhasil masuk. Ini dapat berguna jika Anda perlu mengakses id_karyawan pengguna setelah login.

Kelas ini digunakan untuk berbagai tugas otentikasi dan manajemen pengguna dalam aplikasi yang berinteraksi dengan database "karyawan."






















