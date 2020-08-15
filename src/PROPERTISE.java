import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.util.Random;

public class PROPERTISE extends javax.swing.JFrame {

    public static int Dimen = 10;
    public static int SizeShipSelected;
    public static int XIndex;
    public static int YIndex;
    public static int IDHumanShip = 0;
    public static int IDComputerShip = 0;
    public static int TotalSizeShips = 0;
    public static int RadioId = 0;
    public static int CountShips = 0;
    public static int MineNumber = 0 ;
 
    
    public static int Ratio = 0;
    public static HumanPlayer HumanPlayer;
    public static ComputerPlayer ComputerPlayer;
    public static Grid HumanGrid;
    public static Grid ComputerGrid;
    public static Grid HumanTempGrid;
    public static Grid computerTempGrid;

    public static Ship[] HumanShips;
    public static Ship[] ComputerShips;
    
    public static Square[] HumanMine ;
    public static Square[] ComputerMine ;

    Random random = new Random();
    JButton[][] ArrayButton = new JButton[Dimen][Dimen];
    
    public PROPERTISE() {
    initComponents();

    
    HumanPlayer = new HumanPlayer(Welcome.UserName, 1, 0);
    ComputerPlayer = new ComputerPlayer();
    HumanGrid = new Grid(Dimen);
    ComputerGrid = new Grid(Dimen);
    HumanTempGrid = new Grid(Dimen);
    computerTempGrid = new Grid(Dimen);
    HumanShips = new Ship[Dimen*Dimen];
    
    HumanMine = new Square[Dimen*Dimen];
    ComputerMine = new Square[Dimen*Dimen];
    
    for (int i = 0; i < Dimen*Dimen; i++) {
        HumanShips[i] = new Ship(0, 0);
    }
    ComputerShips = new Ship[Dimen*Dimen];
    for (int i = 0; i < Dimen*Dimen; i++) {
        ComputerShips[i] = new Ship(0, 0);
    }

        ///interface

        ButtonGroup group = new ButtonGroup();
        group.add(RBVertical);
        group.add(RBHorizontal);

        RBHorizontal.setSelected(true);
        
        
        PanelButton.setVisible(true);
        PanelButton.setLayout(new GridLayout(Dimen,Dimen,4,4));
        for ( int i =0 ;i < Dimen; i ++)
        {
            for( int j=0 ;j < Dimen; j++)
            {
                JButton BIndix = new JButton();
                BIndix.setPreferredSize(new Dimension(40,40));
                BIndix.setMaximumSize(new Dimension(40, 40));
                BIndix.setMinimumSize(new Dimension(40, 40));
                PanelButton.add(BIndix);
                ArrayButton[i][j] = BIndix;
                ArrayButton[i][j].setBackground(Color.WHITE);
                BIndix.setActionCommand(""+i+j);

                ArrayButton[i][j].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                    String Tag = e.getActionCommand();

                    int Index = Integer.parseInt(Tag);
                    YIndex = Index%10 ;
                    XIndex = (Index/10);

                   // clickbutton(XIndex , YIndex ) ;
                    SizeShipSelected = Integer.parseInt(TextSize.getText());
                    
                     if(RBHorizontal.isSelected())
                    {
                        RadioId = 1;
                    }
                    if(RBVertical.isSelected())
                    {
                        RadioId = 2;
                    }
                    Subscribe(XIndex , YIndex , RadioId , SizeShipSelected);
                }
            });
        }
    } 
        
}
        
public void Subscribe( int XIndexPlayer, int YIndexPlayer, int Dir, int ShipSize ) {
    
    boolean ok = false;
       // Square ShipSquare = new Square(XIndexPlayer, YIndexPlayer, "Unknown", 0);
        if (RadioId == 1) {
             if(Dimen >= (YIndexPlayer + ShipSize)&& 
                    HumanGrid.Sequence_Is_Free(XIndexPlayer, YIndexPlayer, ShipSize, RadioId) && Ratio <20) {
                for(int i = YIndexPlayer ; i < YIndexPlayer +ShipSize ; i++)
                {
                     ArrayButton[XIndexPlayer][i].setBackground(Color.GRAY);
                    ok = true;
                }
            } else {
               JOptionPane.showMessageDialog(null, "You can't put your Ship here the place is narrow, Please Try Agin");
            }
        } 
        else {
              if(HumanGrid.Get_Dim() >= (XIndexPlayer + ShipSize) && 
                   HumanGrid.Sequence_Is_Free(XIndexPlayer, YIndexPlayer, ShipSize, RadioId) && Ratio <20) {
                for(int i = XIndexPlayer ; i < XIndexPlayer +ShipSize ; i++)
                {    ArrayButton[i][YIndexPlayer].setBackground(Color.GRAY);
                    ok = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "You can't put your Ship here the place is narrow");
            }
        }
        if (ok && Ratio <20) {
            HumanGrid.AddShip(XIndexPlayer ,YIndexPlayer , ShipSize, RadioId, IDHumanShip + 1);
            
            HumanShips[IDHumanShip].Set_Size(ShipSize);
            HumanShips[IDHumanShip].Set_ID(IDHumanShip + 1);
            ComputerShips[IDComputerShip].Set_Size(ShipSize);
            ComputerShips[IDComputerShip].Set_ID(IDComputerShip + 1);
            IDHumanShip++;
            
            CountShips++;
            Ratio += ShipSize;
            Result.setText("The Count Ships: " + CountShips);
            Result.setText("The Result: " + Ratio);
            insertShipComputer(ShipSize);
            IDComputerShip++;
            
        }
        else if(Ratio >= 20){
            JOptionPane.showMessageDialog(null, "You Can't insert any ship more");
        }
}

