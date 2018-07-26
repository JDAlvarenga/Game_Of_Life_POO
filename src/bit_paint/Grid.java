/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_paint;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
/**
 *
 * @author jaiel
 */

public class Grid{
    Object[] space;
    int width;
    int height;
    
/*    public static final Vector[] DIRS ={
    new Vector(0,-1),
          new Vector(1,0),
        new Vector(1,1),
        new Vector(1,1),
        new Vector(0,1),
        new Vector(-1,1),
        new Vector(-1,0),
        new Vector(-1,-1)
    };
  */  
    public static HashMap<String,Vector> DIRS= new HashMap<String,Vector>();
    
    public Grid(int width,int height){
        this.space=new Object[width*height];
        this.width=width;
        this.height=height;
        
        DIRS.put("n", new Vector(0,-1));
        DIRS.put("ne", new Vector(1,-1));
        DIRS.put("e", new Vector(1,0));
        DIRS.put("se", new Vector(1,1));
        DIRS.put("s", new Vector(0,1));
        DIRS.put("sw", new Vector(-1,1));
        DIRS.put("w", new Vector(-1,0));
        DIRS.put("nw", new Vector(-1,-1));

    }
    
    public boolean isInside(Vector vector){
        return vector.x>=0 && vector.x<this.width &&
	vector.y>=0 && vector.y<this.height;
    }
    
    public Object get(Vector vector){
        return this.space[(int)vector.x + (int)vector.y * this.width];
        
    }
    
    public void set(Vector vector,Object value){
        this.space[(int)vector.x + (int)vector.y * this.width] = value;
    }
    
    public void forEach(BiConsumer<Object,Vector> f){
        for(int y=0;y<this.height;y++){
            for(int x=0;x<this.width;x++){ 
                f.accept(this.space[x+y*this.width],new Vector(x,y));
            }
        }
    }
    
    public void init(Function<Vector,Object> f){
        for(int y=0;y<this.height;y++){
            for(int x=0;x<this.width;x++){
                Vector vec=new Vector(x,y);
                this.set(vec,f.apply(vec));
            }
        }
    }
    
    
   
}
