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

#### Penjelasan GUI

#### GUI Delete Menu
      package GUI;

      import Gudang.*;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import javax.swing.JOptionPane;
      
      /**
       *
       * @author Lenovo
       */
      public class DeleteMenu extends javax.swing.JFrame {
          int xx, xy;
          public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
          public DeleteMenu() {
              initComponents();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              idbarangbox = new javax.swing.JTextField();
              deleteButton = new javax.swing.JButton();
              jLabel2 = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
              getContentPane().add(idbarangbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 220, -1));
      
              deleteButton.setText("Delete");
              deleteButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      deleteButtonActionPerformed(evt);
                  }
              });
              getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 110, 40));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel2MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
          String idBarangText = idbarangbox.getText();
      
          if (idBarangText.isEmpty()) {
              JOptionPane.showMessageDialog(this, "ID Barang tidak boleh kosong.");
              return; // Keluar dari metode jika ID kosong
          }
      
          try {
              int id_barang = Integer.parseInt(idBarangText);
      
              if (id_barang <= 0) {
                  JOptionPane.showMessageDialog(this, "ID Barang harus merupakan angka positif.");
              } else {
                  // Membuat objek BarangClass
                  BarangClass barang = new BarangClass();
      
                  // Mengatur id_barang yang akan dihapus
                  barang.setIdBarang(id_barang);
      
                  // Memanggil fungsi deleteBarang untuk menghapus barang
                  boolean isDeleted = barang.deleteBarang();
      
                  if (isDeleted) {
                      // Barang berhasil dihapus
                      JOptionPane.showMessageDialog(this, "Barang berhasil dihapus.");
                  } else {
                      // Barang tidak dapat dihapus, tampilkan pesan kesalahan
                      JOptionPane.showMessageDialog(this, "Gagal menghapus barang.");
                  }
              }
          } catch (NumberFormatException ex) {
              JOptionPane.showMessageDialog(this, "ID Barang harus berupa angka.");
          }
              
      
          }                                            
      
          private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              MainMenu mm = new MainMenu();
              mm.setVisible(true);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(DeleteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(DeleteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(DeleteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(DeleteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new DeleteMenu().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JButton deleteButton;
          private javax.swing.JTextField idbarangbox;
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          // End of variables declaration                   
      }


Kode ini adalah bagian dari antarmuka pengguna (GUI) yang memungkinkan pengguna untuk menghapus data barang dari sistem. Mari kita bahas dalam bahasa yang lebih mudah dipahami:

Kode ini adalah bagian dari antarmuka pengguna (GUI) yang digunakan untuk menghapus barang dari sistem.

Terdapat metode close() yang digunakan untuk menutup jendela saat ini. Ini dilakukan dengan mengirimkan peristiwa penutupan jendela.

Terdapat konstruktor DeleteMenu(), yang digunakan untuk menginisialisasi tampilan GUI saat jendela dijalankan.

Di dalam konstruktor, metode initComponents() akan dijalankan. Metode ini menginisialisasi komponen-komponen antarmuka pengguna seperti kotak teks dan tombol.

Pada metode deleteButtonActionPerformed(), ketika tombol "Delete" ditekan, kode akan melakukan hal berikut:

Mendapatkan ID barang yang akan dihapus dari kotak teks idbarangbox.
Memeriksa apakah ID barang tersebut kosong. Jika kosong, akan menampilkan pesan kesalahan.
Mengonversi ID barang menjadi bilangan bulat dan memeriksa apakah ID tersebut positif. Jika tidak valid, akan menampilkan pesan kesalahan.
Jika ID barang valid, maka akan membuat objek BarangClass dan mengatur ID barang yang akan dihapus.
Memanggil metode deleteBarang() untuk menghapus barang tersebut dari sistem.
Menampilkan pesan berhasil atau gagal sesuai dengan hasil penghapusan barang.
Terdapat juga metode jLabel2MouseClicked(), yang akan menutup jendela saat tombol "Back" ditekan.

Terdapat juga metode untuk mengatur perpindahan jendela dengan menggeserkan jendela menggunakan mouse.

Kode terakhir adalah metode main(), yang menginisialisasi dan menampilkan jendela saat aplikasi dijalankan.

#### GUI Menu Elektronik
      package GUI;
      
      import Database.DatabaseConnection;
      import Gudang.*;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import java.sql.*;
      import javax.swing.JOptionPane;
      
      public class Elektronik extends javax.swing.JFrame {
          int xx, xy;
          private Statement statement;
              public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
      
             
              
      private boolean isIdDuplicate(int id, String tableName) {
          DatabaseConnection db = null; // Deklarasikan variabel di luar blok try
          try {
              db = new DatabaseConnection();
              Connection connection = db.getConnection();
              statement = connection.createStatement();
      
              String query = "SELECT * FROM " + tableName + " WHERE id_barang = " + id;
              ResultSet resultSet = statement.executeQuery(query);
      
              return resultSet.next(); // Mengembalikan true jika ID sudah ada, false jika belum ada
      
          } catch (SQLException ex) {
              ex.printStackTrace();
              // Handle exceptions
          } finally {
              if (db != null) {
                  db.disconnected();
              }
          }
      
          return false;
      }        
              
              
              
              
              
              
          public Elektronik() {
              initComponents();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              txtIdBarang = new javax.swing.JTextField();
              txtModel = new javax.swing.JTextField();
              txtMerk = new javax.swing.JTextField();
              txtDeskripsi = new javax.swing.JTextField();
              txtNamaBarang = new javax.swing.JTextField();
              txtJumlahBarang = new javax.swing.JTextField();
              btncreate = new javax.swing.JButton();
              back = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
              lblKaryawanID = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              addWindowListener(new java.awt.event.WindowAdapter() {
                  public void windowOpened(java.awt.event.WindowEvent evt) {
                      formWindowOpened(evt);
                  }
              });
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              txtIdBarang.setBorder(null);
              getContentPane().add(txtIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 220, 30));
      
              txtModel.setBorder(null);
              txtModel.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      txtModelActionPerformed(evt);
                  }
              });
              getContentPane().add(txtModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 220, 30));
      
              txtMerk.setBorder(null);
              getContentPane().add(txtMerk, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 220, 30));
      
              txtDeskripsi.setBorder(null);
              getContentPane().add(txtDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 220, 30));
      
              txtNamaBarang.setBorder(null);
              getContentPane().add(txtNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 220, 30));
      
              txtJumlahBarang.setBorder(null);
              txtJumlahBarang.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      txtJumlahBarangActionPerformed(evt);
                  }
              });
              getContentPane().add(txtJumlahBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 220, 30));
      
              btncreate.setText("Create");
              btncreate.setBorder(null);
              btncreate.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      btncreateActionPerformed(evt);
                  }
              });
              getContentPane().add(btncreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 110, 40));
      
              back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              back.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      backMouseClicked(evt);
                  }
              });
              getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              lblKaryawanID.setText("jLabel2");
              getContentPane().add(lblKaryawanID, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 470, -1, -1));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void txtModelActionPerformed(java.awt.event.ActionEvent evt) {                                         
              // TODO add your handling code here:
          }                                        
      
          private void txtJumlahBarangActionPerformed(java.awt.event.ActionEvent evt) {                                                
              // TODO add your handling code here:
          }                                               
      
          private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {                                          
          // Baca nilai dari teks field untuk tabel barang
          String idBarangText = txtIdBarang.getText().trim();
          String namaBarang = txtNamaBarang.getText().trim();
          String deskripsi = txtDeskripsi.getText().trim();
          String jumlahBarangText = txtJumlahBarang.getText().trim();
      
              // Check if any of the fields is empty
          if (idBarangText.isEmpty() || namaBarang.isEmpty() || deskripsi.isEmpty() || jumlahBarangText.isEmpty()) {
              JOptionPane.showMessageDialog(null, "Semua field harus diisi.");
              return;
          }
          
              // Check if the ID contains only digits
          if (!idBarangText.matches("\\d+")) {
              JOptionPane.showMessageDialog(null, "ID barang harus berupa angka.");
              return;
          }
          
          
          int idBarang = Integer.parseInt(idBarangText);
          int jumlahBarang = Integer.parseInt(jumlahBarangText);
          
          
              // Check if the ID is negative
          if (idBarang < 1) {
              JOptionPane.showMessageDialog(null, "ID barang tidak boleh negatif.");
              return;
          }
          
              // Check if the quantity is less than 1
          if (jumlahBarang < 1) {
              JOptionPane.showMessageDialog(null, "Jumlah barang tidak boleh kurang dari 1.");
              return;
          }
          
          // Check if the ID already exists in the "barang" table
          if (isIdDuplicate(idBarang, "barang")) {
              JOptionPane.showMessageDialog(null, "ID barang sudah digunakan. Tolong gunakan ID lain.");
              return;
          }
      
          // Buat objek BarangClass dan isi atributnya
          BarangClass barang = new BarangClass();
          barang.setIdBarang(idBarang);
          barang.setNamaBarang(namaBarang);
          barang.setDeskripsi(deskripsi);
          barang.setJumlahBarang(jumlahBarang);
          barang.setKaryawanID(lblKaryawanID.getText());
      
          // Panggil metode createBarang
          if (barang.createBarang()) {
              JOptionPane.showMessageDialog(null, "Data barang berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data barang.");
          }
      
          // Baca nilai dari teks field untuk tabel elektronik
          int idElektronik = Integer.parseInt(txtIdBarang.getText());
          String model = txtModel.getText();
          String merk = txtMerk.getText();
      
          // Buat objek ElektronikClass dan isi atributnya
          ElektronikClass elektronik = new ElektronikClass();
          elektronik.setIdBarang(idElektronik);
          elektronik.setModel(model);
          elektronik.setMerk(merk);
      
          // Panggil metode createElektronik
          if (elektronik.createElektronik()) {
              JOptionPane.showMessageDialog(null, "Data elektronik berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data elektronik.");
          }
          
          // Buat objek DataBarang dan isi atributnya
          DataBarangClass dataBarang = new DataBarangClass();
          dataBarang.setIdBarang(String.valueOf(idBarang));
          dataBarang.setJumlahBarang(String.valueOf(jumlahBarang));
          
          // Panggil metode createDataBarang
          if (dataBarang.createDataBarang()) {
              JOptionPane.showMessageDialog(null, "Data Barang Masuk berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data barang masuk.");
          }
      
          }                                         
      
          private void backMouseClicked(java.awt.event.MouseEvent evt) {                                  
              close();
              SelectCategory mm = new SelectCategory();
              mm.lblKaryawanID.setText(lblKaryawanID.getText());
              mm.setVisible(true);
          }                                 
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
              // TODO add your handling code here:
              // get KaryawanID
          
          System.out.println("=========== KARYAWAN ID : " + lblKaryawanID.getText());
          }                                 
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(Elektronik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(Elektronik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(Elektronik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(Elektronik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new Elektronik().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JLabel back;
          private javax.swing.JButton btncreate;
          private javax.swing.JLabel jLabel1;
          public javax.swing.JLabel lblKaryawanID;
          private javax.swing.JTextField txtDeskripsi;
          private javax.swing.JTextField txtIdBarang;
          private javax.swing.JTextField txtJumlahBarang;
          private javax.swing.JTextField txtMerk;
          private javax.swing.JTextField txtModel;
          private javax.swing.JTextField txtNamaBarang;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) yang digunakan untuk memasukkan data barang elektronik ke dalam sistem. Mari kita bahas dalam bahasa yang lebih mudah dipahami:

Kode ini adalah bagian dari antarmuka pengguna (GUI) yang digunakan untuk memasukkan data barang elektronik ke dalam sistem.

Terdapat metode close() yang digunakan untuk menutup jendela saat ini dengan mengirimkan peristiwa penutupan jendela.

Terdapat metode isIdDuplicate(int id, String tableName) yang digunakan untuk memeriksa apakah ID barang sudah ada dalam tabel tertentu dalam database.

Terdapat konstruktor Elektronik(), yang digunakan untuk menginisialisasi tampilan GUI saat jendela dijalankan.

Di dalam konstruktor, metode initComponents() akan dijalankan. Metode ini menginisialisasi komponen-komponen antarmuka pengguna seperti kotak teks dan tombol.

Pada metode btncreateActionPerformed(), ketika tombol "Create" ditekan, kode akan melakukan hal berikut:

Membaca nilai-nilai dari kotak teks yang berisi ID barang, nama barang, deskripsi, jumlah barang, model, dan merk elektronik.
Memeriksa apakah salah satu kotak teks kosong. Jika ada yang kosong, akan menampilkan pesan kesalahan.
Memeriksa apakah ID barang hanya berisi digit angka. Jika tidak valid, akan menampilkan pesan kesalahan.
Mengonversi ID barang dan jumlah barang menjadi tipe data integer.
Memeriksa apakah ID barang dan jumlah barang positif. Jika tidak valid, akan menampilkan pesan kesalahan.
Memeriksa apakah ID barang sudah ada dalam tabel "barang" dengan menggunakan metode isIdDuplicate().
Membuat objek BarangClass dan mengisi atribut-atributnya.
Memanggil metode createBarang() untuk menyimpan data barang ke dalam database.
Membaca nilai-nilai untuk tabel elektronik (model dan merk).
Membuat objek ElektronikClass dan mengisi atribut-atributnya.
Memanggil metode createElektronik() untuk menyimpan data barang elektronik ke dalam database.
Membaca nilai-nilai untuk tabel data barang masuk (jumlah barang).
Membuat objek DataBarangClass dan mengisi atribut-atributnya.
Memanggil metode createDataBarang() untuk menyimpan data barang masuk ke dalam database.
Terdapat metode untuk mengatur perpindahan jendela dengan menggeserkan jendela menggunakan mouse.

Terdapat juga metode formWindowOpened() yang akan menampilkan ID karyawan saat jendela dibuka.

Kode terakhir adalah metode main(), yang menginisialisasi dan menampilkan jendela saat aplikasi dijalankan.

#### GUI Menu Login
      package GUI;
      
      import Database.DatabaseConnection;
      import Gudang.*;
      import GUI.*;
      import javax.swing.JOptionPane;
      import java.awt.*;
      import java.awt.event.*;
      
      
      public class Login extends javax.swing.JFrame {
          int xx, xy;
          
          public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
        
          public Login() {
              initComponents();
          }
      
          
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              usernamebox = new javax.swing.JTextField();
              exitbut = new javax.swing.JLabel();
              btnLogin = new javax.swing.JButton();
              jPasswordField1 = new javax.swing.JPasswordField();
              jLabel3 = new javax.swing.JLabel();
              jButton1 = new javax.swing.JButton();
              jLabel2 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              usernamebox.setBorder(null);
              usernamebox.setSelectedTextColor(new java.awt.Color(0, 0, 0));
              usernamebox.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      usernameboxActionPerformed(evt);
                  }
              });
              getContentPane().add(usernamebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 220, 30));
      
              exitbut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
              exitbut.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      exitbutMouseClicked(evt);
                  }
              });
              getContentPane().add(exitbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 50, -1));
      
              btnLogin.setText("Login");
              btnLogin.setBorder(null);
              btnLogin.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      btnLoginActionPerformed(evt);
                  }
              });
              getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 140, 60));
      
              jPasswordField1.setBorder(null);
              getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 220, 30));
      
              jLabel3.setForeground(new java.awt.Color(255, 255, 255));
              jLabel3.setText("Don't have account?");
              getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 130, 20));
      
              jButton1.setText("SignUp Here");
              jButton1.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      jButton1ActionPerformed(evt);
                  }
              });
              getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 330, -1, -1));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Login (1).png"))); // NOI18N
              jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel2MouseDragged(evt);
                  }
              });
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel2MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void usernameboxActionPerformed(java.awt.event.ActionEvent evt) {                                            
             
          }                                           
      
          private void exitbutMouseClicked(java.awt.event.MouseEvent evt) {                                     
      //        System.exit(0);
          int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar aplikasi?", ":(", JOptionPane.YES_NO_OPTION);
          
          if (response == JOptionPane.YES_OPTION) {
              // Jika pengguna memilih "Ya", maka keluar dari aplikasi
              System.exit(0);
          } else if (response == JOptionPane.NO_OPTION) {
              // Jika pengguna memilih "Tidak", tidak perlu melakukan apa-apa
          }
          }                                    
      
          private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
          close();
      
          String username = usernamebox.getText();
          String password = new String(jPasswordField1.getPassword());
      
          if (username.isEmpty() || password.isEmpty()) {
              JOptionPane.showMessageDialog(null, "Username dan Password tidak boleh kosong.");
          } else {
              DatabaseConnection db = new DatabaseConnection();
              UserDao userDao = new UserDao(db.getConnection());
      
              if (userDao.loginUser(username, password)) {
                  // Login berhasil
                  this.dispose();
                  MainMenu mainMenu = new MainMenu();
                  JOptionPane.showMessageDialog(null, "Berhasil login");
                  mainMenu.lblKaryawanID.setText(String.valueOf(userDao.loginUserAndGetId(username, password)));
                  mainMenu.setVisible(true);
              } else {
                  JOptionPane.showMessageDialog(null, "Username atau Password salah");
              }
      
              db.disconnected();
          }
      
          }                                        
      
          private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
              close();
              SignUp su = new SignUp();
              su.setVisible(true);
          }                                        
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new Login().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JButton btnLogin;
          private javax.swing.JLabel exitbut;
          private javax.swing.JButton jButton1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JLabel jLabel3;
          public javax.swing.JPasswordField jPasswordField1;
          public javax.swing.JTextField usernamebox;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) untuk sistem login. Mari kita jelaskan dengan lebih mudah dipahami:

