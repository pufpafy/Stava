/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pak;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import static java.lang.Integer.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author George
 */
// ver. 0.0.3
public class alpha1 extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form alpha1
     */
    //декларация на полета
    private JPanel p1, fields1, inlbp, fields2, fields_opt;
    private ramo1Paint pic;
    private JLabel begin, in_lb, opt_lb;
    private JLabel[] lngt_lb, grad_lb, input_lb;
    private JTextField[] intf, lngt_tf;
    private JButton check;
    private JScrollBar scrbar;

    public alpha1() {
        super("stava");
        //initComponents();
        try {
            int i = 0;
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container container = this.getContentPane();
            container.setLayout(new FlowLayout(FlowLayout.CENTER));
            container.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            //header
            p1 = new JPanel();
            begin = new JLabel("Изобразяване на ъгъл от става");
            p1.setPreferredSize(new Dimension(4000, 25));
            p1.add(begin);
            container.add(p1);
            //изображение
            pic = new ramo1Paint(3);
            pic.setPreferredSize(new Dimension(1000,1000));
            JScrollPane scrpic = new JScrollPane(pic);
            scrpic.setPreferredSize(new Dimension(600, 400));
            scrpic.setBorder(BorderFactory.createLineBorder(Color.black));
            container.add(scrpic);
            //етикет
            inlbp = new JPanel();
            inlbp.setPreferredSize(new Dimension(4000, 25));
            in_lb = new JLabel("Въведете число от 0-255 :");
            inlbp.add(in_lb);
            container.add(inlbp);
            //входни полета за градус
            fields1 = new JPanel();
            fields1.setLayout(new GridLayout(4, 2));
            input_lb = new JLabel[3];
            intf = new JTextField[3];
            grad_lb = new JLabel[3];
            for (i = 0; i < 3; i++) {
                input_lb[i] = new JLabel("Става " + (i + 1) + ": ");
                intf[i] = new JTextField("0");
                intf[i].setPreferredSize(new Dimension(100, 25));
                grad_lb[i] = new JLabel("Става " + (i + 1) + "има ъгъл от : 0.0");
            }
            check = new JButton("ok");
            check.addActionListener(this);
            this.getRootPane().setDefaultButton(check);
            //add
            for (i = 0; i < 3; i++) {
                fields1.add(input_lb[i]);
                fields1.add(intf[i]);
                fields1.add(grad_lb[i]);
            }
            fields1.add(new JLabel(" "));
            fields1.add(check);
            container.add(fields1);
            //за дължини
            fields2 = new JPanel();
            fields2.setLayout(new GridLayout(4, 2));
            fields_opt = new JPanel();
            fields_opt.setPreferredSize(new Dimension(4000, 25));
            opt_lb = new JLabel("Въведете дължина на (в pxl):");
            lngt_lb = new JLabel[4];
            lngt_tf = new JTextField[4];
            for (i = 0; i < 4; i++) {
                lngt_lb[i] = new JLabel("рамо " + (i + 1));
                lngt_tf[i] = new JTextField();
                lngt_tf[i].setText("100");
                lngt_tf[i].setPreferredSize(new Dimension(100, 25));
            }
            fields_opt.add(opt_lb);
            for (i = 0; i < 4; i++) {
                fields2.add(lngt_lb[i]);
                fields2.add(lngt_tf[i]);
            }
            container.add(fields_opt);
            container.add(fields2);
            //размер и видимост (финализиране)
            this.setSize(900, 900);
            this.setVisible(true);
            System.out.println("initialization completed successfully");
        }catch (Exception exc) {
            //изписва грешката в систем конзолата и в popup прозорец
            System.out.println("ERROR" + exc);
            JOptionPane.showMessageDialog(this, exc, "ERROR", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        final int max=255, min=0;
        try {
            Object source = e.getSource();
            if (source == check) {
                System.out.println("Action detected");
                //парсваме данните от TXTfields в контролиращия клас
                int i=0;
                for (i=0;i<3;i++) {
                    int pass;
                    pass = parseInt(intf[i].getText());
                    if (pass < min || pass > max) {
                        throw new outofboudsException("Въведеното число е извън границите :" + pass);
                    } 
                    pic.setFrom(i, pass);
                }
                for (i=0;i<4;i++) {
                    int pass;
                    pass = parseInt(lngt_tf[i].getText());
                    pic.setSticks(i, pass);
                }
                pic.calcs(); // извикваме магията !!!
                //връщаме градусите
                for (i=0;i < 3;i++) {
                    grad_lb[i].setText("Става " + (i + 1) + "има ъгъл от : " + pic.getDegree(i));
                }
                this.repaint();   //обновява картинката
            }
        }catch (Exception exc) {
            //изписва грешката в систем конзолата и в popup прозорец
            System.out.println("ERROR" + exc);
            JOptionPane.showMessageDialog(this, exc, "ERROR", JOptionPane.OK_OPTION);
        }
            
        
        
        
        
        /*
        int i=0;
        int[] degtake = null, lngt_of = null;
        try {
            Object source = e.getSource();
            if (source == check) {
                System.out.println("Action detected");
                //парсваме данните от TXTfields
                degtake = new int[3];
                for (i=0;i < 3 ;i++) {
                    degtake[i] = parseInt(intf[i].getText());
                }
                lngt_of = new int[4];
                for (i=0;i < 4 ;i++) {
                    lngt_of[i] = parseInt(lngt_tf[i].getText());
                }
                //създаваме обект за кал. 
                degrecalc[] ramo = new degrecalc[3] ;
                for (i=0;i < 3;i++) {
                    ramo[i] = new degrecalc( degtake[i] ,lngt_of[i+1]);
                }
                //калкулиране на ъгли
                for (i=0;i < 3;i++) {
                    grad_lb[i].setText("Става " + (i + 1) + "има ъгъл от : " + ramo[i].getDegree());
                }
                
                //сетваме променливите в изображението
                int[] X = new int[3];
                int[] Y = new int[3];
                double[] degs = new double[3];
                for (i=0;i < 3;i++) {
                    X[i] = ramo[i].getX();
                    Y[i] = ramo[i].getY();
                    degs[i] = ramo[i].getDegree();
                }
                pic.setSticks(lngt_of);
                pic.setEnd(X, Y);
                pic.setdegre(degs);
                
                this.repaint();     //попринцип става автоматично но за всеки случеи изискваме repaint
            }
        } catch (Exception exc) {
            //изписва грешката в систем конзолата и в popup прозорец
            System.out.println("ERROR" + exc);
            JOptionPane.showMessageDialog(this, exc, "ERROR", JOptionPane.OK_OPTION);
        }
        */
    }

    /*
     @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    /*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1093, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new alpha1().setVisible(true);
            }
        });
    }
    /*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
*/
}
