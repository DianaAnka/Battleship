
import java.util.Random;
import javax.swing.JOptionPane;
import sun.management.snmp.util.MibLogger;


public class Grid implements java.io.Serializable{

	// Members of Grid
	private int Dim;
	public Square[][] GridArray;
        Random random = new Random();

	// Constructer
	public Grid(int D) {
		Dim = D;
		GridArray = new Square[Dim][Dim];
		for (int i = 0; i < GridArray.length; i++) {
			for (int j = 0; j < GridArray.length; j++) {
				GridArray[i][j] = new Square(i, j, "UnKnown", 0);
			}
		}

	}
        
	// Set & Get for Members
	public int Get_Dim() {
		return Dim;
	}
	public void Set_Dim(int D) {
		Dim = D;
		for (int i = 0; i < GridArray.length; i++) {
			for (int j = 0; j < GridArray.length; j++) {
				GridArray[i][j].Set_x(i);
				GridArray[i][j].Set_y(j);
				GridArray[i][j].Set_Statue("Unknown");
				GridArray[i][j].Set_IDShip(0);
			}
		}
	}

	// Method to check out if a Square is in our Grid or not
	public boolean Square_Is_Accept(int x, int y) {
		if (x >= 0 && x < Dim && y >= 0 && y < Dim) {
			return true;
		}
		return false;
	}

	// Method to check if a sequence of squares is Unknown
	public boolean Sequence_Is_Free(int XIndexPlayer,int YIndexPlayer, int size, int choice) {

		if (choice == 2) {// Vertical
			for (int k = XIndexPlayer; k < XIndexPlayer+size; k++) {
				if (!"UnKnown".equals(GridArray[k][YIndexPlayer].Get_Statue()))
                                { 
					return false;
                                }
			}
		} else {//Horizintal
			for (int i = YIndexPlayer; i < YIndexPlayer+size; i++) {
				if (!"UnKnown".equals(GridArray[XIndexPlayer][i].Get_Statue()))
                                { 
                                 
					return false;
                                }
			}
		}
		return true;
	}

	public void AddShip(int XIndexPlayer,int YIndexPlayer, int size, int choice, int ID) {

		if (choice == 1) {// Horizontal
			for (int k = YIndexPlayer; k < size + YIndexPlayer; k++) {
				GridArray[XIndexPlayer][k].Set_Statue("Ship");
				GridArray[XIndexPlayer][k].Set_IDShip(ID);
			}
		} else {// Vertical
			for (int i = XIndexPlayer; i < size + XIndexPlayer; i++) {
				GridArray[i][YIndexPlayer].Set_Statue("Ship");
				GridArray[i][YIndexPlayer].Set_IDShip(ID);

			}
		}
	}

	public void PrintGrid() {
		for (int i = 0; i < GridArray.length; i++) {
                    for (int j = 0; j < GridArray.length; j++) {
                        JOptionPane.showMessageDialog(null, GridArray[i][j].Get_Statue() + " " + GridArray[i][j].Get_IDShip()+"    ");
                    }
                    System.out.println("");
		}
	}

	// Fill Grid with Water
	public void FillWater() {
		for (int i = 0; i < GridArray.length; i++) {
			for (int j = 0; j < GridArray.length; j++) {
				if (!"Ship".equals(GridArray[i][j].Get_Statue()))
					GridArray[i][j].Set_Statue("Water");
			}

		}
	}
	
	public int Manhattan ( Ship[] ship , Square sq ) { //1
		int mn = 1000000 ;
		int id = sq.Get_IDShip() ;
		int x = sq.Get_x() ;
		int y = sq.Get_y() ;
		for (int i = 0; i < GridArray.length; i++) {
			for (int j = 0; j < GridArray.length; j++) {
			  int idgrid = GridArray[i][j].Get_IDShip() ;
		      if (idgrid != id &&  idgrid != 0 )
		      {
		    	  if (ship[idgrid-1].Get_Injured() != true )
		    	  {
		    		 mn = Math.min(mn, Math.abs(x-i) + Math.abs(y-j) );
		    	  }
		      }
			}
		}
		return mn ;
	}
        public Square AddMine ( ){
            int x , y ;
            
        //Get Random Value For x & Y 
        x = random.nextInt(Dim);
        y = random.nextInt(Dim);
        while (!"Water".equals(GridArray[x][y].Get_Statue())){
        x = random.nextInt(Dim);
        y = random.nextInt(Dim);    
        }
        Square S = new Square(x, y, "Mine", 0 );
        GridArray[x][y].Set_Statue("Mine");
        return S ; 
        }
        public void MoveMine(Square s){
            int x = s.Get_x();
            int y = s.Get_y();
            if ((x+1) < Dim && "Water".equals(GridArray[x+1][y].Get_Statue())){
                GridArray[x][y].Set_Statue("Water");
                GridArray[x+1][y].Set_Statue("Mine");
                s.Set_x(x+1);
            }
           else if ((x-1) >= 0 && "Water".equals(GridArray[x-1][y].Get_Statue())){
                GridArray[x][y].Set_Statue("Water");
                GridArray[x-1][y].Set_Statue("Mine");
                s.Set_x(x-1);
            }
             else if ((y-1) >= 0 && "Water".equals(GridArray[x][y-1].Get_Statue())){
                GridArray[x][y].Set_Statue("Water");
                GridArray[x][y-1].Set_Statue("Mine");
                s.Set_y(y-1);
            }
            else if ((y+1) < Dim && "Water".equals(GridArray[x][y+1].Get_Statue())){
                GridArray[x][y].Set_Statue("Water");
                GridArray[x][y+1].Set_Statue("Mine");
                s.Set_y(y+1);
            }
        }
        
}