Kode ini digunakan untuk membuat antarmuka login, yang memungkinkan pengguna untuk memasukkan username dan password.

Terdapat metode close(), yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Konstruktor Login() digunakan untuk menginisialisasi tampilan GUI saat jendela dijalankan.

Pada metode initComponents(), komponen-komponen antarmuka pengguna seperti kotak teks, tombol, dan label diinisialisasi.

Terdapat metode usernameboxActionPerformed(), yang belum digunakan dalam kode ini.

Terdapat gambar ikon "close" (exitbut) yang digunakan untuk menutup aplikasi saat diklik.

Terdapat tombol "Login" yang digunakan untuk memproses masukan pengguna dan melakukan login.

Terdapat kotak teks untuk username dan kotak teks sandi (password).

Terdapat label yang menawarkan opsi untuk mendaftar (sign up) jika pengguna belum memiliki akun.

Terdapat metode btnLoginActionPerformed(), yang dipanggil saat tombol "Login" ditekan. Kode dalam metode ini melakukan hal berikut:

Mengambil nilai username dan password dari kotak teks.
Memeriksa apakah kedua kotak teks terisi. Jika tidak, menampilkan pesan kesalahan.
Membuat koneksi ke database dan menggunakan objek UserDao untuk memeriksa apakah pengguna telah berhasil login.
Jika login berhasil, jendela ini ditutup, dan jendela utama (MainMenu) ditampilkan dengan menampilkan ID pengguna yang berhasil login.
Jika login gagal, menampilkan pesan kesalahan.
Terdapat tombol "SignUp Here" yang memungkinkan pengguna untuk mendaftar (sign up) ke dalam sistem.

