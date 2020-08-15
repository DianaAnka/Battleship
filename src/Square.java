


public class Square implements java.io.Serializable {
    // members of Square
    private int x ; 
    private int y ;
    private String Statue ;
    private int IDShip ;
    
    //constructer 
    public Square (){
        x = 0 ;
        y = 0 ;
        Statue = "UnKnown";
        IDShip = 0 ;
    }
    
    public Square (int x , int y , String Statue , int IDShip ){
        this.x = x ;
        this.y = y ;
        this.Statue = Statue ;
        this.IDShip = IDShip ;
    }
    
    //Set & Get for members 
    public void Set_x (int x ){
        this.x = x ;
    }
    public void Set_y (int y ){
        this.y = y ;
    }
    public void Set_Statue (String Statue ){
        this.Statue = Statue ; 
    }
    public void Set_IDShip (int IDShip ){
        this.IDShip = IDShip ;
    }
    public int Get_x (){
        return x ;
    }
    public int Get_y (){
        return y ;
    }
    public String Get_Statue (){
        return Statue ;
    }
    public int Get_IDShip (){
        return IDShip ;
    }
    
            
}
