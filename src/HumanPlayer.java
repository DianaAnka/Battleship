
import javax.swing.JOptionPane;

public class HumanPlayer extends BattleShipPlayer {
//Members 

    public int XIndexPlayer;
    public int YIndexPlayer;
    
    public int XIndexComputer;
    public int YIndexComputer;
    
    //Constructer
    public HumanPlayer() {

    }

    public HumanPlayer(String name, int id, int CountShip) {
        this.Name = name;
        this.ID = id;
        this.CountShip = CountShip;
    }

    //Get a new Move from player 
    public Square GetMove(Grid grid) {
        JOptionPane.showMessageDialog(null,"Enter Coordinates where you want to Attack with them");
        int x, y;
        x = XIndexComputer;
        y = YIndexComputer;
        Square Attacked = new Square(x, y, "Unknown", 0);
        return Attacked;
    }

    //Get ship location
    public Square GetShipPlace(Grid grid) {
        JOptionPane.showMessageDialog(null,"Enter Coordinates where you want to put your ship");
        XIndexPlayer = PROPERTISE.XIndex;      
        YIndexPlayer = PROPERTISE.YIndex;
        Square ShipSquare = new Square(XIndexPlayer, YIndexPlayer, "Unknown", 0);

        return ShipSquare;
    }
}