Terdapat metode untuk menggeser jendela saat mouse digunakan.

Terdapat metode exitbutMouseClicked(), yang memungkinkan pengguna untuk mengkonfirmasi sebelum menutup aplikasi.

Kode terakhir adalah metode main(), yang menginisialisasi dan menampilkan jendela login saat aplikasi dijalankan.

#### GUI Main Menu
      package GUI;
      
      import Database.DatabaseConnection;
      import Gudang.*;
      import GUI.*;
      import javax.swing.JOptionPane;
      import java.awt.*;
      import java.awt.event.*;
      /**
       *
       * @author Lenovo
       */
      public class MainMenu extends javax.swing.JFrame {
          int xx, xy;
          
          public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
      
          public MainMenu() {
              initComponents();
      
              
              
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              jLabel7 = new javax.swing.JLabel();
              jLabel2 = new javax.swing.JLabel();
              btncreate = new javax.swing.JLabel();
              btnshow = new javax.swing.JLabel();
              btndelete = new javax.swing.JLabel();
              btnupdate = new javax.swing.JLabel();
              jLabel9 = new javax.swing.JLabel();
              lblKaryawanID = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel2MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 50, 50));
      
              btncreate.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      btncreateMouseClicked(evt);
                  }
              });
              getContentPane().add(btncreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 180, 130));
      
              btnshow.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      btnshowMouseClicked(evt);
                  }
              });
              getContentPane().add(btnshow, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 170, 120));
      
              btndelete.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      btndeleteMouseClicked(evt);
                  }
              });
              getContentPane().add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 180, 130));
      
              btnupdate.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      btnupdateMouseClicked(evt);
                  }
              });
              getContentPane().add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 326, 170, 120));
      
              jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MenuUtama (1).png"))); // NOI18N
              jLabel9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel9MouseDragged(evt);
                  }
              });
              jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel9MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              lblKaryawanID.setText("jLabel1");
              getContentPane().add(lblKaryawanID, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, -1, -1));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {                                     
              close();
              Login mm = new Login();
              mm.setVisible(true);
          }                                    
      
          private void btncreateMouseClicked(java.awt.event.MouseEvent evt) {                                       
              close();
              SelectCategory cm = new SelectCategory();
              cm.lblKaryawanID.setText(lblKaryawanID.getText());
              cm.setVisible(true);
          }                                      
      
          private void btnshowMouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              ShowMenu sm = new ShowMenu();
              sm.setVisible(true);
          }                                    
      
          private void btndeleteMouseClicked(java.awt.event.MouseEvent evt) {                                       
              close();
              DeleteMenu dm = new DeleteMenu();
              dm.setVisible(true);
          }                                      
      
          private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {                                       
              close();
              UpdateMenu um = new UpdateMenu();
              um.setVisible(true);
          }                                      
      
          private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel9MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new MainMenu().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JLabel btncreate;
          private javax.swing.JLabel btndelete;
          private javax.swing.JLabel btnshow;
          private javax.swing.JLabel btnupdate;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JLabel jLabel7;
          private javax.swing.JLabel jLabel9;
          public javax.swing.JLabel lblKaryawanID;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) yang merupakan menu utama dari aplikasi. Mari jelaskan dengan lebih mudah dipahami:

Kode ini digunakan untuk membuat tampilan menu utama aplikasi, yang menyediakan opsi untuk mengakses fitur-fitur utama aplikasi.

Terdapat metode close(), yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Konstruktor MainMenu() digunakan untuk menginisialisasi tampilan GUI saat jendela dijalankan.

Pada metode initComponents(), komponen-komponen antarmuka pengguna seperti label, gambar, dan tindakan mouse diinisialisasi.

Terdapat empat gambar yang mewakili opsi menu, yaitu "Create", "Show", "Delete", dan "Update". Gambar ini adalah tombol yang akan mengarahkan pengguna ke fitur-fitur terkait.

Terdapat metode btncreateMouseClicked(), btnshowMouseClicked(), btndeleteMouseClicked(), dan btnupdateMouseClicked(), yang akan mengarahkan pengguna ke jendela yang sesuai dengan opsi yang mereka pilih. Jendela-jendela ini mungkin digunakan untuk membuat data baru, menampilkan data, menghapus data, atau memperbarui data.

Terdapat metode jLabel2MousePressed(), yang digunakan untuk kembali ke jendela login saat tombol "Back" diklik.

Terdapat metode untuk menggeser jendela saat mouse digunakan.

Kode terakhir adalah metode main(), yang menginisialisasi dan menampilkan jendela menu utama saat aplikasi dijalankan.

