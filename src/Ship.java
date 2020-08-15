

public class Ship implements java.io.Serializable{
    //Members 
    private int Size = 0 ;
    private int Id = 0 ;
    private boolean Injured = false ;
    //Constrcture
    public Ship (int size , int Id ){
        this.Size = size ;
        this.Id = Id ;
    }
    //Set & Get 
    public void Set_Size ( int size){
     this.Size = size ;
    }  
    public void Set_ID (int Id){
     this.Id = Id ;   
    }
    public int Get_Size (){
        return Size ;
    }
    public int Get_Id (){
        return  Id ;
    }
    public boolean Get_Injured () {
    	return Injured ;
    }
    public void Set_Injured ( boolean injured) {
    	Injured = injured ;
    }
    
}
