/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

/**
 *
 * @author Lenovo
 */
public class Makanan extends javax.swing.JFrame {

    /**
     * Creates new form Makanan
     */
    public Makanan() {
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

        txtIdBarang = new javax.swing.JTextField();
        txtTanggalKadaluarsa = new javax.swing.JTextField();
        txtJenisMakanan = new javax.swing.JTextField();
        txtDeskripsi = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtJumlahBarang = new javax.swing.JTextField();
        txtTglBarangMasuk = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBarangActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 220, -1));
        getContentPane().add(txtTanggalKadaluarsa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 220, -1));

        txtJenisMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJenisMakananActionPerformed(evt);
            }
        });
        getContentPane().add(txtJenisMakanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 220, -1));
        getContentPane().add(txtDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 220, -1));
        getContentPane().add(txtNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 220, -1));
        getContentPane().add(txtJumlahBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 220, -1));
        getContentPane().add(txtTglBarangMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 220, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/makanan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBarangActionPerformed

    private void txtJenisMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJenisMakananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJenisMakananActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtDeskripsi;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JTextField txtJenisMakanan;
    private javax.swing.JTextField txtJumlahBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtTanggalKadaluarsa;
    private javax.swing.JTextField txtTglBarangMasuk;
    // End of variables declaration//GEN-END:variables
}