#### GUI Menu Makanan
      package GUI;
      
      import Database.DatabaseConnection;
      import Gudang.*;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import java.sql.*;
      import javax.swing.JOptionPane;
      
      
      public class Makanan extends javax.swing.JFrame {
          int xx, xy;
              private Statement statement;
              public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
      
          private boolean isIdDuplicate(int id, String tableName) {
          DatabaseConnection db = null; // Deklarasikan variabel di luar blok try
          try {
              db = new DatabaseConnection();
              Connection connection = db.getConnection();
              statement = connection.createStatement();
      
              String query = "SELECT * FROM " + tableName + " WHERE id_barang = " + id;
              ResultSet resultSet = statement.executeQuery(query);
      
              return resultSet.next(); // Mengembalikan true jika ID sudah ada, false jika belum ada
      
          } catch (SQLException ex) {
              ex.printStackTrace();
              // Handle exceptions
          } finally {
              if (db != null) {
                  db.disconnected();
              }
          }
      
          return false;
      }    
              
              
          public Makanan() {
              initComponents();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              txtIdBarang = new javax.swing.JTextField();
              txtTanggalKadaluarsa = new javax.swing.JTextField();
              txtJenisMakanan = new javax.swing.JTextField();
              txtDeskripsi = new javax.swing.JTextField();
              txtNamaBarang = new javax.swing.JTextField();
              txtJumlahBarang = new javax.swing.JTextField();
              btncreate = new javax.swing.JButton();
              jLabel3 = new javax.swing.JLabel();
              jLabel2 = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
              lblKaryawanID = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              txtIdBarang.setBorder(null);
              txtIdBarang.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      txtIdBarangActionPerformed(evt);
                  }
              });
              getContentPane().add(txtIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 220, 30));
      
              txtTanggalKadaluarsa.setBorder(null);
              getContentPane().add(txtTanggalKadaluarsa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 220, 30));
      
              txtJenisMakanan.setBorder(null);
              txtJenisMakanan.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      txtJenisMakananActionPerformed(evt);
                  }
              });
              getContentPane().add(txtJenisMakanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 220, 30));
      
              txtDeskripsi.setBorder(null);
              getContentPane().add(txtDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 220, 30));
      
              txtNamaBarang.setBorder(null);
              getContentPane().add(txtNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 220, 30));
      
              txtJumlahBarang.setBorder(null);
              getContentPane().add(txtJumlahBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 220, 30));
      
              btncreate.setText("Create");
              btncreate.setBorder(null);
              btncreate.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      btncreateActionPerformed(evt);
                  }
              });
              getContentPane().add(btncreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 110, 40));
      
              jLabel3.setForeground(new java.awt.Color(0, 0, 0));
              jLabel3.setText("(YYYY-MM-DD)");
              getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, -1, -1));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel2MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/8.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              lblKaryawanID.setText("jLabel3");
              getContentPane().add(lblKaryawanID, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 470, -1, -1));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void txtIdBarangActionPerformed(java.awt.event.ActionEvent evt) {                                            
              // TODO add your handling code here:
          }                                           
      
          private void txtJenisMakananActionPerformed(java.awt.event.ActionEvent evt) {                                                
              // TODO add your handling code here:
          }                                               
      
          private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {                                          
      
          String idBarangText = txtIdBarang.getText().trim();
          String namaBarang = txtNamaBarang.getText().trim();
          String deskripsi = txtDeskripsi.getText().trim();
          String jumlahBarangText = txtJumlahBarang.getText().trim();
      
          if (idBarangText.isEmpty() || namaBarang.isEmpty() || deskripsi.isEmpty() || jumlahBarangText.isEmpty()) {
              JOptionPane.showMessageDialog(null, "Semua field harus diisi.");
              return;
          }
          
              if (!idBarangText.matches("\\d+")) {
              JOptionPane.showMessageDialog(null, "ID barang harus berupa angka.");
              return;
          }
          
          
          
          int idBarang = Integer.parseInt(idBarangText);
          int jumlahBarang = Integer.parseInt(jumlahBarangText);
          
          if (idBarang < 1) {
              JOptionPane.showMessageDialog(null, "ID barang tidak boleh negatif.");
              return;
          }
      
          if (jumlahBarang < 1) {
              JOptionPane.showMessageDialog(null, "Jumlah barang tidak boleh kurang dari 1.");
              return;
          }
          
          
              String tanggal_kadaluarsa2 = txtTanggalKadaluarsa.getText().trim();
      
      
          if (!tanggal_kadaluarsa2.matches("\\d{4}-\\d{2}-\\d{2}")) {
              JOptionPane.showMessageDialog(null, "Format tanggal kadaluarsa harus YYYY-MM-DD.");
              return;
          }
          
          
      
          if (isIdDuplicate(idBarang, "barang")) {
              JOptionPane.showMessageDialog(null, "ID barang sudah digunakan. Tolong gunakan ID lain.");
              return;
          }
          
      
          BarangClass barang = new BarangClass();
          barang.setIdBarang(idBarang);
          barang.setNamaBarang(namaBarang);
          barang.setDeskripsi(deskripsi);
          barang.setJumlahBarang(jumlahBarang);
          barang.setKaryawanID(lblKaryawanID.getText());
      
      
          if (barang.createBarang()) {
              JOptionPane.showMessageDialog(null, "Data barang berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data barang.");
          }
      
      
          int idMakanan = Integer.parseInt(txtIdBarang.getText());
          String tanggal_kadaluarsa = txtTanggalKadaluarsa.getText();
          String jenis_makanan = txtJenisMakanan.getText();
      
      
          MakananClass makanan = new MakananClass();
          makanan.setId_barang(idMakanan);
          makanan.setTanggal_kadaluarsa(tanggal_kadaluarsa);
          makanan.setJenis_makanan(jenis_makanan);
      
      
          if (makanan.createMakanan()) {
              JOptionPane.showMessageDialog(null, "Data makanan berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data makanan.");
          }
          
      
          DataBarangClass dataBarang = new DataBarangClass();
          dataBarang.setIdBarang(String.valueOf(idBarang));
          dataBarang.setJumlahBarang(String.valueOf(jumlahBarang));
      
          if (dataBarang.createDataBarang()) {
              JOptionPane.showMessageDialog(null, "Data Barang Masuk berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data barang masuk.");
          }
          
          }                                         
      
          private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              SelectCategory mm = new SelectCategory();
              mm.lblKaryawanID.setText(lblKaryawanID.getText());
              mm.setVisible(true);
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(Makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(Makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(Makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(Makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new Makanan().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JButton btncreate;
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JLabel jLabel3;
          public javax.swing.JLabel lblKaryawanID;
          private javax.swing.JTextField txtDeskripsi;
          private javax.swing.JTextField txtIdBarang;
          private javax.swing.JTextField txtJenisMakanan;
          private javax.swing.JTextField txtJumlahBarang;
          private javax.swing.JTextField txtNamaBarang;
          private javax.swing.JTextField txtTanggalKadaluarsa;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) untuk memasukkan data barang makanan ke dalam database. Mari jelaskan dalam bahasa yang mudah dipahami:

Kode ini digunakan untuk membuat tampilan pengisian data barang makanan yang akan disimpan dalam database.

Terdapat metode close() yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Terdapat metode isIdDuplicate() yang digunakan untuk memeriksa apakah ID barang yang dimasukkan sudah ada di database.

Konstruktor Makanan() digunakan untuk menginisialisasi tampilan GUI ketika jendela dijalankan.

Pada metode initComponents(), komponen-komponen antarmuka pengguna seperti kotak teks, label, dan tombol "Create" diinisialisasi.

Terdapat validasi yang memeriksa apakah semua field telah diisi dengan benar sebelum data barang makanan disimpan.

Jika data memenuhi validasi, maka data barang umum (tipe BarangClass) dan data makanan (tipe MakananClass) akan disimpan dalam database.

Terdapat juga validasi untuk memastikan bahwa ID barang tidak duplikat, dan tanggal kadaluarsa sesuai dengan format "YYYY-MM-DD".

Terdapat juga logika untuk menutup jendela dan kembali ke jendela pemilihan kategori setelah data barang makanan berhasil disimpan.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela ini saat aplikasi dijalankan.

#### GUI Menu Pakaian
      package GUI;
      
      import Database.DatabaseConnection;
      import Gudang.*;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import java.sql.*;
      import javax.swing.JOptionPane;
      
      
      public class Pakaian extends javax.swing.JFrame {
          int xx, xy;
          private Statement statement;
              public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
      
      private boolean isIdDuplicate(int id, String tableName) {
          DatabaseConnection db = null; // Deklarasikan variabel di luar blok try
          try {
              db = new DatabaseConnection();
              Connection connection = db.getConnection();
              statement = connection.createStatement();
      
              String query = "SELECT * FROM " + tableName + " WHERE id_barang = " + id;
              ResultSet resultSet = statement.executeQuery(query);
      
              return resultSet.next(); // Mengembalikan true jika ID sudah ada, false jika belum ada
      
          } catch (SQLException ex) {
              ex.printStackTrace();
              // Handle exceptions
          } finally {
              if (db != null) {
                  db.disconnected();
              }
          }
      
          return false;
      }         
              
              
              
              
              
              
      
          public Pakaian() {
              initComponents();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              txtIdBarang = new javax.swing.JTextField();
              txtUkuran = new javax.swing.JTextField();
              txtWarna = new javax.swing.JTextField();
              txtDeskripsi = new javax.swing.JTextField();
              txtNamaBarang = new javax.swing.JTextField();
              txtJumlahBarang = new javax.swing.JTextField();
              txtBahan = new javax.swing.JTextField();
              btncreate = new javax.swing.JButton();
              jLabel2 = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
              lblKaryawanID = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              txtIdBarang.setBorder(null);
              getContentPane().add(txtIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 220, 30));
      
              txtUkuran.setBorder(null);
              getContentPane().add(txtUkuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 220, 30));
      
              txtWarna.setBorder(null);
              txtWarna.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      txtWarnaActionPerformed(evt);
                  }
              });
              getContentPane().add(txtWarna, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 220, 30));
      
              txtDeskripsi.setBorder(null);
              getContentPane().add(txtDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 220, 30));
      
              txtNamaBarang.setBorder(null);
              getContentPane().add(txtNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 220, 30));
      
              txtJumlahBarang.setBorder(null);
              getContentPane().add(txtJumlahBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 220, 30));
      
              txtBahan.setBorder(null);
              txtBahan.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      txtBahanActionPerformed(evt);
                  }
              });
              getContentPane().add(txtBahan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 220, 30));
      
              btncreate.setText("Create");
              btncreate.setBorder(null);
              btncreate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
              btncreate.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      btncreateActionPerformed(evt);
                  }
              });
              getContentPane().add(btncreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 110, 40));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel2MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              lblKaryawanID.setText("jLabel3");
              getContentPane().add(lblKaryawanID, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 470, -1, -1));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void txtBahanActionPerformed(java.awt.event.ActionEvent evt) {                                         
              // TODO add your handling code here:
          }                                        
      
          private void txtWarnaActionPerformed(java.awt.event.ActionEvent evt) {                                         
              // TODO add your handling code here:
          }                                        
      
          private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {                                          
      
          String idBarangText = txtIdBarang.getText().trim();
          String namaBarang = txtNamaBarang.getText().trim();
          String deskripsi = txtDeskripsi.getText().trim();
          String jumlahBarangText = txtJumlahBarang.getText().trim();
      
          if (idBarangText.isEmpty() || namaBarang.isEmpty() || deskripsi.isEmpty() || jumlahBarangText.isEmpty()) {
              JOptionPane.showMessageDialog(null, "Semua field harus diisi.");
              return;
          }
          
              if (!idBarangText.matches("\\d+")) {
              JOptionPane.showMessageDialog(null, "ID barang harus berupa angka.");
              return;
          }
          
          
          int idBarang = Integer.parseInt(idBarangText);
          int jumlahBarang = Integer.parseInt(jumlahBarangText);    
          
      
          if (idBarang < 1) {
              JOptionPane.showMessageDialog(null, "ID barang tidak boleh negatif.");
              return;
          }
          
      
          if (jumlahBarang < 1) {
              JOptionPane.showMessageDialog(null, "Jumlah barang tidak boleh kurang dari 1.");
              return;
          }
          
      
          if (isIdDuplicate(idBarang, "barang")) {
              JOptionPane.showMessageDialog(null, "ID barang sudah digunakan. Tolong gunakan ID lain.");
              return;
          }    
          
      
          BarangClass barang = new BarangClass();
          barang.setIdBarang(idBarang);
          barang.setNamaBarang(namaBarang);
          barang.setDeskripsi(deskripsi);
          barang.setJumlahBarang(jumlahBarang);
          barang.setKaryawanID(lblKaryawanID.getText());
      
      
          if (barang.createBarang()) {
              JOptionPane.showMessageDialog(null, "Data barang berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data barang.");
          }
      
      
          int idPakaian = Integer.parseInt(txtIdBarang.getText());
          String ukuran = txtUkuran.getText();
          String warna = txtWarna.getText();
          String bahan = txtBahan.getText();
      
      
          PakaianClass pakaian = new PakaianClass();
          pakaian.setId_barang(idPakaian);
          pakaian.setUkuran(ukuran);
          pakaian.setWarna(warna);
          pakaian.setBahan(bahan);
      
      
          if (pakaian.createPakaian()) {
              JOptionPane.showMessageDialog(null, "Data pakaian berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data pakaian.");
          }
          
      
          DataBarangClass dataBarang = new DataBarangClass();
          dataBarang.setIdBarang(String.valueOf(idBarang));
          dataBarang.setJumlahBarang(String.valueOf(jumlahBarang));
      
          if (dataBarang.createDataBarang()) {
              JOptionPane.showMessageDialog(null, "Data Barang Masuk berhasil disimpan.");
          } else {
              JOptionPane.showMessageDialog(null, "Gagal menyimpan data barang masuk.");
          }
          }                                         
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              SelectCategory mm = new SelectCategory();
              mm.lblKaryawanID.setText(lblKaryawanID.getText());
              mm.setVisible(true);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(Pakaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(Pakaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(Pakaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(Pakaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new Pakaian().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JButton btncreate;
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          public javax.swing.JLabel lblKaryawanID;
          private javax.swing.JTextField txtBahan;
          private javax.swing.JTextField txtDeskripsi;
          private javax.swing.JTextField txtIdBarang;
          private javax.swing.JTextField txtJumlahBarang;
          private javax.swing.JTextField txtNamaBarang;
          private javax.swing.JTextField txtUkuran;
          private javax.swing.JTextField txtWarna;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) untuk memasukkan data pakaian ke dalam database. Mari jelaskan dalam bahasa yang mudah dipahami:

Kode ini digunakan untuk membuat tampilan pengisian data pakaian yang akan disimpan dalam database.

Terdapat metode close() yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Terdapat metode isIdDuplicate() yang digunakan untuk memeriksa apakah ID barang yang dimasukkan sudah ada di database, mirip dengan kode sebelumnya.

Konstruktor Pakaian() digunakan untuk menginisialisasi tampilan GUI ketika jendela dijalankan.

Pada metode initComponents(), komponen-komponen antarmuka pengguna seperti kotak teks, label, dan tombol "Create" diinisialisasi.

Terdapat validasi yang memeriksa apakah semua field telah diisi dengan benar sebelum data pakaian disimpan.

Jika data memenuhi validasi, maka data barang umum (tipe BarangClass) dan data pakaian (tipe PakaianClass) akan disimpan dalam database.

Terdapat juga validasi untuk memastikan bahwa ID barang tidak duplikat.

Terdapat juga logika untuk menutup jendela dan kembali ke jendela pemilihan kategori setelah data pakaian berhasil disimpan.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela ini saat aplikasi dijalankan.

#### GUI Select Category
      package GUI;
      
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import javax.swing.JOptionPane;
      /**
       *
       * @author ASUS
       */
      public class SelectCategory extends javax.swing.JFrame {
          int xx, xy;
          public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
          
          public SelectCategory() {
              initComponents();
          }
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              elektronik = new javax.swing.JLabel();
              pakaian = new javax.swing.JLabel();
              makanan = new javax.swing.JLabel();
              back = new javax.swing.JLabel();
              jLabel2 = new javax.swing.JLabel();
              lblKaryawanID = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              elektronik.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      elektronikMouseClicked(evt);
                  }
              });
              getContentPane().add(elektronik, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 180, 130));
      
              pakaian.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      pakaianMouseClicked(evt);
                  }
              });
              getContentPane().add(pakaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 180, 130));
      
              makanan.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      makananMouseClicked(evt);
                  }
              });
              getContentPane().add(makanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 180, 130));
      
              back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              back.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      backMouseClicked(evt);
                  }
              });
              getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 50, 50));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select.png"))); // NOI18N
              jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel2MouseDragged(evt);
                  }
              });
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel2MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              lblKaryawanID.setText("jLabel1");
              getContentPane().add(lblKaryawanID, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 480, -1, -1));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void elektronikMouseClicked(java.awt.event.MouseEvent evt) {                                        
              close();
              Elektronik el = new Elektronik();
              el.lblKaryawanID.setText(lblKaryawanID.getText());
              el.setVisible(true);
          }                                       
      
          private void pakaianMouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              Pakaian pa = new Pakaian();
              pa.lblKaryawanID.setText(lblKaryawanID.getText());
              pa.setVisible(true);
          }                                    
      
          private void makananMouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              Makanan ma = new Makanan();
              ma.lblKaryawanID.setText(lblKaryawanID.getText());
              ma.setVisible(true);
      
          }                                    
      
          private void backMouseClicked(java.awt.event.MouseEvent evt) {                                  
              close();
              MainMenu mm = new MainMenu();
              mm.lblKaryawanID.setText(lblKaryawanID.getText());
              mm.setVisible(true);
          }                                 
      
          private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(SelectCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(SelectCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(SelectCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(SelectCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new SelectCategory().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JLabel back;
          private javax.swing.JLabel elektronik;
          private javax.swing.JLabel jLabel2;
          public javax.swing.JLabel lblKaryawanID;
          private javax.swing.JLabel makanan;
          private javax.swing.JLabel pakaian;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) untuk memilih kategori barang, seperti elektronik, pakaian, atau makanan. Mari jelaskan dalam bahasa yang mudah dipahami:

Kode ini digunakan untuk membuat tampilan pemilihan kategori barang sebelum memasukkan data ke dalam database.

Terdapat metode close() yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Konstruktor SelectCategory() digunakan untuk menginisialisasi tampilan GUI ketika jendela dijalankan.

Pada metode initComponents(), gambar-gambar yang mewakili setiap kategori (elektronik, pakaian, makanan) serta tombol "Back" diinisialisasi.

Saat pengguna mengklik salah satu gambar kategori, jendela saat ini akan ditutup, dan jendela yang sesuai dengan kategori yang dipilih akan ditampilkan.

Terdapat juga fungsi untuk menampilkan jendela utama ("Back") jika pengguna ingin kembali ke menu utama.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela ini saat aplikasi dijalankan.

#### GUI Show Data Barang Masuk
      package GUI;
      
      import Database.DatabaseConnection;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import java.sql.*;
      import javax.swing.table.DefaultTableModel;
      
      public class ShowDBM extends javax.swing.JFrame {
          int xx, xy;
              public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
          private DatabaseConnection db;
          
          
       public void displayDBM() {
          
          DefaultTableModel model = (DefaultTableModel) tbldbm.getModel();
          model.setRowCount(0); // Mengosongkan tabel
          model.setColumnIdentifiers(new Object[]{"ID Data ", "Tanggal Pendataan", "Jumlah Barang Masuk", "ID Barang"});
      
          try {
              db.getConnection(); // Buka koneksi database
      
              // Gunakan objek statement yang sudah ada
              db.statement = db.connection.createStatement();
      
              String query = "SELECT * FROM data_barang_masuk";
              ResultSet resultSet = db.statement.executeQuery(query);
      
              while (resultSet.next()) {
                  int id = resultSet.getInt("id_data");
                  String modelName = resultSet.getString("tanggal_pendataan");
                  String merk = resultSet.getString("jumlah_barang_masuk");
                  String merkk = resultSet.getString("barang_id_barang");
      
                  model.addRow(new Object[]{id, modelName, merk, merkk});
              }
      
              resultSet.close();
          } catch (SQLException ex) {
              ex.printStackTrace();
              // Handle exceptions
          } finally {
              db.disconnected(); // Tutup koneksi setelah selesai
          }
      }   
          
          
          
          
          public ShowDBM() {
              initComponents();
              db = new DatabaseConnection(); // Inisialisasi objek Database
              db.getConnection(); // Buka koneksi database
              displayDBM();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              jScrollPane1 = new javax.swing.JScrollPane();
              tbldbm = new javax.swing.JTable();
              jLabel2 = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              tbldbm.setModel(new javax.swing.table.DefaultTableModel(
                  new Object [][] {
                      {null, null, null, null},
                      {null, null, null, null},
                      {null, null, null, null},
                      {null, null, null, null}
                  },
                  new String [] {
                      "Title 1", "Title 2", "Title 3", "Title 4"
                  }
              ) {
                  boolean[] canEdit = new boolean [] {
                      false, false, false, false
                  };
      
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                      return canEdit [columnIndex];
                  }
              });
              jScrollPane1.setViewportView(tbldbm);
      
              getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 740, 290));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel2MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/showDBM.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              ShowMenu mm = new ShowMenu();
              mm.setVisible(true);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(ShowDBM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(ShowDBM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(ShowDBM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(ShowDBM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new ShowDBM().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JScrollPane jScrollPane1;
          private javax.swing.JTable tbldbm;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) yang digunakan untuk menampilkan data dari tabel "data_barang_masuk" dari database. Mari jelaskan dalam bahasa yang mudah dipahami:

Kode ini digunakan untuk membuat tampilan yang menampilkan data barang masuk dari tabel "data_barang_masuk" dalam database.

Terdapat metode close() yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Terdapat objek DatabaseConnection (db) yang digunakan untuk mengelola koneksi database.

Terdapat metode displayDBM() yang digunakan untuk mengambil data dari database dan menampilkannya dalam tabel pada antarmuka pengguna.

Di dalam displayDBM(), data dari tabel "data_barang_masuk" diambil dari database dan dimasukkan ke dalam tabel pada antarmuka pengguna.

Konstruktor ShowDBM() digunakan untuk menginisialisasi tampilan GUI saat jendela dijalankan. Saat jendela dibuka, data barang masuk akan ditampilkan di tabel.

Pada metode initComponents(), tabel untuk menampilkan data disiapkan, serta tombol untuk kembali ke menu sebelumnya.

Terdapat juga fungsi untuk menampilkan jendela menu sebelumnya jika pengguna ingin kembali.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela ini saat aplikasi dijalankan.

#### GUI Show Elekronik
      package GUI;
      
      import Database.DatabaseConnection;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import java.sql.*;
      import javax.swing.table.DefaultTableModel;
      
      public class ShowElektronik extends javax.swing.JFrame {
          int xx, xy;
              public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
          private DatabaseConnection db;
      
      public void displayElektronik() {
          
          DefaultTableModel model = (DefaultTableModel) tblElektronik.getModel();
          model.setRowCount(0); // Mengosongkan tabel
          model.setColumnIdentifiers(new Object[]{"ID Barang ", "Model", "Merk"});
      
          try {
              db.getConnection(); // Buka koneksi database
      
              // Gunakan objek statement yang sudah ada
              db.statement = db.connection.createStatement();
      
              String query = "SELECT * FROM elektronik";
              ResultSet resultSet = db.statement.executeQuery(query);
      
              while (resultSet.next()) {
                  int id = resultSet.getInt("id_barang");
                  String modelName = resultSet.getString("model");
                  String merk = resultSet.getString("merk");
      
                  model.addRow(new Object[]{id, modelName, merk});
              }
      
              resultSet.close();
          } catch (SQLException ex) {
              ex.printStackTrace();
              // Handle exceptions
          } finally {
              db.disconnected(); // Tutup koneksi setelah selesai
          }
      }
          public ShowElektronik() {
              initComponents();
              db = new DatabaseConnection(); // Inisialisasi objek Database
              db.getConnection(); // Buka koneksi database
              displayElektronik();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              jScrollPane1 = new javax.swing.JScrollPane();
              tblElektronik = new javax.swing.JTable();
              jLabel2 = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              tblElektronik.setModel(new javax.swing.table.DefaultTableModel(
                  new Object [][] {
                      {null, null, null, null},
                      {null, null, null, null},
                      {null, null, null, null},
                      {null, null, null, null}
                  },
                  new String [] {
                      "Title 1", "Title 2", "Title 3", "Title 4"
                  }
              ) {
                  boolean[] canEdit = new boolean [] {
                      false, false, false, false
                  };
      
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                      return canEdit [columnIndex];
                  }
              });
              jScrollPane1.setViewportView(tblElektronik);
      
              getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 740, 290));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel2MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shwE.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              ShowMenu mm = new ShowMenu();
              mm.setVisible(true);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(ShowElektronik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(ShowElektronik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(ShowElektronik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(ShowElektronik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new ShowElektronik().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JScrollPane jScrollPane1;
          private javax.swing.JTable tblElektronik;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) yang digunakan untuk menampilkan data barang elektronik dari database dalam bentuk tabel. Mari jelaskan dalam bahasa yang mudah dipahami:

Kode ini digunakan untuk membuat tampilan yang menampilkan data barang elektronik dari tabel "elektronik" dalam database.

Terdapat metode close() yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Terdapat objek DatabaseConnection (db) yang digunakan untuk mengelola koneksi database.

Terdapat metode displayElektronik() yang digunakan untuk mengambil data dari database dan menampilkannya dalam tabel pada antarmuka pengguna.

Di dalam displayElektronik(), data dari tabel "elektronik" diambil dari database dan dimasukkan ke dalam tabel pada antarmuka pengguna.

Konstruktor ShowElektronik() digunakan untuk menginisialisasi tampilan GUI saat jendela dijalankan. Saat jendela dibuka, data barang elektronik akan ditampilkan di tabel.

Pada metode initComponents(), tabel untuk menampilkan data disiapkan, serta terdapat gambar panah yang memungkinkan pengguna untuk kembali ke menu sebelumnya.

Terdapat juga fungsi untuk menampilkan jendela menu sebelumnya jika pengguna ingin kembali.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela ini saat aplikasi dijalankan.

#### GUI Show Makanan
      package GUI;
      
      import Database.DatabaseConnection;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import java.sql.*;
      import javax.swing.table.DefaultTableModel;
      
      public class ShowMakanan extends javax.swing.JFrame {
          int xx, xy;
              public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
      
          private DatabaseConnection db;
          
      public void displayMakanan() {
          
          DefaultTableModel model = (DefaultTableModel) tblMakanan.getModel();
          model.setRowCount(0); // Mengosongkan tabel
          model.setColumnIdentifiers(new Object[]{"ID Barang ", "Tanggal Kadaluarsa", "Jenis Makanan"});
      
          try {
              db.getConnection(); // Buka koneksi database
      
              // Gunakan objek statement yang sudah ada
              db.statement = db.connection.createStatement();
      
              String query = "SELECT * FROM makanan";
              ResultSet resultSet = db.statement.executeQuery(query);
      
              while (resultSet.next()) {
                  int id = resultSet.getInt("id_barang");
                  String tgl_kadaluarsa = resultSet.getString("tanggal_kadaluarsa");
                  String jenis_makanan = resultSet.getString("jenis_makanan");
      
                  model.addRow(new Object[]{id, tgl_kadaluarsa, jenis_makanan});
              }
      
              resultSet.close();
          } catch (SQLException ex) {
              ex.printStackTrace();
              // Handle exceptions
          } finally {
              db.disconnected(); // Tutup koneksi setelah selesai
          }
      }
          public ShowMakanan() {
              initComponents();
              db = new DatabaseConnection(); // Inisialisasi objek Database
              db.getConnection(); // Buka koneksi database
              displayMakanan();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              jScrollPane1 = new javax.swing.JScrollPane();
              tblMakanan = new javax.swing.JTable();
              jLabel2 = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              tblMakanan.setModel(new javax.swing.table.DefaultTableModel(
                  new Object [][] {
                      {null, null, null, null},
                      {null, null, null, null},
                      {null, null, null, null},
                      {null, null, null, null}
                  },
                  new String [] {
                      "Title 1", "Title 2", "Title 3", "Title 4"
                  }
              ) {
                  boolean[] canEdit = new boolean [] {
                      false, false, false, false
                  };
      
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                      return canEdit [columnIndex];
                  }
              });
              jScrollPane1.setViewportView(tblMakanan);
      
              getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 740, 290));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel2MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shwM.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              ShowMenu mm = new ShowMenu();
              mm.setVisible(true);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(ShowMakanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(ShowMakanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(ShowMakanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(ShowMakanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new ShowMakanan().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JScrollPane jScrollPane1;
          private javax.swing.JTable tblMakanan;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) yang digunakan untuk menampilkan data barang makanan dari database dalam bentuk tabel. Mari jelaskan dalam bahasa yang mudah dipahami:

Kode ini digunakan untuk membuat tampilan yang menampilkan data barang makanan dari tabel "makanan" dalam database.

Terdapat metode close() yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Terdapat objek DatabaseConnection (db) yang digunakan untuk mengelola koneksi database.

Terdapat metode displayMakanan() yang digunakan untuk mengambil data dari database dan menampilkannya dalam tabel pada antarmuka pengguna.

Di dalam displayMakanan(), data dari tabel "makanan" diambil dari database dan dimasukkan ke dalam tabel pada antarmuka pengguna.

Konstruktor ShowMakanan() digunakan untuk menginisialisasi tampilan GUI saat jendela dijalankan. Saat jendela dibuka, data barang makanan akan ditampilkan di tabel.

Pada metode initComponents(), tabel untuk menampilkan data disiapkan, serta terdapat gambar panah yang memungkinkan pengguna untuk kembali ke menu sebelumnya.

Terdapat juga fungsi untuk menampilkan jendela menu sebelumnya jika pengguna ingin kembali.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela ini saat aplikasi dijalankan.

#### GUI Pilih Show Menu
      package GUI;
      
      import Database.DatabaseConnection;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import java.sql.*;
      import javax.swing.table.DefaultTableModel;
      
      
      
      public class ShowMenu extends javax.swing.JFrame {
          int xx, xy;
          public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }

      public ShowMenu() {
              initComponents();
      
      @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              ele = new javax.swing.JLabel();
              pka = new javax.swing.JLabel();
              mkn = new javax.swing.JLabel();
              jLabel5 = new javax.swing.JLabel();
              masok = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              ele.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      eleMouseClicked(evt);
                  }
              });
              getContentPane().add(ele, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 190, 130));
      
              pka.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      pkaMouseClicked(evt);
                  }
              });
              getContentPane().add(pka, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 190, 130));
      
              mkn.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      mknMouseClicked(evt);
                  }
              });
              getContentPane().add(mkn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 190, 130));
      
              jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel5MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 50, 50));
      
              masok.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      masokMouseClicked(evt);
                  }
              });
              getContentPane().add(masok, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 190, 120));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/showcate.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void eleMouseClicked(java.awt.event.MouseEvent evt) {                                 
              close();
              ShowElektronik el = new ShowElektronik();
              el.setVisible(true);
          }                                
      
          private void pkaMouseClicked(java.awt.event.MouseEvent evt) {                                 
              close();
              ShowPakaian el = new ShowPakaian();
              el.setVisible(true);
          }                                
      
          private void mknMouseClicked(java.awt.event.MouseEvent evt) {                                 
              close();
              ShowMakanan el = new ShowMakanan();
              el.setVisible(true);
          }                                
      
          private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              MainMenu mm = new MainMenu();
              mm.setVisible(true);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          private void masokMouseClicked(java.awt.event.MouseEvent evt) {                                   
              close();
              ShowDBM mm = new ShowDBM();
              mm.setVisible(true);
          }                                  
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(ShowMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(ShowMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(ShowMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(ShowMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new ShowMenu().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JLabel ele;
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel5;
          private javax.swing.JLabel masok;
          private javax.swing.JLabel mkn;
          private javax.swing.JLabel pka;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari antarmuka pengguna (GUI) yang berfungsi sebagai menu utama aplikasi. Mari jelaskan dalam bahasa yang mudah dipahami:

Kode ini membuat jendela menu utama (ShowMenu) yang digunakan untuk memilih kategori barang yang akan ditampilkan.

Terdapat metode close() yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Terdapat empat kategori barang yang dapat dipilih: "ele" (elektronik), "pka" (pakaian), "mkn" (makanan), dan "masok" (barang masuk).

Pada metode initComponents(), gambar dan area yang dapat diklik untuk memilih kategori ditambahkan ke jendela. Gambar panah kecil digunakan untuk kembali ke menu utama.

Terdapat metode-metode seperti eleMouseClicked, pkaMouseClicked, mknMouseClicked, dan masokMouseClicked yang dijalankan ketika pengguna mengklik salah satu kategori. Metode ini akan menutup jendela menu utama dan membuka jendela yang sesuai dengan kategori yang dipilih.

Terdapat juga fungsi untuk menampilkan jendela menu utama jika pengguna ingin kembali.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela menu utama saat aplikasi dijalankan.

#### GUI Show Makanan
      package GUI;
      
      import Database.DatabaseConnection;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import java.sql.*;
      import javax.swing.table.DefaultTableModel;
      
      public class ShowPakaian extends javax.swing.JFrame {
          int xx, xy;
              public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
          
          
          
          
          private DatabaseConnection db;
      public void displayPakaian() {
          
          DefaultTableModel model = (DefaultTableModel) tblPakaian.getModel();
          model.setRowCount(0); // Mengosongkan tabel
          model.setColumnIdentifiers(new Object[]{"ID Barang ", "Ukuran", "Warna", "Bahan"});
      
          try {
              db.getConnection(); // Buka koneksi database
      
              // Gunakan objek statement yang sudah ada
              db.statement = db.connection.createStatement();
      
              String query = "SELECT * FROM pakaian";
              ResultSet resultSet = db.statement.executeQuery(query);
      
              while (resultSet.next()) {
                  int id = resultSet.getInt("id_barang");
                  String ukuran = resultSet.getString("ukuran");
                  String warna = resultSet.getString("warna");
                  String bahan = resultSet.getString("bahan");
      
                  model.addRow(new Object[]{id, ukuran, warna, bahan});
              }
      
              resultSet.close();
          } catch (SQLException ex) {
              ex.printStackTrace();
              // Handle exceptions
          } finally {
              db.disconnected(); // Tutup koneksi setelah selesai
          }
      }
          
          public ShowPakaian() {
              initComponents();
              db = new DatabaseConnection(); // Inisialisasi objek Database
              db.getConnection(); // Buka koneksi database
              displayPakaian();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              jScrollPane1 = new javax.swing.JScrollPane();
              tblPakaian = new javax.swing.JTable();
              jLabel2 = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              tblPakaian.setModel(new javax.swing.table.DefaultTableModel(
                  new Object [][] {
                      {null, null, null, null},
                      {null, null, null, null},
                      {null, null, null, null},
                      {null, null, null, null}
                  },
                  new String [] {
                      "Title 1", "Title 2", "Title 3", "Title 4"
                  }
              ) {
                  boolean[] canEdit = new boolean [] {
                      false, false, false, false
                  };
      
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                      return canEdit [columnIndex];
                  }
              });
              jScrollPane1.setViewportView(tblPakaian);
      
              getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 740, 290));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel2MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shwP.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              ShowMenu mm = new ShowMenu();
              mm.setVisible(true);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              // TODO add your handling code here:
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(ShowPakaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(ShowPakaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(ShowPakaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(ShowPakaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new ShowPakaian().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JScrollPane jScrollPane1;
          private javax.swing.JTable tblPakaian;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari aplikasi Java yang merupakan antarmuka pengguna (GUI) untuk menampilkan daftar barang kategori "Pakaian" dari database. Mari kita jelaskan dalam bahasa yang mudah dipahami:

Kode ini membuat sebuah jendela (class ShowPakaian) yang digunakan untuk menampilkan daftar barang kategori "Pakaian".

Terdapat metode close(), yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Ada penggunaan koneksi database melalui objek DatabaseConnection (class yang mengelola koneksi database).

Terdapat metode displayPakaian() yang digunakan untuk mengambil data dari tabel "pakaian" dalam database dan menampilkannya dalam sebuah tabel di jendela.

Dalam metode initComponents(), elemen-elemen tampilan seperti tabel dan gambar panah untuk kembali ke menu utama ditambahkan ke jendela.

Terdapat juga metode-metode seperti jLabel2MouseClicked, jLabel1MousePressed, dan jLabel1MouseDragged yang digunakan untuk mengatur tindakan saat pengguna berinteraksi dengan elemen-elemen jendela.

Metode main() hanya digunakan untuk menginisialisasi dan menampilkan jendela ketika aplikasi dijalankan.

#### GUI Sign Up
      package GUI;
      
      import java.sql.*;
      import Database.DatabaseConnection;
      import Gudang.*;
      import GUI.*;
      import javax.swing.JOptionPane;
      import java.awt.*;
      import java.awt.event.*;
      
      public class SignUp extends javax.swing.JFrame {
          int xx, xy;
          
          public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
       
          private boolean isNonNegativeNumber(String input) {
              try {
                  long idKaryawan = Long.parseLong(input);
                  return idKaryawan >= 0;
              } catch (NumberFormatException ex) {
                  return false;
              }
          }    
          
          public SignUp() {
              initComponents();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              btnRegister = new javax.swing.JButton();
              jLabel2 = new javax.swing.JLabel();
              usernamebox = new javax.swing.JTextField();
              passwordbox = new javax.swing.JPasswordField();
              jLabel3 = new javax.swing.JLabel();
              jButton1 = new javax.swing.JButton();
              idkaryawan = new javax.swing.JTextField();
              jLabel1 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
      
              btnRegister.setText("Create Account");
              btnRegister.setBorder(null);
              btnRegister.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      btnRegisterActionPerformed(evt);
                  }
              });
              getContentPane().add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, 140, 60));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel2MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 50, 40));
      
              usernamebox.setBorder(null);
              usernamebox.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      usernameboxActionPerformed(evt);
                  }
              });
              getContentPane().add(usernamebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 220, 30));
      
              passwordbox.setBorder(null);
              passwordbox.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      passwordboxActionPerformed(evt);
                  }
              });
              getContentPane().add(passwordbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 220, 30));
      
              jLabel3.setForeground(new java.awt.Color(255, 255, 255));
              jLabel3.setText("Already have an account");
              getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 130, 20));
      
              jButton1.setText("Back");
              jButton1.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      jButton1ActionPerformed(evt);
                  }
              });
              getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, -1, -1));
      
              idkaryawan.setBorder(null);
              getContentPane().add(idkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 220, 30));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/signupbaru.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void usernameboxActionPerformed(java.awt.event.ActionEvent evt) {                                            
              // TODO add your handling code here:
          }                                           
      
          private void passwordboxActionPerformed(java.awt.event.ActionEvent evt) {                                            
              // TODO add your handling code here:
          }                                           
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {                                     
      //        System.exit(0);
          int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar aplikasi?", ":(", JOptionPane.YES_NO_OPTION);
          
          if (response == JOptionPane.YES_OPTION) {
              // Jika pengguna memilih "Ya", maka keluar dari aplikasi
              System.exit(0);
          } else if (response == JOptionPane.NO_OPTION) {
              // Jika pengguna memilih "Tidak", tidak perlu melakukan apa-apa
          }
          }                                    
      
          
          private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {                                            
          String idKaryawanText = idkaryawan.getText();
      
          if (idKaryawanText.isEmpty() || idKaryawanText.length() > 10 || !isNonNegativeNumber(idKaryawanText)) {
              JOptionPane.showMessageDialog(null, "ID Karyawan harus berupa angka positif dengan maksimal 10 digit.");
          } else {
              String username = usernamebox.getText();
              String password = new String(passwordbox.getPassword());
      
              // Validasi input untuk username dan password
              if (username.isEmpty() || password.isEmpty()) {
                  JOptionPane.showMessageDialog(null, "Username dan Password tidak boleh kosong.");
              } else {
                  DatabaseConnection db = new DatabaseConnection();
                  UserDao userDao = new UserDao(db.getConnection());
      
                  if (userDao.isUsernameExists(idKaryawanText, username)) {
                      JOptionPane.showMessageDialog(null, "Username atau ID Karyawan sudah digunakan. Silahkan pilih username atau ID Karyawan lain.");
                  } else {
                      if (userDao.registerUser(idKaryawanText, username, password)) {
                          System.out.println("Pendaftaran Berhasil");
                          this.dispose();
                          Login loginn = new Login();
                          JOptionPane.showMessageDialog(null, "Berhasil membuat akun baru. Silahkan login ulang.");
                          loginn.setVisible(true);
                      } else {
                          System.out.println("Pendaftaran gagal.");
                      }
                  }
      
                  db.disconnected();
              }
          }
        
          }                                           
      
          private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
              close();
              Login log = new Login();
              log.setVisible(true);
          }                                        
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  
                  public void run() {
                        new SignUp().setVisible(true);
                  }
              });
          }
          
          
      
          // Variables declaration - do not modify                     
          private javax.swing.JButton btnRegister;
          private javax.swing.JTextField idkaryawan;
          private javax.swing.JButton jButton1;
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JLabel jLabel3;
          private javax.swing.JPasswordField passwordbox;
          private javax.swing.JTextField usernamebox;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari aplikasi Java yang digunakan untuk membuat jendela pendaftaran (sign-up) pengguna. Mari kita jelaskan dalam bahasa yang mudah dipahami:

