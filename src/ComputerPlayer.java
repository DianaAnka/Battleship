


import java.util.Random;
import javax.swing.JOptionPane;

public class ComputerPlayer extends BattleShipPlayer{
    //Random 
    Random random = new Random();
    //Constructer
    public ComputerPlayer (){
        Name = "Computer" ;
        ID = 0 ;
        CountShip = 0 ;
    }
    //Methods
    public Square GetMove (Grid grid){
        
        int x , y ;
        //Get Random Value For x & Y 
        x = random.nextInt(grid.Get_Dim());
        y = random.nextInt(grid.Get_Dim());
        Square Attacked = new Square (x , y ,"UnKnown" , 0 );
        while(!grid.Square_Is_Accept(x, y)){
        x = random.nextInt(grid.Get_Dim());
        y = random.nextInt(grid.Get_Dim());
        Attacked.Set_x(x);
        Attacked.Set_y(y);
        }
        return Attacked ;
    }
    //Get ship location
    public Square GetShipPlace(Grid grid) {
        JOptionPane.showMessageDialog(null, "Enter Coordinates where you want to put your ship");
        int x = random.nextInt(grid.Get_Dim());
        int y = random.nextInt(grid.Get_Dim());
        Square ShipSquare = new Square(x, y, "Unknown", 0);
        while (!grid.Square_Is_Accept(x, y)) {
            x = random.nextInt(grid.Get_Dim());
            y = random.nextInt(grid.Get_Dim());
            ShipSquare.Set_x(x);
            ShipSquare.Set_y(y);
        }
        return ShipSquare;
    }
}
