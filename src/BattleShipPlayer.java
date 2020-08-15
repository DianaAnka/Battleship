

public class BattleShipPlayer implements java.io.Serializable{
    //Members
    protected String Name ;
    protected int ID ;
    protected int Score ;
    protected int CountShip ;//Numbers of Complete Ship that a player has
    protected Square LastAttack = new Square(0, 0, "UnKnown", 0) ;
    protected double Luck = 0 ;
    
    //Set & Get 
    public void Set_Name (String Name){
     this.Name = Name ;   
    }
    public void Set_ID (int ID){
        this.ID = ID ;
    }
    public void Set_CountShip(int CountShip){
        this.CountShip = CountShip ;
    }
    public String Get_name (){
        return Name ;
    }
    public int Get_ID (){
        return ID ;
    }
    public int Get_CountShip (){
        return CountShip ;
    }
    
    public int Get_Score() {
    	return Score ;
    }
    
    public void Set_Score(int score) {
    	
    	Score = score ;
    }
    public void SetLastAttack ( Square Temp ) {
    	LastAttack = Temp ;
    }
    public Square GetLastAttack () {
    	return LastAttack ;
    }
    public void SetLuck (double l) {
    	Luck = l ;
    }
    public double GetLuck () {
    	return Luck ;
    }
}