Kode ini menciptakan jendela pendaftaran (class SignUp) yang digunakan untuk membuat akun pengguna baru.

Terdapat metode close(), yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Terdapat metode isNonNegativeNumber(String input) yang digunakan untuk memeriksa apakah input adalah angka positif yang tidak melebihi 10 digit.

Dalam konstruktor SignUp(), elemen-elemen tampilan seperti tombol pendaftaran, kotak teks username, kotak sandi, dan lainnya ditambahkan ke jendela.

Terdapat metode btnRegisterActionPerformed, yang akan dipanggil saat tombol pendaftaran ditekan. Metode ini memvalidasi input ID karyawan, username, dan password, kemudian mencoba mendaftarkan pengguna baru ke database. Jika pendaftaran berhasil, maka jendela akan ditutup dan pengguna akan diminta untuk login kembali.

Terdapat metode jButton1ActionPerformed, yang akan dipanggil saat tombol "Back" (kembali) ditekan. Metode ini digunakan untuk menutup jendela pendaftaran dan membuka jendela login.

Terdapat juga metode-metode lain seperti jLabel1MouseDragged, jLabel1MousePressed, dan jLabel2MousePressed yang digunakan untuk mengatur tindakan saat pengguna berinteraksi dengan elemen-elemen jendela.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela pendaftaran saat aplikasi dijalankan.

