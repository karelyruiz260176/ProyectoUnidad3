
package model;


public class Dulces {
    
      
    String name;
    int id;
    String foto;
    int precio;
    
    public Dulces(int id, String name, String foto, int precio){
        this.id = id;
        this.name = name;
        this.foto = foto;
        this.precio = precio;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getFoto(){
        return foto;
    }
    
     public double getPrecio(){
        return precio;
    }
    
    
}
    

