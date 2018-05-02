import java.awt.*;
import java.awt.geom.Line2D;

public class Vector2D {
    public double wspx, wspy;

    public double getWspx(){
        return wspx;
    }

    public void setWspx(double wspx){
        this.wspx=wspx;
    }

    public double getWspy(){
        return wspy;
    }
    public void setWspy(double wspy){
        this.wspy=wspy;
    }

    public Vector2D(){          //konstruktor domyslny
        setWspx(0);
        setWspy(0);
    }

    public Vector2D(double x, double y){            //konstruktor z parametrami
        setWspx(x);
        setWspy(y);
    }

    public Vector2D suma(Vector2D x){           //metoda zwracająca sume wektora ktorego zostala wywolana i podanego za parametr
        double a=getWspx()+x.getWspx();
        double b=getWspy()+x.getWspy();
        return new Vector2D(a,b);
    }
    public Vector2D roznica(Vector2D x){            //metoda zwracajaca roznice
        double a=getWspx()-x.getWspx();
        double b=getWspy()-x.getWspy();
        return new Vector2D(a,b);
    }
    public Vector2D iloczyn(double n){          //metoda zwracajaca iloczyn
        double a=n*getWspx();
        double b=n*getWspy();
        return new Vector2D(a,b);
    }

    public double dlugosc(){            //metoda zwracajaca dlugosc
        return Math.sqrt(getWspx()*getWspx()+getWspy()*getWspy());
    }

    public Vector2D normal(){           //metoda zwracajaca wektor znormalizowany
        double l=dlugosc();
        return iloczyn(1/l);
    }

    public void info(){
        System.out.println("x: "+getWspx());
        System.out.println("y: "+getWspy());
    }

    public String info(int a){          //metoda wyswietlajaca info o wspolrzednych wektorow na applecie
        return ("x= "+(int)getWspx()+" y= "+(int)getWspy());
    }


    public void rysowWekt(double x,double y,Graphics g) {           //rysowanie wektorow
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(new Line2D.Double(x,y,x+getWspx(),y+getWspy()));
        Vector2D normalny = normal();
        double a = x + getWspx() - 6 * normalny.getWspx();
        double b = y + getWspy() - 6 * normalny.getWspy();
        Vector2D obrot = new Vector2D();
        obrot.setWspx(-normalny.getWspy());
        obrot.setWspy(normalny.getWspx());
        int x1 = (int) Math.round(a + 4 * obrot.getWspx());
        int x2 = (int) Math.round(a - 4 * obrot.getWspx());
        int y1 = (int) Math.round(b + 4 * obrot.getWspy());
        int y2 = (int) Math.round(b - 4 * obrot.getWspy());
        int x3 = (int) Math.round(x + getWspx());
        int y3 = (int) Math.round(y + getWspy());
        int xpoints[]={x1,x2,x3};
        int ypoints[]={y1,y2,y3};
        g2.fillPolygon(xpoints, ypoints, 3);
    }

    public static void main (String[] args){
    }

}