#### GUI Update Menu
      package GUI;
      
      import Gudang.*;
      import java.awt.Toolkit;
      import java.awt.event.WindowEvent;
      import javax.swing.JOptionPane;
      /**
       *
       * @author Lenovo
       */
      public class UpdateMenu extends javax.swing.JFrame {
          int xx, xy;
              public void close() {
              WindowEvent closewindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
              Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closewindow);
          }
          /**
           * Creates new form UpdateMenu
           */
          public UpdateMenu() {
              initComponents();
          }
      
          /**
           * This method is called from within the constructor to initialize the form.
           * WARNING: Do NOT modify this code. The content of this method is always
           * regenerated by the Form Editor.
           */
          @SuppressWarnings("unchecked")
          // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
          private void initComponents() {
      
              idbarangbox = new javax.swing.JTextField();
              jumlahbarangbox = new javax.swing.JTextField();
              updatebarangbox = new javax.swing.JTextField();
              updateButton = new javax.swing.JButton();
              jLabel2 = new javax.swing.JLabel();
              jLabel1 = new javax.swing.JLabel();
      
              setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
              setUndecorated(true);
              getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
              getContentPane().add(idbarangbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 220, -1));
      
              jumlahbarangbox.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      jumlahbarangboxActionPerformed(evt);
                  }
              });
              getContentPane().add(jumlahbarangbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 220, -1));
              getContentPane().add(updatebarangbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 230, -1));
      
              updateButton.setText("Update");
              updateButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      updateButtonActionPerformed(evt);
                  }
              });
              getContentPane().add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 120, 50));
      
              jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrowww.png"))); // NOI18N
              jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mouseClicked(java.awt.event.MouseEvent evt) {
                      jLabel2MouseClicked(evt);
                  }
              });
              getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 0, 50, 50));
      
              jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
              jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                  public void mouseDragged(java.awt.event.MouseEvent evt) {
                      jLabel1MouseDragged(evt);
                  }
              });
              jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                  public void mousePressed(java.awt.event.MouseEvent evt) {
                      jLabel1MousePressed(evt);
                  }
              });
              getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
      
              pack();
              setLocationRelativeTo(null);
          }// </editor-fold>                        
      
          private void jumlahbarangboxActionPerformed(java.awt.event.ActionEvent evt) {                                                
              // TODO add your handling code here:
          }                                               
      
          private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
              String idBarangText = idbarangbox.getText().trim();
          
          // Check if ID is empty
          if (idBarangText.isEmpty()) {
              JOptionPane.showMessageDialog(this, "ID Barang tidak boleh kosong.");
              return;
          }
      
          // Check if the ID contains only digits
          if (!idBarangText.matches("\\d+")) {
              JOptionPane.showMessageDialog(this, "ID barang harus berupa angka.");
              return;
          }
          
          int id_barang = Integer.parseInt(idBarangText);
      
          // Check if the ID is positive
          if (id_barang <= 0) {
              JOptionPane.showMessageDialog(this, "ID barang harus merupakan angka positif.");
              return;
          }
      
          try {
              int jumlah_barang = Integer.parseInt(jumlahbarangbox.getText());
              String deskripsi = updatebarangbox.getText();
      
              // Buat objek BarangClass
              BarangClass barang = new BarangClass();
              barang.setIdBarang(id_barang); // Anda perlu membuat setter untuk id_barang, deskripsi, dan jumlah_barang di kelas BarangClass
              barang.setDeskripsi(deskripsi);
              barang.setJumlahBarang(jumlah_barang);
      
              // Panggil metode updateBarang
              boolean result = barang.updateBarang();
      
              if (result) {
                  JOptionPane.showMessageDialog(this, "Barang berhasil diperbarui!");
              } else {
                  JOptionPane.showMessageDialog(this, "Gagal memperbarui barang.");
              }
          } catch (NumberFormatException ex) {
              JOptionPane.showMessageDialog(this, "Harap masukkan jumlah barang yang valid.");
          }
        
          }                                            
      
          private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
              close();
              MainMenu mm = new MainMenu();
              mm.setVisible(true);
          }                                    
      
          private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
              xx = evt.getX();
              xy = evt.getY();
          }                                    
      
          private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     
              int x = evt.getXOnScreen();
              int y = evt.getYOnScreen();
              this.setLocation(x - xx, y - xy);
          }                                    
      
          /**
           * @param args the command line arguments
           */
          public static void main(String args[]) {
              /* Set the Nimbus look and feel */
              //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
              /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
               * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
               */
              try {
                  for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                      if ("Nimbus".equals(info.getName())) {
                          javax.swing.UIManager.setLookAndFeel(info.getClassName());
                          break;
                      }
                  }
              } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(UpdateMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(UpdateMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(UpdateMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(UpdateMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }
              //</editor-fold>
      
              /* Create and display the form */
              java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                      new UpdateMenu().setVisible(true);
                  }
              });
          }
      
          // Variables declaration - do not modify                     
          private javax.swing.JTextField idbarangbox;
          private javax.swing.JLabel jLabel1;
          private javax.swing.JLabel jLabel2;
          private javax.swing.JTextField jumlahbarangbox;
          private javax.swing.JButton updateButton;
          private javax.swing.JTextField updatebarangbox;
          // End of variables declaration                   
      }

