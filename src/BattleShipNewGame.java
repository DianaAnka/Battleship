

import java.io.Serializable;
import javax.swing.JOptionPane;

public class BattleShipNewGame extends Game implements  java.io.Serializable {


	// Members
    
	public int Dimen;
        public static int Ratio = 5 ;
        
	private HumanPlayer HumanPlayer;
	private ComputerPlayer ComputerPlayer;
        
	private Grid HumanGrid;
	private Grid ComputerGrid;
	private Grid HumanTempGrid;
	private Grid computerTempGrid;

	private Ship[] ships;
	private Ship[] ComputerShips;
        
        private Square [] CompMine ;
        private Square [] HumMine ;

	private int CountShips = 0;
        
        private boolean FinishGame ;
        private boolean HumTurn ;
        private boolean ComTurn ;

	// Constructer
        
        // Done
	public BattleShipNewGame(HumanPlayer HumanPlayer , ComputerPlayer ComputerPlayer ,
             Grid HumanGrid , Grid  ComputerGrid , Grid HumanTempGrid , Grid  computerTempGrid ,
          Ship[] ships , Ship[] ComputerShips ,  Square[] HumMine , Square [] CompMine  ,
        boolean FinishGame , boolean HumTurn , boolean ComTurn)  {
            this.HumanPlayer = HumanPlayer ;
            this.ComputerPlayer = ComputerPlayer ;
            this.HumanGrid = HumanGrid ;
            this.ComputerGrid = ComputerGrid ;
            this.HumanTempGrid = HumanTempGrid ;
            this.computerTempGrid = computerTempGrid ;
            this.ships = ships ;
            this.ComputerShips = ComputerShips ;
            this.CompMine = CompMine ;
            this.HumMine = HumMine ;
            this.FinishGame = FinishGame ;
            this.HumTurn = HumTurn ;
            this.ComTurn = ComTurn ;
	}
        

	// Subcribe a new Game
	// Methods
        
        
        // خالص
	public void Subscribe() {
            // choose Ships size
            int i = 0;
            int shipsize = 0;

            // Enter the ships sizes
            while (Ratio > 0) {
                //System.out.println("Enter The Size Of Ship number  " + (1 + i));
                shipsize = PROPERTISE.SizeShipSelected;

                ships[i].Set_Size(shipsize);
                ships[i].Set_ID(i + 1);
                ComputerShips[i].Set_Size(shipsize);
                ComputerShips[i].Set_ID(i + 1);
                i++;
                Ratio -= shipsize;
            }
            CountShips = i;
            //System.out.println("Count of Ships is " + i);

		// Add Ships to Human Grid

		for (int j = 0; j < i; j++) {
                    boolean ok = false;
                    while (!ok) {
                        int choice = PROPERTISE.RadioId;
                        Square ShipSquare = HumanPlayer.GetShipPlace(HumanGrid);
                        // put a ship horizontal
                        if (choice == 1) {
                                if (HumanGrid.Get_Dim() >= (ShipSquare.Get_y() + ships[j].Get_Size())
                                                && HumanGrid.Sequence_Is_Free(choice,choice, ships[j].Get_Size(), choice)) {
                                        ok = true;
                                } else {
                                   JOptionPane.showMessageDialog(null, "You can't put your Ship here the place is narrow");
                                   // ShipSquare = HumanPlayer.GetShipPlace(HumanGrid);
                                }
                                // Change the Statue of Squares
                        } // put a ship vertical
                        else {
                                if (HumanGrid.Get_Dim() >= (ShipSquare.Get_x() + ships[j].Get_Size())
                                                && HumanGrid.Sequence_Is_Free(choice,choice, ships[j].Get_Size(), choice)) {
                                        ok = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "You can't put your Ship here the place is narrow");
                                    // ShipSquare = HumanPlayer.GetShipPlace(HumanGrid);
                                }
                        }
                        if (ok)
                            HumanGrid.AddShip(55,5, ships[j].Get_Size(), choice, j + 1);
                    }
		}
		HumanGrid.FillWater();

