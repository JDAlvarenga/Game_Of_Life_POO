/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_paint;

/**
 *
 * @author jaiel
 */
public class Vector {
    double x;
    double y;
    
    public Vector(double x,double y){
        this.x=x;
        this.y=y;
    }
    
    public Vector plus(Vector vector){
        return new Vector(this.x+vector.x,this.y+vector.y);
    }
    
    public Vector minus(Vector vector){
        return new Vector(this.x-vector.x,this.y-vector.y);
    }
    
    public double length(){
        return Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y,2));
    }
    
    public Vector times(Vector value){
        return new Vector(this.x*value.x-this.y*value.y, this.x*value.y+this.y*value.x);
    }
    
    public Vector times(double value){
        return new Vector(this.x*value,this.y*value);
    }
    
    public Vector times(int value){
        return new Vector(this.x*value,this.y*value);
    }
}