Kode ini adalah bagian dari aplikasi Java yang digunakan untuk memperbarui data barang di suatu gudang. Mari kita jelaskan dalam bahasa yang mudah dipahami:

Kode ini menciptakan jendela (class UpdateMenu) yang digunakan untuk memperbarui data barang di gudang.

Terdapat metode close(), yang digunakan untuk menutup jendela dengan mengirimkan peristiwa penutupan jendela.

Dalam konstruktor UpdateMenu(), elemen-elemen tampilan seperti kotak teks ID barang, jumlah barang yang baru, deskripsi barang yang baru, dan tombol "Update" ditambahkan ke jendela.

Terdapat metode updateButtonActionPerformed, yang akan dipanggil saat tombol "Update" ditekan. Metode ini memeriksa apakah ID barang yang dimasukkan adalah angka positif, kemudian mencoba untuk memperbarui data barang dengan ID tersebut dengan jumlah dan deskripsi yang baru. Hasilnya akan ditampilkan sebagai pesan dialog.

Terdapat juga metode-metode lain seperti jLabel1MouseDragged, jLabel1MousePressed, dan jLabel2MouseClicked yang digunakan untuk mengatur tindakan saat pengguna berinteraksi dengan elemen-elemen jendela.

Metode main() digunakan untuk menginisialisasi dan menampilkan jendela UpdateMenu saat aplikasi dijalankan.







