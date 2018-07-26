/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jaiel
 */
public class World extends JPanel{


Grid grid;  //Grid cantaining a Cell object on every position
BitCanvas canvas;
ColorSelection colorPallete;
Color currColor;
int indexEmptyColor;
static ArrayList<Color> COLORS;
int size;

JTextField filename;
public World(int w, int h, int size,int panelcols,ArrayList<Color> Colors){

    this.COLORS= Colors;
    
    this.grid=new Grid(w,h);

    indexEmptyColor=COLORS.indexOf(new Color(0,0,0,0));
    if(indexEmptyColor==-1) {COLORS.add(new Color(0,0,0,0)); indexEmptyColor=COLORS.size()-1;}
    this.grid.init(vec->COLORS.get(indexEmptyColor));
    this.currColor=COLORS.get(1);
    
    World world=this;




    //--------------SETTING UP PANELS-----------


    //Canvas Panel which locally handles the graphic representation
    canvas = new BitCanvas(this, size);
    super.add(canvas,BorderLayout.EAST);


    
    
    //Colors Panel allows selection of color to be used
    colorPallete=new ColorSelection(this,size,panelcols);
    super.add(colorPallete,BorderLayout.WEST);
    
    
    
    
    //Controls Panel: define buttons' actions and textfields
    JPanel controls= new JPanel();
    
    JButton clear= new JButton("Clear");
    clear.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
           world.clear();
            world.canvas.repaint();
        }
    });
    controls.add(clear);

    
    
    
    
    JButton fill= new JButton("Fill");
    fill.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            world.grid.init(v->world.currColor);
            world.repaint();
  
        }
    });
    controls.add(fill);
    
    
    

    JButton save= new JButton("Save");
    save.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            
            /////////////////
                FileWriter fichero = null;
                PrintWriter pw = null;
                try
                {
                    fichero = new FileWriter(world.getFileName());
                    pw = new PrintWriter(fichero);

                    
                        pw.write(world.toString());

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                   try {
                   // Nuevamente aprovechamos el finally para 
                   // asegurarnos que se cierra el fichero.
                   if (null != fichero)
                      fichero.close();
                   } catch (Exception e2) {
                      e2.printStackTrace();
                   }
                }
            //////////////////

  
        }
    });
    controls.add(save);
    

    
    JButton load= new JButton("Load");
    load.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            File archivo = null;
            FileReader fr = null;

            try {
                archivo = new File (world.getFileName());
                fr = new FileReader (archivo);
               
                Scanner car=null;

                String []rgba= new String [4];
                rgba[0]="";rgba[1]="";rgba[2]="";rgba[3]="";
                int i=0,x=0,y=0;

                car=new Scanner(archivo); 
                while(car.hasNextLine()){

                    char[] row=car.nextLine().toCharArray();
                    for(char noc:row){
                        if(noc=='['){
                        i=0;continue;                        
                        }
                        if(noc==','){
                            i++; continue;
                        }
                        if(noc>='0' && noc<='9'){
                            rgba[i]+=noc;
                            continue;
                        }
                        if(noc==']'){
                            world.grid.set(new Vector(x,y), new Color((Integer.parseInt(rgba[0])), (Integer.parseInt(rgba[1])), (Integer.parseInt(rgba[2])),(Integer.parseInt(rgba[3])) ));
                            rgba[0]="";rgba[1]="";rgba[2]="";rgba[3]="";
                            x++;
                            continue;                       
                        }
                            x=0;
                            y++;   
                    }
                }
   
               
            }
            catch(IOException exe){
                world.filename.setText("File not Found");
            }finally{
                try{                    
                    if( null != fr ){   
                     fr.close();     
                    }                  
                }catch (Exception e2){ 
                    e2.printStackTrace();
                }
            }
            world.canvas.repaint();
        }
        
        });

        controls.add(load);
        
        super.add(controls,BorderLayout.SOUTH);

    
    this.filename=new JTextField(20);
    this.filename.setText(this.getFileName());
    controls.add(filename);
    
    
    
    
    super.add(controls,BorderLayout.SOUTH);
    

}

    //clears the grid to all empty;
    public void clear(){
        this.grid.init(vec->COLORS.get(indexEmptyColor));
    }

    // return String representation
    public String toString(){
        String st="";
        for(int r=0;r<this.grid.height;r++){
            for(int c=0;c<this.grid.width;c++){
                Color color=((Color)this.grid.get(new Vector(c,r)));
                st+="["+color.getRed()+",";
                st+=color.getGreen()+",";
                st+=color.getBlue()+",";
                st+=color.getAlpha()+"]";
                
            }
            st+="\n";
        }
        st+="\n";
        return st;
    }
    
    public String getFileName(){
        String st=this.filename.getText();
        if(st.equals("")) st="DefaultFileName";
        if(!st.matches("(.*)[.]txt$"))
            st+=".txt";
        return st;
    }

}

