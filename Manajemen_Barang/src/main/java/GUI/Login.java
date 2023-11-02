
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jLabel2MouseDragged

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jLabel2MousePressed

    private void usernameboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameboxActionPerformed
       
    }//GEN-LAST:event_usernameboxActionPerformed

    private void exitbutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitbutMouseClicked
//        System.exit(0);
    int response = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar aplikasi?", ":(", JOptionPane.YES_NO_OPTION);
    
    if (response == JOptionPane.YES_OPTION) {
        // Jika pengguna memilih "Ya", maka keluar dari aplikasi
        System.exit(0);
    } else if (response == JOptionPane.NO_OPTION) {
        // Jika pengguna memilih "Tidak", tidak perlu melakukan apa-apa
    }
    }//GEN-LAST:event_exitbutMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
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

    }//GEN-LAST:event_btnLoginActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        close();
        SignUp su = new SignUp();
        su.setVisible(true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel exitbut;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JPasswordField jPasswordField1;
    public javax.swing.JTextField usernamebox;
    // End of variables declaration//GEN-END:variables
}
