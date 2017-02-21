/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pak;

/**
 *
 * @author George
 */
public class degrecalc {
    private int pasd_deg, pasd_lngt2, cordX=0, cordY=0;
    private final int max=255, min=0, deg_max=180, deg_min=0;
    private double degre, degconst;
    public degrecalc () {} // конструктор без пар. препоръчително е да не се използва
    public degrecalc (int form_deg, int form_lngt2) throws Exception {
        //конст. с пар.
        pasd_deg = form_deg;
        pasd_lngt2 = form_lngt2;
        //извикване на методите които са ни нужни за полетата дег и корд.
        todegree();
        cord();
    }
    public void todegree () throws Exception{
        if (pasd_deg > max || pasd_deg < min) {
            //проверка на входа
            throw new outofboudsException("числото не е в позволената граница");
        }
        //операции
        degconst = (double)deg_max / (double)max;
        degre = (double)pasd_deg * degconst;
        //
        if ( (int)degre < deg_min || (int)degre > deg_max) {
            //проверка на изхода
            throw new outofboudsException("възникна грешка при смятане на градуса");
        }
    }
    public double getDegree () {
        // връща градус
        return degre;
    }
    public void cord () throws Exception {
        double x1 , y1;
        double beta,alpha;
        //необходимо е да се използват два различни метода зависимосто от дали е над 90 или под
        if ( degre <= 90 && degre >= 0) {
            alpha = (degre*Math.PI) / (double)180; //трябва да се превърнат в радиани за sin ф.
            beta = (double)90 - degre;
            beta = (beta*Math.PI) / (double)180; //трябва да се превърнат в радиани за sin ф.
            x1 = (double)pasd_lngt2 * Math.sin(alpha);
            y1 = (double)pasd_lngt2 * Math.sin(beta);
        }
        else if ( degre > 90 && degre <= 180) {
            alpha = deg_max - degre;
            beta = 90 - alpha;
            alpha = (alpha*Math.PI) / (double)180; //трябва да се превърнат в радиани за sin ф.
            beta = (beta*Math.PI) / (double)180; //трябва да се превърнат в радиани за sin ф.
            x1 = (double)pasd_lngt2 * Math.sin(alpha);
            y1 = (double)pasd_lngt2 * Math.sin(beta);
        }
        else {
            //в случей че ислезне от 0-180 границата
            throw new outofboudsException("възникна грешка при смятане на кординати");
        }   // да се добавявт още методи ако ще се работи с над 180 градуса
        //задавамеги на полетата
        cordX = (int)x1;
        cordY = (int)y1;
    }
    public int getX () {
        //връща х кордината
        return cordX;
    }
    public int getY () {
        //връща у кордината
        return cordY;
    }
}
