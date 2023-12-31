/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    private void usernameboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameboxActionPerformed

    private void passwordboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordboxActionPerformed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
//        System.exit(0);
    int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar aplikasi?", ":(", JOptionPane.YES_NO_OPTION);
    
    if (response == JOptionPane.YES_OPTION) {
        // Jika pengguna memilih "Ya", maka keluar dari aplikasi
        System.exit(0);
    } else if (response == JOptionPane.NO_OPTION) {
        // Jika pengguna memilih "Tidak", tidak perlu melakukan apa-apa
    }
    }//GEN-LAST:event_jLabel2MousePressed

    
    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//    String id_karyawan = idkaryawan.getText();
//    String username = usernamebox.getText();
//    String password = new String(passwordbox.getPassword());
//
//    // Validasi input
//    if (id_karyawan.isEmpty() || username.isEmpty() || password.isEmpty()) {
//        JOptionPane.showMessageDialog(null, "ID, Username, dan Password tidak boleh kosong.");
//    } else {
//        DatabaseConnection db = new DatabaseConnection();
//        UserDao userDao = new UserDao(db.getConnection());
//
//        if (userDao.isUsernameExists(id_karyawan, username)) {
//            JOptionPane.showMessageDialog(null, "Username atau id_karyawan sudah digunakan. \nSilahkan pilih username atau id_karyawan lain.");
//        } else {
//            if (userDao.registerUser(id_karyawan, username, password)) {
//                System.out.println("Pendaftaran Berhasil");
//                this.dispose();
//                Login loginn = new Login();
//                JOptionPane.showMessageDialog(null, "Berhasil membuat akun baru, \nSilahkan login ulang.");
//                loginn.setVisible(true);
//            } else {
//                System.out.println("Pendaftaran gagal.");
//            }
//        }
//
//        db.disconnected();
//    }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        close();
        Login log = new Login();
        log.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JTextField idkaryawan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField passwordbox;
    private javax.swing.JTextField usernamebox;
    // End of variables declaration//GEN-END:variables
}
