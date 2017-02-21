/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pak;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author George
 */
public class ramo1Paint extends JPanel{
    private int stick1, stick2, h, w, s2x2, s2y2 ;
    private double degre;
    public ramo1Paint() {
        // конструктор без парам. задаващ подразбиращи се стоиности на някои променливи
        stick1 = 100;
        stick2 = 100;
    }
    public ramo1Paint (int stick1,int stick2) {
        //конструктор с параметри (не се използва в вер. 0.0.1)
        this.stick1 = stick1;
        this.stick2 = stick2;
        h = super.getHeight();
        w = super.getWidth();
    }
    public void setStick1 (int x) {
        stick1 = x;
    }
    public void setStick2 (int x) {
        stick2 = x;
    }
    public void setStick2x2 (int x) {
        s2x2 = x;
    }public void setStick2y2 (int x) {
        s2y2 = x;
    }
    public void setdegre (double a) {
        degre = a;
    }
    public int getStick1 () {
        return stick1;
    }
    public int getStick2 () {
        return stick2;
    }
    public int getStick2x2 () {
        return s2x2;
    }
    public int getStick2y2 () {
        return s2y2;
    }
    public double getdegre() {
        return degre;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        h = super.getHeight();
        w = super.getWidth();
        int s1y2 = h - (stick1 + 10) ; 
        g.drawLine( (w/2 - 15) , (h-10) , (w/2 + 15), (h-10) );
        g.drawLine( (w/2), (h-10), (w/2), s1y2 );
        g.fillOval( (w/2 - 5), (s1y2 - 5) , 10, 10);
        if (degre <= 90) {
            g.drawLine( (w/2), s1y2, (w/2 - s2x2), (s1y2 + s2y2) );
        }
        if (degre > 90) {
            g.drawLine( (w/2), s1y2, (w/2 - s2x2), (s1y2 - s2y2) );
        }
    }
}
