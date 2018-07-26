/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_paint;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author jaiel
 */
public class Bit_Paint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList panels= new ArrayList();
        
        //----------Feel free to play with these values :)
        int     cols=30,     //number of columns
                rows=25,     //number of rows
                size=17,    //size of square in pixels
                panelcols=2;
        
        
        //Original MSX Color Palette
        ArrayList<Color> msxColorPalette=new ArrayList();
        msxColorPalette.add(new Color(0,0,0,0));
        msxColorPalette.add(new Color(219,101,89));
        msxColorPalette.add(new Color(0,0,0,255));
        msxColorPalette.add(new Color(255,137,125));
        msxColorPalette.add(new Color(62,184,73));
        msxColorPalette.add(new Color(204,195,94));
        msxColorPalette.add(new Color(116,208,125));
        msxColorPalette.add(new Color(222,208,135));
        msxColorPalette.add(new Color(89,85,224));
        msxColorPalette.add(new Color(58,162,65));
        msxColorPalette.add(new Color(128,118,241));
        msxColorPalette.add(new Color(183,102,181));
        msxColorPalette.add(new Color(185,94,81));
        msxColorPalette.add(new Color(204,204,204));
        msxColorPalette.add(new Color(101,219,239));
        msxColorPalette.add(new Color(255,255,255));
        //----------Feel free to play with these values :)

        World world = new World(cols, rows, size,panelcols,msxColorPalette);
        panels.add(world);
        
        Window window = new Window("16 Bit Paint",Math.max(cols*size+panelcols*size+20,600),Math.max(rows*size+90,(int)Math.ceil(msxColorPalette.size()/panelcols)*size+90),panels);

    }
    
}
