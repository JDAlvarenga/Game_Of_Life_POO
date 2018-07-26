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
public class ColorSelection extends JPanel{
    int size;
    World world;
    int cols;
    
    public ColorSelection(World world, int size,int cols) {
        this.world=world;
        this.size=size;
        this.cols=cols;
        
        while(world.COLORS.size()%cols!=0){
            world.COLORS.add(world.COLORS.get(world.indexEmptyColor));
        }
                
        setPreferredSize(new Dimension(cols*size+1,(int)Math.ceil(world.COLORS.size()/cols)*size+1));
        
        
        addMouseListener(new MouseAdapter() { 
          public void mousePressed(MouseEvent me) {
                try{
              world.currColor=world.COLORS.get((int)Math.floor(me.getX()/size)+(int)Math.floor(me.getY()/size)*cols);
                }
                catch(IndexOutOfBoundsException e){
                    world.currColor=world.COLORS.get(world.indexEmptyColor);
                }
                repaint();

          }
          
        }); 
        
    }
    
    
    
    //-------------GUI----------------
    @Override
    public void paint(Graphics ctx){
        super.paint(ctx);
        
        drawMatrix(ctx);
        drawGrid(ctx);
        highlightSelectedColor(ctx);
    }
    
        public void drawMatrix(Graphics g){
            world.COLORS.forEach((color)->{

                g.setColor(color);
                int i=this.world.COLORS.indexOf(color);
                g.fillRect((i%this.cols)*size,(int)Math.floor(i/this.cols*size),size,size);
            });

        }
        public void drawGrid(Graphics g){
            int x=0;
            int y=0;
        
            g.setColor(Color.BLACK);
            for(int i = 0; i <= Math.ceil(this.world.COLORS.size()/this.cols); i++){
                g.drawLine(x, y, this.cols*this.size, y);
                y+=size;
            }
            y = x;
            for(int i = 0; i <= this.cols; i++){
                g.drawLine(x, y, x, (int)Math.ceil(this.world.COLORS.size()/this.cols)*this.size);
                x+=size;
            }
        }
        
        public void highlightSelectedColor(Graphics g){
            g.setColor(Color.BLACK);
            int i=this.world.COLORS.indexOf(this.world.currColor);
            g.drawRect((i%this.cols)*size+1,(int)Math.floor(i/this.cols)*size+1,size-2,size-2);
        }
    
}
