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