public void insertShipComputer(int ShipSize)
{
    boolean ok = false;
    while(!ok)
    {
        int XIndexPlayer = random.nextInt(Dimen) ;
        int YIndexPlayer = random.nextInt(Dimen) ;
        if (Math.random() < 0.5) {
        RadioId = 1;
        if (Dimen >= (YIndexPlayer + ShipSize)
                        && ComputerGrid.Sequence_Is_Free(XIndexPlayer, YIndexPlayer, ShipSize, RadioId)) {
                ok = true;
        }
        }
        else {
            RadioId = 2;
            if (Dimen >= (XIndexPlayer + ShipSize)
                            && ComputerGrid.Sequence_Is_Free(XIndexPlayer, YIndexPlayer, ShipSize, RadioId)) {
                    ok = true;
            }
        }
        if (ok)
        {
            ComputerGrid.AddShip(XIndexPlayer, YIndexPlayer, ShipSize, RadioId, IDComputerShip + 1);
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BNext = new javax.swing.JButton();
        PanelButton = new javax.swing.JPanel();
        RBHorizontal = new javax.swing.JRadioButton();
        RBVertical = new javax.swing.JRadioButton();
        Result = new javax.swing.JLabel();
        TextSize = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TBNumberMine = new javax.swing.JTextField();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 3, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(197, 255, 18));
        jLabel3.setText("Ship Size:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, 30));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 3, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(214, 255, 42));
        jLabel4.setText("Choose the position of the ship");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 270, 30));

        BNext.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        BNext.setForeground(new java.awt.Color(153, 0, 102));
        BNext.setText("Next >>");
        BNext.setMaximumSize(new java.awt.Dimension(200, 27));
        BNext.setMinimumSize(new java.awt.Dimension(200, 27));
        BNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNextActionPerformed(evt);
            }
        });
        getContentPane().add(BNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 100, 30));

        PanelButton.setPreferredSize(new java.awt.Dimension(500, 300));

        javax.swing.GroupLayout PanelButtonLayout = new javax.swing.GroupLayout(PanelButton);
        PanelButton.setLayout(PanelButtonLayout);
        PanelButtonLayout.setHorizontalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        PanelButtonLayout.setVerticalGroup(
            PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        getContentPane().add(PanelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 490, 350));

        RBHorizontal.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        RBHorizontal.setForeground(new java.awt.Color(236, 179, 25));
        RBHorizontal.setText("Horizontal");
        getContentPane().add(RBHorizontal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        RBVertical.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        RBVertical.setForeground(new java.awt.Color(236, 179, 25));
        RBVertical.setText("Vertical");
        RBVertical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBVerticalActionPerformed(evt);
            }
        });
        getContentPane().add(RBVertical, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        Result.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        Result.setForeground(new java.awt.Color(239, 0, 0));
        getContentPane().add(Result, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 170, 40));

        TextSize.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        TextSize.setText("1");
        TextSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextSizeActionPerformed(evt);
            }
        });
        getContentPane().add(TextSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 90, 30));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 3, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(197, 255, 18));
        jLabel5.setText("choose The size to the ship");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 250, 20));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 3, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(197, 255, 18));
        jLabel6.setText("Number mine:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 130, 30));

        TBNumberMine.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        TBNumberMine.setText("1");
        TBNumberMine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TBNumberMineActionPerformed(evt);
            }
        });
        getContentPane().add(TBNumberMine, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 90, 30));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Untitled-2.jpg"))); // NOI18N
        Background.setName(""); // NOI18N
        Background.setPreferredSize(new java.awt.Dimension(800, 500));
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 440));

        setBounds(0, 0, 807, 470);
    }// </editor-fold>//GEN-END:initComponents

    private void clickbutton (int k , int h)
    {
        JOptionPane.showMessageDialog(null, "(X = "+k + ",Y = "+ h+")");
    }
              
    private void BNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNextActionPerformed
        // TODO add your handling code here:
      MineNumber = Integer.parseInt(TBNumberMine.getText());
        if(MineNumber < 1 || MineNumber > 10)
        {
            JOptionPane.showMessageDialog(null, "Please Less number of Mine!");
        }
        else 
        {
            HumanGrid.FillWater();
            ComputerGrid.FillWater();
            for(int k = 0 ; k < MineNumber ; k ++){
            HumanMine[k] = HumanGrid.AddMine();
            ComputerMine[k] = ComputerGrid.AddMine();    
            }
            BattleShipsGame X1 = new BattleShipsGame();
            X1.setVisible(true);
            this.setVisible(false);
        }
        
        
           
        
       
    }//GEN-LAST:event_BNextActionPerformed

    private void RBVerticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBVerticalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RBVerticalActionPerformed

    private void TBNumberMineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TBNumberMineActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_TBNumberMineActionPerformed

    private void TextSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextSizeActionPerformed

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
            java.util.logging.Logger.getLogger(PROPERTISE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PROPERTISE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PROPERTISE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PROPERTISE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PROPERTISE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BNext;
    private javax.swing.JLabel Background;
    private javax.swing.JPanel PanelButton;
    private javax.swing.JRadioButton RBHorizontal;
    private javax.swing.JRadioButton RBVertical;
    private javax.swing.JLabel Result;
    private javax.swing.JTextField TBNumberMine;
    private javax.swing.JTextField TextSize;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}

