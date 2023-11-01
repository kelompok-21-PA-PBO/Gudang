/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Database.DatabaseConnection;
import Gudang.*;
import GUI.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

public class UpdateMenu extends javax.swing.JFrame {

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

        TfIdBarang = new javax.swing.JTextField();
        TfDeskripsi = new javax.swing.JTextField();
        TfJumlah = new javax.swing.JTextField();
        BtnUpdate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(TfIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 220, -1));
        getContentPane().add(TfDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 230, 30));
        getContentPane().add(TfJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 220, 30));

        BtnUpdate.setText("Update");
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(BtnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 120, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\Documents\\Praktikum PBO 2023\\menuUpdate.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>                        

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String idBarang = TfIdBarang.getText();
        String deskripsi = TfDeskripsi.getText();
        String jumlah = TfJumlah.getText(); // Anda bisa melakukan validasi untuk memastikan ini adalah angka

        if (idBarang.isEmpty() || deskripsi.isEmpty() || jumlah.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Barang, Deskripsi, dan Jumlah tidak boleh kosong");
            return;
        }

        // Membuat objek koneksi database
        DatabaseConnection db = new DatabaseConnection();
        UserDao userDao = new UserDao(db.getConnection());

        try {
            if (userDao.updateBarang(idBarang, deskripsi, Integer.parseInt(jumlah))) {
                JOptionPane.showMessageDialog(null, "Berhasil memperbarui data");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal memperbarui data atau data tidak ditemukan");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        }

        db.disconnected();
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
    private javax.swing.JButton BtnUpdate;
    private javax.swing.JTextField TfDeskripsi;
    private javax.swing.JTextField TfIdBarang;
    private javax.swing.JTextField TfJumlah;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration                   
}