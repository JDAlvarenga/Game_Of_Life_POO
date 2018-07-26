/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_paint;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author jaiel
 */
public class BitCanvas extends JPanel{
    int size;
    World world;
    
    public BitCanvas(World world, int size) {
        this.world=world;
        this.size=size;
        setPreferredSize(new Dimension(world.grid.width*size+1,world.grid.height*size+1));
        
        
        addMouseListener(new MouseAdapter() { 
          public void mousePressed(MouseEvent me) { 
            world.grid.set(new Vector(Math.ceil(me.getX()/size),Math.ceil(me.getY()/size)),world.currColor);
            repaint();

          }
          
        }); 
        
        addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent me){
              Vector v=new Vector(Math.ceil(me.getX()/size),Math.ceil(me.getY()/size));
              if(world.grid.isInside(v)){
                world.grid.set(v,world.currColor);
                repaint();
              }            
              
          }
        });
    }
    
    
    
    //-------------GUI----------------
    @Override
    public void paint(Graphics ctx){
        super.paint(ctx);
        
        drawMatrix(ctx);
        drawGrid(ctx);
    }
    
        public void drawMatrix(Graphics g){
        int currx=0, curry=0;
        for(int i=0;i<this.world.grid.width;i++){
            for(int j=0;j<this.world.grid.height;j++){
                currx = i*size;
                curry = j*size;

                    g.setColor((Color)this.world.grid.get(new Vector(i,j)));
                    g.fillRect(currx,curry,size,size);
                
            }
        }
    }
        public void drawGrid(Graphics g){
            int x=0;
            int y=0;
        
        g.setColor(Color.BLACK);
        for(int i = 0; i <= this.world.grid.height; i++){
            g.drawLine(x, y, this.world.grid.width*this.size, y);
            y+=size;
        }
        y = x;
        for(int i = 0; i <= this.world.grid.width; i++){
            g.drawLine(x, y, x, this.world.grid.height*this.size);
            x+=size;
        }
    }
    
}
