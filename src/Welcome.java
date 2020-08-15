
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JOptionPane;

public class Welcome extends javax.swing.JFrame {

    public static String UserName;

    public Welcome() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Play = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        PlayerName = new javax.swing.JTextField();
        LoadGame = new javax.swing.JButton();
        NewGame = new javax.swing.JButton();
        BackgroundWelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wlecome to Battleship");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setName("Welcome"); // NOI18N
        setPreferredSize(new java.awt.Dimension(612, 535));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Play.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        Play.setForeground(new java.awt.Color(54, 48, 0));
        Play.setText("Play");
        Play.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 172)));
        Play.setContentAreaFilled(false);
        Play.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Play.setName("Play"); // NOI18N
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });
        getContentPane().add(Play, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 100, 50));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(94, 85, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Enter Your Name:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 160, 30));

        PlayerName.setFont(new java.awt.Font("Comic Sans MS", 3, 16)); // NOI18N
        PlayerName.setToolTipText("");
        getContentPane().add(PlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 160, 30));

        LoadGame.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        LoadGame.setForeground(new java.awt.Color(51, 0, 51));
        LoadGame.setText("Load Game");
        LoadGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LoadGame.setContentAreaFilled(false);
        LoadGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LoadGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadGameActionPerformed(evt);
            }
        });
        getContentPane().add(LoadGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 140, 50));

        NewGame.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        NewGame.setForeground(new java.awt.Color(51, 0, 51));
        NewGame.setText("New Game ");
        NewGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NewGame.setContentAreaFilled(false);
        NewGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });
        getContentPane().add(NewGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 140, 50));

        BackgroundWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Untitled-1.jpg"))); // NOI18N
        BackgroundWelcome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 81), 3, true));
        getContentPane().add(BackgroundWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed

        UserName = PlayerName.getText();
//        if ("".equals(UserName)) {
//            JOptionPane.showMessageDialog(null, "Please Enter Your Name!");
//        } else {
//            PROPERTISE prep = new PROPERTISE();
//            prep.setVisible(true);
//            this.setVisible(false);
//        }

    }//GEN-LAST:event_PlayActionPerformed

    private void LoadGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadGameActionPerformed
        // TODO add your handling code here:
        UserName = PlayerName.getText();
        if ("".equals(UserName)) {
            JOptionPane.showMessageDialog(null, "Please Enter Your Name!");
        } else {
            File dir = new File("C:\\Users\\Diana MA\\Pictures\\Camera Roll\\");
            File[] listOfFiles = dir.listFiles();
            for (File file : listOfFiles) {
                if ((UserName + ".txt").equals(file.getName())) {
                    JOptionPane.showMessageDialog(null, " Found ");
                }
            }
        }
    }//GEN-LAST:event_LoadGameActionPerformed

    private void NewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameActionPerformed
        // TODO add your handling code here:
        UserName = PlayerName.getText();
        if ("".equals(UserName)) {
            JOptionPane.showMessageDialog(null, "Please Enter Your Name!");
        } else {
            PROPERTISE prep = new PROPERTISE();
            prep.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_NewGameActionPerformed

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
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundWelcome;
    private javax.swing.JButton LoadGame;
    private javax.swing.JButton NewGame;
    private javax.swing.JButton Play;
    private javax.swing.JTextField PlayerName;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
