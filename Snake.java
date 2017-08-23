package some;

//Author : DHRUVIL SURESH PATEL

import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Snake extends JFrame implements KeyListener {

    int width, height, xpos, ypos, fx, fy , score = 0;
    char pressed = 'd' , prev = 'd';

    Snake(int h, int w) {
        width = w;
        height = h;
    }

    private Snake() {
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, d.width , d.height);
        
        g.setColor(Color.BLACK);
        g.drawString("#", fx, fy);
        
        if(pressed=='d'){
            if(prev=='a'){
                pressed = 'a';
                xpos -= 10;
            }
            else{
                xpos += 10;
            }
        }
        else if(pressed=='w'){
            if(prev=='s'){
                pressed = 's';
                ypos += 10;
            }
            else{
                ypos -= 10;
            }
        }
        else if(pressed=='a'){
            if(prev=='d'){
                pressed = 'd';
                xpos += 10;
            }
            else{
                xpos -= 10;
            }
        }
        else if(pressed=='s'){
            if(prev=='w'){
                pressed = 'w';
                ypos -= 10;
            }
            else{
                ypos += 10;
            }
        }
        
        if(xpos>=width || xpos<0 || ypos<0 || ypos>=height){
            xpos = width/2;
            ypos = height/2;
            pressed = 't';
            JOptionPane.showMessageDialog(null, "Your Score is " + score);
            System.exit(0);
        }
        g.drawString("@", xpos, ypos);
        if(xpos==fx && ypos==fy){
            score++;
            makeFood();
            g.drawString("#", fx, fy);
        }
            
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        prev = pressed;
    }
    
    int random(int i , int j){
        Random r = new Random();
        int num = r.nextInt((j - i) + 1) + i;
        return num;
    }

    void makeFood() {
        int x = random(0 , height/10) * 10, y = random(0 , width/10) * 10;
        while (x == xpos || y == ypos) {
            x = random(0 , height/10) * 10;
            y = random(0 , width/10) * 10;
        }
       
        fx = x;
        fy = y;
    }

    void initialize() {
        xpos = 0;
        ypos = 0;
        fx = 2;
        fy = 2;
    }

    public static void main(String[] args) {
        String hei = JOptionPane.showInputDialog("Enter the height of board you need: ") , wid = JOptionPane.showInputDialog("Enter the width of board you need: ");
        Snake f = new Snake(Integer.parseInt(hei), Integer.parseInt(wid));
        f.setSize(f.height, f.width);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(f);
        f.initialize();
        f.run();
    }
    
    void run(){
        makeFood();
        while(true){
            repaint();
        }
    }

    public void keyTyped(KeyEvent e) {
        pressed = e.getKeyChar();
    }

    public void keyPressed(KeyEvent e) { 
    }

    public void keyReleased(KeyEvent e) {
    }

}