		// add ships to Computer Grid
		for (int k = 0; k < i; k++) {
			boolean ok = false;
			while (!ok) {
                            int choice = 0;
                            Square ShipSquare = ComputerPlayer.GetShipPlace(ComputerGrid);
                            if (Math.random() < 0.5) {
                                choice = 1;
                                if (ComputerGrid.Get_Dim() >= (ShipSquare.Get_y() + ComputerShips[k].Get_Size())
                                                && ComputerGrid.Sequence_Is_Free(choice,choice, ComputerShips[k].Get_Size(), choice)) {
                                        ok = true;
                                }
                                    // Change the Statue of Squares
                            } // put a ship vertical
                            else {
                                choice = 2;
                                if (ComputerGrid.Get_Dim() >= (ShipSquare.Get_x() + ComputerShips[k].Get_Size())
                                                && ComputerGrid.Sequence_Is_Free(choice,choice, ComputerShips[k].Get_Size(), choice)) {
                                        ok = true;
                                }
                            }
                            if (ok)
                                ComputerGrid.AddShip(choice,choice, ComputerShips[k].Get_Size(), choice, k + 1);
			}
		}
		ComputerGrid.FillWater();
                
                
//		System.out.println("*********** Human Grid ***********");
//		HumanGrid.PrintGrid();
//		System.out.println("");
//		System.out.println("*********** computer Temp Grid ***********");
//		computerTempGrid.PrintGrid();
//		System.out.println("");
//		System.out.println("*********** Human Temp Grid ***********");
//		HumanTempGrid.PrintGrid();
//		System.out.println("");
//		System.out.println("*********** Computer Grid ***********");
//		ComputerGrid.PrintGrid();
//		System.out.println("");

	}

	// Run the Game
	public void Run() {
		// Inner Class
		class AttackResult {
			public String Result(Square S, Grid OppGrid, Ship[] Tempships) {
                        int x = S.Get_x();
                        int y = S.Get_y();
                        int id = OppGrid.GridArray[x][y].Get_IDShip();
                        int size;
                        if (id != 0) {
                                size = Tempships[id - 1].Get_Size();
                        } else
                                size = 0;
                        // A Ship has been Destroyed
                        if (OppGrid.GridArray[x][y].Get_Statue() == "Ship" && size == 1) {
                                Tempships[id - 1].Set_Size(0);
                                Tempships[id - 1].Set_Injured(true);
                                OppGrid.GridArray[x][y].Set_Statue("Destroyed");
                                JOptionPane.showMessageDialog(null, "Destroyed Completely");
                                return null;

                        } else if (OppGrid.GridArray[x][y].Get_Statue() == "Ship" && size != 1) {
                                Tempships[id - 1].Set_Size(size - 1);
                                Tempships[id - 1].Set_Injured(true);
                                OppGrid.GridArray[x][y].Set_Statue("Destroyed");
                                JOptionPane.showMessageDialog(null, "Destroyed");
                                return null;
                        } else {
                            JOptionPane.showMessageDialog(null, "Water");
                                return null;
                        }
                    }
		}

		for (int i = 1; i <= Dimen * Dimen; i++) {
			double luck ;
			luck = 0 ;
			// Human player turn
                        JOptionPane.showMessageDialog(null, "Your Turn");
			//Get a new move from Human player
			AttackResult NewAttack = new AttackResult();
			Square AttackedSquare = HumanPlayer.GetMove(HumanTempGrid);
			String Result = NewAttack.Result(AttackedSquare, ComputerGrid, ComputerShips);
			//System.out.println(Result);
			
			if (Result == "Destroyed Completely") {
				// A Ship is Destroyed Completely
				
				HumanPlayer.Set_CountShip(HumanPlayer.Get_CountShip() + 1);
				HumanPlayer.Set_Score(HumanPlayer.Get_Score() + ((Dimen * Dimen) - i + 1));
				// Set the Luck to Human player
				 luck = 2 * Math.pow(ComputerGrid.Manhattan(ComputerShips, AttackedSquare), 2)
						* Math.pow(Math.abs(AttackedSquare.Get_x() - HumanPlayer.GetLastAttack().Get_x())
								+ Math.abs(AttackedSquare.Get_y() - HumanPlayer.GetLastAttack().Get_y()), 2);
				HumanPlayer.SetLuck(luck);
				if (HumanPlayer.Get_CountShip() == CountShips) {
					HumanTempGrid.GridArray[AttackedSquare.Get_x()][AttackedSquare.Get_y()].Set_Statue("Destroyed");
					JOptionPane.showMessageDialog(null, "You Win Congradulation");
					break;
				} else {
					HumanTempGrid.GridArray[AttackedSquare.Get_x()][AttackedSquare.Get_y()].Set_Statue("Destroyed");
					JOptionPane.showMessageDialog(null, "You Destroy A Ship");
					HumanPlayer.Set_Score(HumanPlayer.Get_Score() + ((Dimen * Dimen) - i + 1));
                                        JOptionPane.showMessageDialog(null, "Count = " + HumanPlayer.Get_CountShip());
				}

			} else if (Result == "Destroyed") {
				HumanPlayer.Set_Score(HumanPlayer.Get_Score() + ((Dimen * Dimen) - i + 1));
				// A piece of Ship is Destroyed
				HumanTempGrid.GridArray[AttackedSquare.Get_x()][AttackedSquare.Get_y()].Set_Statue("Destroyed");
				JOptionPane.showMessageDialog(null, "You Destroy A piece of Ship");
				 luck = 2 * Math.pow(ComputerGrid.Manhattan(ComputerShips, AttackedSquare), 2)
						* Math.pow(Math.abs(AttackedSquare.Get_x() - HumanPlayer.GetLastAttack().Get_x())
								+ Math.abs(AttackedSquare.Get_y() - HumanPlayer.GetLastAttack().Get_y()), 2);
				HumanPlayer.SetLuck(luck);
			} else if (Result == "Water") {
				HumanTempGrid.GridArray[AttackedSquare.Get_x()][AttackedSquare.Get_y()].Set_Statue("Water");
				JOptionPane.showMessageDialog(null, "The Square is full of Water");
			}
                        JOptionPane.showMessageDialog(null, "Luck =  "+HumanPlayer.GetLuck());
			HumanPlayer.SetLastAttack(AttackedSquare);

			// Computer player turn
                        JOptionPane.showMessageDialog(null, "Computer Turn Please Wait...");
			do {
				AttackedSquare = ComputerPlayer.GetMove(computerTempGrid);
			} while (AttackedSquare.Get_Statue() != "UnKnown");
			Result = NewAttack.Result(AttackedSquare, HumanGrid, ships);
			if (Result == "Destroyed Completely") {
				ComputerPlayer.Set_Score(ComputerPlayer.Get_Score() + ((Dimen * Dimen) - i + 1));
				 luck = 2 * Math.pow(HumanGrid.Manhattan(ships, AttackedSquare), 2)
						* Math.pow(Math.abs(AttackedSquare.Get_x() - ComputerPlayer.GetLastAttack().Get_x())
								+ Math.abs(AttackedSquare.Get_y() - ComputerPlayer.GetLastAttack().Get_y()), 2);
				ComputerPlayer.SetLuck(luck);
				// A Ship is Destroyed Completely
				ComputerPlayer.Set_CountShip(ComputerPlayer.Get_CountShip() + 1);
				if (ComputerPlayer.Get_CountShip() == CountShips) {
					computerTempGrid.GridArray[AttackedSquare.Get_x()][AttackedSquare.Get_y()].Set_Statue("Destroyed");
					JOptionPane.showMessageDialog(null, "Game Over");
					break;
				} else {
					ComputerPlayer.Set_Score(ComputerPlayer.Get_Score() + ((Dimen * Dimen) - i + 1));
					computerTempGrid.GridArray[AttackedSquare.Get_x()][AttackedSquare.Get_y()].Set_Statue("Destroyed");
					JOptionPane.showMessageDialog(null, "Your Ship Has Destroyed");
                                        JOptionPane.showMessageDialog(null, "Count = " + ComputerPlayer.Get_CountShip());
				}
			} else if (Result == "Destroyed") {
				ComputerPlayer.Set_Score(ComputerPlayer.Get_Score() + ((Dimen * Dimen) - i + 1));
				 luck = 2 * Math.pow(HumanGrid.Manhattan(ships, AttackedSquare), 2)
						* Math.pow(Math.abs(AttackedSquare.Get_x() - ComputerPlayer.GetLastAttack().Get_x())
								+ Math.abs(AttackedSquare.Get_y() - ComputerPlayer.GetLastAttack().Get_y()), 2);
				ComputerPlayer.SetLuck(luck);
				// A piece of Ship is Destroyed
				computerTempGrid.GridArray[AttackedSquare.Get_x()][AttackedSquare.Get_y()].Set_Statue("Destroyed");
				JOptionPane.showMessageDialog(null, "A piece of Your Ship has Destroyed");
			} else if (Result == "Water") {
				computerTempGrid.GridArray[AttackedSquare.Get_x()][AttackedSquare.Get_y()].Set_Statue("Water");
				JOptionPane.showMessageDialog(null, "Water");
			}
			ComputerPlayer.SetLastAttack(AttackedSquare);
                        JOptionPane.showMessageDialog(null, "Luck = "+ComputerPlayer.GetLuck());
		}
		
//		System.out.println("*********** Human Grid ***********");
//		HumanGrid.PrintGrid();
//		System.out.println("");
//		System.out.println("*********** computer Temp Grid ***********");
//		computerTempGrid.PrintGrid();
//		System.out.println("");
//		System.out.println("*********** Human Temp Grid ***********");
//		HumanTempGrid.PrintGrid();
//		System.out.println("");
//		System.out.println("*********** Computer Grid ***********");
//		ComputerGrid.PrintGrid();
//		System.out.println("");

	}
        
  
}
