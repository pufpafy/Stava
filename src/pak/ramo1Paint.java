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
    private int h, w, count;
    private int[] sticks,addX,addY,from;
    private double[] degre;
    public ramo1Paint(int count) {
        // конструктор само с брояч задаващ подразбиращи се стоиности на някои променливи
        this.count = count;
        sticks = new int[count+1];
        addX = new int[count];
        addY = new int[count];
        from = new int[count];
        degre = new double[count];
        int i = 0;
        for (i=0;i < count;i++) {
            sticks[i] = 100;
            addX[i] = 0;
            addY[i] = 0;
            degre[i] = 0.0;
        }
    }
    public ramo1Paint (int count, int[] sticks_size, int[] X, int[] Y,double[] deg) {
        //конструктор с параметри (не се използва в вер. 0.0.1)
        this.count = count;
        sticks = new int[count+1];
        addX = new int[count];
        addY = new int[count];
        from = new int[count];
        degre = new double[count];
        int i = 0;
        for (i=0;i < count;i++) {
            sticks[i] = sticks_size[i];
            addX[i] = X[i];
            addY[i] = Y[i];
            degre[i] = deg[i];
        }
    }
    public void setSticks (int i,int lngt) {
        sticks[i] = lngt;
    }
    public void setFrom (int i,int degS) {
        from[i] = degS;
    }
    public void setDegre (int i,double deg) {
        degre[i] = deg;
    }
    public void setAdds (int i, int X, int Y) {
        addX[i] = X;
        addY[i] = Y;
    }
    public int getSticks (int i) {
        return sticks[i];
    }
    public double getDegree (int i) {
        return degre[i];
    }
    public void calcs () {
        try {
            int i = 0, med=0, tot=0;
            //масив от класове за калкулации
            degrecalc[] stava = new degrecalc[count];
            for (i=0;i < count;i++) {
                tot = tot + from[i];
                med = i*255 + tot;
                stava[i] = new degrecalc(med,sticks[i+1]);
            }
            //прехвърляме резултатите в променливите
            for (i=0;i < count;i++) {
                if (i==0) {
                    degre[i] = stava[i].getDegree() ;
                }else {
                    degre[i] = stava[i].getDegree() - stava[i-1].getDegree() - 180;
                }
                addX[i] = stava[i].getX();
                addY[i] = stava[i].getY();
            }
        }catch (Exception e) {
            System.out.println("ERROR in calcs: " + e);
        }
    } 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            int i;
            //calcs();
            //помощни променливи
            int[] endX = null, endY = null;
            i=0;
            endX = new int[count+1];
            endY = new int[count+1];
            //взимаме размери от панела
            h = super.getHeight();
            w = super.getWidth();
            //краищата на първото рамо което е стазионарно
            endX[i] = w/2;
            endY[i] = h - (sticks[i] + h/2) ;
            i=1;
            //изчертаваме рамо 1
            g.drawLine( (w/2 - 15) , h/2 , (w/2 + 15), h/2);
            g.drawLine( (w/2), h/2, endX[0], endY[0] );
            //краищата на следващите рамене
            int j=0;
            for (i=1; i < (count+1) ;i++) {
                    endX[i] = endX[i-1] - addX[j];
                    endY[i] = endY[i-1] + addY[j];
                
                j++;
            }
            //изчертаваме и останалите рамене
            for (i=0;i<count;i++) {
                g.drawLine(endX[i], endY[i], endX[i+1], endY[i+1]);
                g.fillOval( (endX[i] - 5), (endY[i] - 5) , 10, 10);
            }
            this.repaint(); //обновява картинката
        }catch (Exception e) {
            System.out.println("ERROR in paint: " + e);
        }
    }
}
