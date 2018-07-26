/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_paint;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
/**
 *
 * @author jaiel
 */

public class Window extends JFrame {
    public Window(String title,int w, int h, ArrayList<JPanel> panels){
        super(title);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        panels.forEach((panel)->{add(panel);});
        
        setSize(w,h);
        setVisible(true);
    }
}
