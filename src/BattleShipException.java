
import javax.swing.JOptionPane;


public class BattleShipException extends Exception {
	public String GetMassege() {
            JOptionPane.showConfirmDialog(null, "An Occur happend Game can't start");
		return null;
	}
}
