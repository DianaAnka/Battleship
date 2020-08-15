
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;

public class BattleShipsGame extends javax.swing.JFrame {

    JButton[][] ArrayButtonPlayer = new JButton[PROPERTISE.Dimen][PROPERTISE.Dimen];
    JButton[][] ArrayButtonComputer = new JButton[PROPERTISE.Dimen][PROPERTISE.Dimen];
    public int XIndexComputer;
    public int YIndexComputer;
    public int XIndexPlayer;
    public int YIndexPlayer;
    public int Count = 0;
    public boolean Stop_while_Time = true;
    public boolean Stop_while_TimePC = true;

    public static int PlayerWin = 0;

    public boolean PlayerGame = true;

    //Here
    public static boolean FinshGame = true; /// نهاية اللعبة

    public static boolean HumTurn = true;

    public static boolean ComTurn = true;

    Random random = new Random();

    myThread_time_Round thread_time_Round = new myThread_time_Round();

    myThread_time_Game thread_time_Game = new myThread_time_Game();

    MineThread mine = new MineThread();

    BattleShipNewGame Game;

    public BattleShipsGame() {
        initComponents();

        // thread_time_Round = new myThread_time_Round();
        thread_time_Round.start();
        mine.start();

        thread_time_Game.start();
        /* Game = new BattleShipNewGame ( PROPERTISE.HumanPlayer , PROPERTISE.ComputerPlayer , PROPERTISE.HumanGrid ,
         PROPERTISE.ComputerGrid , PROPERTISE.HumanTempGrid , PROPERTISE.computerTempGrid , PROPERTISE.HumanShips ,
         PROPERTISE.ComputerShips , PROPERTISE.ComputerMine , PROPERTISE.HumanMine , FinshGame , HumTurn , ComTurn) ;*/

        Player.setText("Name: " + Welcome.UserName);
        PanelPlayer.setLayout(new GridLayout(PROPERTISE.Dimen, PROPERTISE.Dimen, 5, 5));
        for (int i = 0; i < PROPERTISE.Dimen; i++) {
            for (int j = 0; j < PROPERTISE.Dimen; j++) {
                JButton BIndix = new JButton();
                BIndix.setPreferredSize(new Dimension(40, 40));
                BIndix.setMaximumSize(new Dimension(40, 40));
                BIndix.setMinimumSize(new Dimension(40, 40));
                PanelPlayer.add(BIndix);
                ArrayButtonPlayer[i][j] = BIndix;

                if ("Ship".equals(PROPERTISE.HumanGrid.GridArray[i][j].Get_Statue())) {
                    ArrayButtonPlayer[i][j].setBackground(Color.GRAY);
                } else if ("Mine".equals(PROPERTISE.HumanGrid.GridArray[i][j].Get_Statue())) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("Image/Mine.jpg"));
                        ArrayButtonPlayer[i][j].setIcon(new ImageIcon(img));
                    } catch (IOException ex) {
                        Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    ArrayButtonPlayer[i][j].setBackground(Color.CYAN);
                }
            }
        }

        PanelComputer.setLayout(new GridLayout(PROPERTISE.Dimen, PROPERTISE.Dimen, 5, 5));
        for (int i = 0; i < PROPERTISE.Dimen; i++) {
            for (int j = 0; j < PROPERTISE.Dimen; j++) {
                JButton BIndix = new JButton();
                BIndix.setPreferredSize(new Dimension(40, 40));
                BIndix.setMaximumSize(new Dimension(40, 40));
                BIndix.setMinimumSize(new Dimension(40, 40));
                PanelComputer.add(BIndix);
                ArrayButtonComputer[i][j] = BIndix;
                ArrayButtonComputer[i][j].setBackground(Color.yellow);
                BIndix.setActionCommand("" + i + j);

                ArrayButtonComputer[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Tag = e.getActionCommand();
                        int Index = Integer.parseInt(Tag);
                        YIndexComputer = Index % 10;
                        XIndexComputer = (Index / 10);
                        Count++;
                        ShotsCount.setText("Shots: " + Count);
                        NamePlayer.setText("Your Turn");
                        HumanTurn(XIndexComputer, YIndexComputer);
                        myThread_time_Round Hu = new myThread_time_Round();
                        Hu.start();
                    }
                });
            }
        }
    }

    private void clickButton(int k, int h) {
        JOptionPane.showMessageDialog(this, "(X = " + k + ",Y = " + h + ")");
    }

    public void MusicWater() {
        File FMusicWater = new File("Water.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(FMusicWater));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {

        }
    }

    public void MusicMine() {
        File FMusicBig_Bomb = new File("Big_Bomb.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(FMusicBig_Bomb));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {

        }
    }

    public void MusicBoom() {
        File FMusicSonic_Boom = new File("Sonic_Boom.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(FMusicSonic_Boom));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 10);
        } catch (Exception e) {

        }
    }

    public void MusicBoomComplet() {
        File FMusicSonic_BoomComplet = new File("Bomb-Sound.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(FMusicSonic_BoomComplet));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 10);
        } catch (Exception e) {

        }
    }

    public void HumanTurn(int XIndexComputer, int YIndexComputer) {
        //Here
        if (FinshGame && HumTurn) {

            String Statue = PROPERTISE.ComputerGrid.GridArray[XIndexComputer][YIndexComputer].Get_Statue();
            int ID = PROPERTISE.ComputerGrid.GridArray[XIndexComputer][YIndexComputer].Get_IDShip();

            int SizeShip = 0;
            if (ID != 0) {
                SizeShip = PROPERTISE.ComputerShips[ID - 1].Get_Size();
            }
            if ("Ship".equals(Statue) && SizeShip == 1) {
                PROPERTISE.ComputerGrid.GridArray[XIndexComputer][YIndexComputer].Set_Statue("Water");
                PROPERTISE.ComputerShips[ID - 1].Set_Size(0);
                PROPERTISE.ComputerShips[ID - 1].Set_Injured(true);
                PROPERTISE.HumanPlayer.Set_CountShip(PROPERTISE.HumanPlayer.Get_CountShip() + 1);
                PROPERTISE.HumanPlayer.Set_Score(PROPERTISE.HumanPlayer.Get_Score() + ((PROPERTISE.Dimen * PROPERTISE.Dimen) - Count));
                try {
                    Image img = ImageIO.read(getClass().getResource("Image/boom.jpg"));
                    ArrayButtonComputer[XIndexComputer][YIndexComputer].setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                PlayerScore.setText("Player: " + PROPERTISE.HumanPlayer.Get_Score());
                MusicBoomComplet();
            } else if ("Ship".equals(Statue)) {
                PROPERTISE.ComputerGrid.GridArray[XIndexComputer][YIndexComputer].Set_Statue("water");
                PROPERTISE.ComputerShips[ID - 1].Set_Size(SizeShip - 1);
                PROPERTISE.ComputerShips[ID - 1].Set_Injured(true);

                PROPERTISE.HumanPlayer.Set_Score(PROPERTISE.HumanPlayer.Get_Score() + ((PROPERTISE.Dimen * PROPERTISE.Dimen) - Count));
                try {
                    Image img = ImageIO.read(getClass().getResource("Image/boom.jpg"));
                    ArrayButtonComputer[XIndexComputer][YIndexComputer].setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                PlayerScore.setText("Player: " + PROPERTISE.HumanPlayer.Get_Score());
                MusicBoom();
            } else if ("Mine".equals(Statue)) {
                PROPERTISE.ComputerGrid.GridArray[XIndexComputer][YIndexComputer].Set_Statue("water");
                PROPERTISE.HumanPlayer.Set_Score((PROPERTISE.HumanPlayer.Get_Score()) * 3);
                PlayerScore.setText("Player: " + PROPERTISE.HumanPlayer.Get_Score());
                ArrayButtonComputer[XIndexComputer][YIndexComputer].setBackground(Color.BLACK);
                for (int k = 0; k < PROPERTISE.MineNumber; k++) {
                    if (PROPERTISE.HumanMine[k].Get_x() == XIndexComputer && PROPERTISE.HumanMine[k].Get_y() == YIndexComputer) {
                        PROPERTISE.HumanMine[k].Set_Statue("Water");
                    }
                }
                MineTurnHuman(XIndexComputer, YIndexComputer);
            } else {
                try {
                    Image img = ImageIO.read(getClass().getResource("Image/water.jpg"));
                    ArrayButtonComputer[XIndexComputer][YIndexComputer].setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                MusicWater();

            }

            if (PROPERTISE.HumanPlayer.Get_CountShip() == PROPERTISE.CountShips) {
                PlayerWin = 1;
                FinalGame X = new FinalGame();
                //Here
                FinshGame = false;
                X.setVisible(true);
                this.hide();
            }
        }
        HumTurn = false;
        ComTurn = true;

    }

    public void MineTurnHuman(int XComputer, int YComputer) {
        MusicMine();
        for (int k = XComputer - 1; k < XComputer + 2; k++) {
            for (int j = YComputer - 1; j < YComputer + 2; j++) {
                if (k < PROPERTISE.Dimen && k >= 0 && j < PROPERTISE.Dimen && j >= 0) {
                    HumanTurn(k, j);
                }
            }
        }
    }

    public void MineTurnComputer(int XComputer, int YComputer) {
        MusicBoomComplet();
        for (int k = XComputer - 1; k < XComputer + 2; k++) {
            for (int j = YComputer - 1; j < YComputer + 2; j++) {
                if (k < PROPERTISE.Dimen && k >= 0 && j < PROPERTISE.Dimen && j >= 0) {
                    ComputerTurn(k, j);
                }
            }
        }
    }

    public void ComputerTurn(int XIndexPlayer, int YIndexPlayer) {
        //Here
        if (FinshGame && ComTurn) {
//            thread_time_Round = new myThread_time_Round();
//            thread_time_Round.start();
//        
//            Thread_time_RoundPC = new myThread_time_RoundPC();
//            Thread_time_RoundPC.start();

            String Statue = PROPERTISE.HumanGrid.GridArray[XIndexPlayer][YIndexPlayer].Get_Statue();
            int ID = PROPERTISE.HumanGrid.GridArray[XIndexPlayer][YIndexPlayer].Get_IDShip();
            int SizeShip = 0;
            if (ID != 0) {
                SizeShip = PROPERTISE.HumanShips[ID - 1].Get_Size();
            }
            if ("Ship".equals(Statue) && SizeShip == 1) {
                PROPERTISE.HumanGrid.GridArray[XIndexPlayer][YIndexPlayer].Set_Statue("Water");
                PROPERTISE.computerTempGrid.GridArray[XIndexPlayer][YIndexPlayer].Set_Statue("Destroyed");
                PROPERTISE.HumanShips[ID - 1].Set_Size(0);
                PROPERTISE.HumanShips[ID - 1].Set_Injured(true);
                PROPERTISE.ComputerPlayer.Set_CountShip(PROPERTISE.ComputerPlayer.Get_CountShip() + 1);
                PROPERTISE.ComputerPlayer.Set_Score(PROPERTISE.ComputerPlayer.Get_Score() + ((PROPERTISE.Dimen * PROPERTISE.Dimen) - Count));
                try {
                    Image img = ImageIO.read(getClass().getResource("Image/boom.jpg"));
                    ArrayButtonPlayer[XIndexPlayer][YIndexPlayer].setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                ComputerScore.setText("Computer: " + PROPERTISE.ComputerPlayer.Get_Score());
                MusicBoom();
            } else if ("Ship".equals(Statue)) {
                PROPERTISE.HumanGrid.GridArray[XIndexPlayer][YIndexPlayer].Set_Statue("Water");
                PROPERTISE.computerTempGrid.GridArray[XIndexPlayer][YIndexPlayer].Set_Statue("Destroyed");
                PROPERTISE.HumanShips[ID - 1].Set_Size(SizeShip - 1);
                PROPERTISE.HumanShips[ID - 1].Set_Injured(true);
                PROPERTISE.ComputerPlayer.Set_Score(PROPERTISE.ComputerPlayer.Get_Score() + ((PROPERTISE.Dimen * PROPERTISE.Dimen) - Count));
                try {
                    Image img = ImageIO.read(getClass().getResource("Image/boom.jpg"));
                    ArrayButtonPlayer[XIndexPlayer][YIndexPlayer].setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                ComputerScore.setText("Computer: " + PROPERTISE.ComputerPlayer.Get_Score());
                MusicBoomComplet();
            } else if ("Mine".equals(Statue)) {

                PROPERTISE.HumanGrid.GridArray[XIndexPlayer][YIndexPlayer].Set_Statue("water");
                PROPERTISE.HumanTempGrid.GridArray[XIndexPlayer][YIndexPlayer].Set_Statue("Destroyed");

                ArrayButtonPlayer[XIndexPlayer][YIndexPlayer].setBackground(Color.BLACK);

                MineTurnComputer(XIndexPlayer, YIndexPlayer);
                for (int k = 0; k < PROPERTISE.MineNumber; k++) {
                    if (PROPERTISE.ComputerMine[k].Get_x() == XIndexPlayer && PROPERTISE.HumanMine[k].Get_y() == YIndexPlayer) {
                        PROPERTISE.ComputerMine[k].Set_Statue("Water");
                    }
                }
//            PROPERTISE.ComputerPlayer.Set_Score(PROPERTISE.ComputerPlayer.Get_Score()*3);
//            ComputerScore.setText( "Computer: " + PROPERTISE.ComputerPlayer.Get_Score());
            } else {

                PROPERTISE.computerTempGrid.GridArray[XIndexPlayer][YIndexPlayer].Set_Statue("Water");
                try {
                    Image img = ImageIO.read(getClass().getResource("Image/water.jpg"));
                    ArrayButtonPlayer[XIndexPlayer][YIndexPlayer].setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                MusicWater();
            }

            if (PROPERTISE.ComputerPlayer.Get_CountShip() == PROPERTISE.CountShips) {
                PlayerWin = 2;
                FinalGame X = new FinalGame();
                X.setVisible(true);
                //Here
                FinshGame = false;
                this.setVisible(false);

            }
        }
        HumTurn = true;
        ComTurn = false;
    }

//				 luck = 2 * Math.pow(HumanGrid.Manhattan(ships, AttackedSquare), 2)
//						* Math.pow(Math.abs(AttackedSquare.Get_x() - ComputerPlayer.GetLastAttack().Get_x())
//								+ Math.abs(AttackedSquare.Get_y() - ComputerPlayer.GetLastAttack().Get_y()), 2);
//				ComputerPlayer.SetLuck(luck);
//    ComputerPlayer.GetLuck()
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        Player = new javax.swing.JLabel();
        PanelPlayer = new javax.swing.JPanel();
        PanelComputer = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        ShotsCount = new javax.swing.JLabel();
        Lucky = new javax.swing.JLabel();
        Computer1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Score = new javax.swing.JLabel();
        ComputerScore = new javax.swing.JLabel();
        PlayerScore = new javax.swing.JLabel();
        Timer = new javax.swing.JLabel();
        CumputerGrid = new javax.swing.JButton();
        NamePlayer = new javax.swing.JLabel();
        Save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Title.setBackground(new java.awt.Color(23, 0, 157));
        Title.setFont(new java.awt.Font("Comic Sans MS", 3, 48)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 51, 102));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Battleships");
        Title.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Player.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        Player.setForeground(new java.awt.Color(141, 141, 141));
        Player.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player.setText("Player");
        Player.setToolTipText("");
        Player.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        PanelPlayer.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout PanelPlayerLayout = new javax.swing.GroupLayout(PanelPlayer);
        PanelPlayer.setLayout(PanelPlayerLayout);
        PanelPlayerLayout.setHorizontalGroup(
            PanelPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelPlayerLayout.setVerticalGroup(
            PanelPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelComputer.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout PanelComputerLayout = new javax.swing.GroupLayout(PanelComputer);
        PanelComputer.setLayout(PanelComputerLayout);
        PanelComputerLayout.setHorizontalGroup(
            PanelComputerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelComputerLayout.setVerticalGroup(
            PanelComputerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 96)));

        ShotsCount.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        ShotsCount.setForeground(new java.awt.Color(102, 102, 102));
        ShotsCount.setText("Shots:");

        Lucky.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        Lucky.setForeground(new java.awt.Color(0, 102, 102));
        Lucky.setText("Lucky: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Lucky, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ShotsCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Lucky, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ShotsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Computer1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        Computer1.setForeground(new java.awt.Color(141, 141, 141));
        Computer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Computer1.setText("Computer");
        Computer1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 96)));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setMaximumSize(new java.awt.Dimension(172, 92));

        Score.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Score.setText("Score");

        ComputerScore.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        ComputerScore.setForeground(new java.awt.Color(24, 24, 24));
        ComputerScore.setText("Computer: ");

        PlayerScore.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        PlayerScore.setForeground(new java.awt.Color(24, 24, 24));
        PlayerScore.setText("Player:");

        Timer.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        Timer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ComputerScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Score, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PlayerScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(Timer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(135, 135, 135))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(Score)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PlayerScore, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComputerScore, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(Timer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CumputerGrid.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        CumputerGrid.setText("Computer Grid");
        CumputerGrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CumputerGridActionPerformed(evt);
            }
        });

        NamePlayer.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N

        Save.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Save.setText("Save");
        Save.setAutoscrolls(true);
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Player, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                        .addGap(137, 137, 137)
                        .addComponent(Computer1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(PanelPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CumputerGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NamePlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addComponent(PanelComputer, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Player, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Computer1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addComponent(PanelComputer, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CumputerGrid)
                        .addGap(26, 26, 26)
                        .addComponent(NamePlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Save)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CumputerGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CumputerGridActionPerformed
        // TODO add your handling code here:
        TempHuman X1 = new TempHuman();
        X1.setVisible(true);
    }//GEN-LAST:event_CumputerGridActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
        BattleShipNewGame MyGame = new BattleShipNewGame(PROPERTISE.HumanPlayer, PROPERTISE.ComputerPlayer,
                PROPERTISE.HumanGrid, PROPERTISE.ComputerGrid, PROPERTISE.HumanTempGrid, PROPERTISE.computerTempGrid,
                PROPERTISE.HumanShips, PROPERTISE.ComputerShips, PROPERTISE.HumanMine, PROPERTISE.ComputerMine,
                FinshGame, HumTurn, ComTurn);
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose where to save your Game");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        JOptionPane.showMessageDialog(null,f);
        try {
            FileOutputStream fileout = new FileOutputStream(filename+"\\"+PROPERTISE.HumanPlayer.Get_name()+".txt");
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(MyGame);
            out.close();
            fileout.close();
            FileOutputStream DefaulteFile = new FileOutputStream("C:\\Users\\Diana MA\\Pictures\\Camera Roll\\"+PROPERTISE.HumanPlayer.Get_name()+".txt");
            ObjectOutputStream o = new ObjectOutputStream(DefaulteFile);
            o.writeObject(MyGame);
            o.close();
            DefaulteFile.close();
            JOptionPane.showMessageDialog(null,"Save is Done");
        } catch (Exception i) {
            JOptionPane.showMessageDialog(null,"An Error Occure");
            i.printStackTrace();
        }
    }//GEN-LAST:event_SaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BattleShipsGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BattleShipsGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BattleShipsGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BattleShipsGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BattleShipsGame().setVisible(true);
            }
        });
    }

    class myThread_time_Round extends Thread {

        public void run() {
            int SS = 0;
            Stop_while_Time = true;
            while (Stop_while_Time) {
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                SS++;
                if (SS > 10) {
                    myThread_time_RoundPC roundPC = new myThread_time_RoundPC();
                    roundPC.start();
                    Stop_while_Time = false;
                }
            }
            ComTurn = false;
            HumTurn = true;

        }

    }

    class myThread_time_RoundPC extends Thread {

        public void run() {
            NamePlayer.setText("Computer Turn Please Wait...");
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            int XPlayer = 0, YPlayer = 0;
            do {
                XPlayer = random.nextInt(PROPERTISE.Dimen);
                YPlayer = random.nextInt(PROPERTISE.Dimen);
            } while (!"UnKnown".equals(PROPERTISE.computerTempGrid.GridArray[XPlayer][YPlayer].Get_Statue()));
            ComTurn = true;
            HumTurn = false;
            ComputerTurn(XPlayer, YPlayer);

        }
    }

    class myThread_time_Game extends Thread {

        public void run() {
            int SS = 0, MM = 0, HH = 0;
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                SS++;
                if (SS == 60) {
                    SS = 0;
                    MM++;
                }
                if (MM == 60) {
                    MM = 0;
                    HH++;
                }
                Timer.setText(HH + " : " + MM + " : " + SS);
            }
        }
    }

    class MineThread extends Thread {

        public void run() {
            while (true) {
                try {
                    sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleShipsGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < PROPERTISE.HumanMine.length; i++) {
                    if ("Mine".equals(PROPERTISE.HumanMine[i])) {
                        PROPERTISE.HumanGrid.MoveMine(PROPERTISE.HumanMine[i]);
                    }
                    if ("Mine".equals(PROPERTISE.ComputerMine[i])) {
                        PROPERTISE.ComputerGrid.MoveMine(PROPERTISE.ComputerMine[i]);
                    }
                }
            }

             //PROPERTISE.HumanGrid.PrintGrid();
            //PROPERTISE.ComputerGrid.PrintGrid();
        }

    }

    public void SaveGame() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Computer1;
    private javax.swing.JLabel ComputerScore;
    private javax.swing.JButton CumputerGrid;
    private javax.swing.JLabel Lucky;
    private javax.swing.JLabel NamePlayer;
    private javax.swing.JPanel PanelComputer;
    private javax.swing.JPanel PanelPlayer;
    private javax.swing.JLabel Player;
    private javax.swing.JLabel PlayerScore;
    private javax.swing.JButton Save;
    private javax.swing.JLabel Score;
    private javax.swing.JLabel ShotsCount;
    private javax.swing.JLabel Timer;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
