/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        getContentPane().add(updatebarangbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 220, -1));

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
    }// </editor-fold>//GEN-END:initComponents

    private void jumlahbarangboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahbarangboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahbarangboxActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
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
        
        
        
        
        
        
        
        
        
        
        
//    try {
//        // Dapatkan input dari JTextField
//        int id_barang = Integer.parseInt(idbarangbox.getText());
//        int jumlah_barang = Integer.parseInt(jumlahbarangbox.getText());
//        String deskripsi = updatebarangbox.getText();
//
//        // Buat objek BarangClass
//        BarangClass barang = new BarangClass();
//        barang.setIdBarang(id_barang); // Anda perlu membuat setter untuk id_barang, deskripsi, dan jumlah_barang di kelas BarangClass
//        barang.setDeskripsi(deskripsi);
//        barang.setJumlahBarang(jumlah_barang);
//
//        // Panggil metode updateBarang
//        boolean result = barang.updateBarang();
//
//        if (result) {
//            JOptionPane.showMessageDialog(this, "Barang berhasil diperbarui!");
//        } else {
//            JOptionPane.showMessageDialog(this, "Gagal memperbarui barang.");
//        }
//    } catch (NumberFormatException ex) {
//        JOptionPane.showMessageDialog(this, "Harap masukkan ID dan jumlah barang yang valid.");
//    }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        close();
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jLabel1MouseDragged

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idbarangbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jumlahbarangbox;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField updatebarangbox;
    // End of variables declaration//GEN-END:variables
}
