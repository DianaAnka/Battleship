/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah Maatouk
 */
public class FinalGame extends javax.swing.JFrame {

    
    public FinalGame() {
        initComponents();

        if(BattleShipsGame.PlayerWin == 1)
            Happy.setText("You Win Congradulation");
        else if(BattleShipsGame.PlayerWin == 2)
            Happy.setText("Computer Win! GameOver!");
        
        ScorePlayer.setText("Your Scour: " + PROPERTISE.HumanPlayer.Get_Score() );
        ScoreComputer.setText("Computer Scour: " + PROPERTISE.ComputerPlayer.Get_Score() );
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ScorePlayer = new javax.swing.JLabel();
        Happy = new javax.swing.JLabel();
        ScoreComputer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 100));

        ScorePlayer.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        ScorePlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ScorePlayer.setFocusable(false);
        ScorePlayer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Happy.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        Happy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Happy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Happy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        ScoreComputer.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        ScoreComputer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ScoreComputer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScorePlayer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Happy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScoreComputer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(Happy, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScorePlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScoreComputer, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinalGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Happy;
    private javax.swing.JLabel ScoreComputer;
    private javax.swing.JLabel ScorePlayer;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
