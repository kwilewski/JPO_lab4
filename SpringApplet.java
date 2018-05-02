import java.awt.*;
import java.util.*;
import javax.swing.JApplet;
import java.awt.geom.*;

public class SpringApplet extends JApplet {
    private SimEngine engine;
    private SimTask task;
    private Timer timer;

    public void utwierdzenie(Graphics g,Vector2D xz){       //rysowanie symbolu utwierdzenia
        Graphics2D g2= (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.draw(new Line2D.Double(xz.getWspx()-50,xz.getWspy(),xz.getWspx()+50,xz.getWspy()));
        for(int i=(int)xz.getWspx()-50;i<xz.getWspx()+50;i=i+10){
            g2.draw(new Line2D.Double(i,xz.getWspy(),i+10,xz.getWspy()-20));
        }
    }

    public void sprezyna(Graphics2D g, Vector2D xz, Vector2D x){        //rysowanie liny zastepujacej sprezyne
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.draw(new Line2D.Double(xz.getWspx(),xz.getWspy(),x.getWspx(),x.getWspy()));
    }

    public void init(){
        this.engine = new SimEngine(20, 2, 0.2, 200, 9.81, new Vector2D(600, 400), new Vector2D(0, 0), new Vector2D(400, 30));
        this.task = new SimTask(engine, this, 0.1);
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(task, 100, 10);
    }

    public void paint(Graphics g) {
        setSize(800, 600);
        setBackground(Color.white);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.clearRect(0, 0, getWidth(), getHeight());
        g2.setPaint(Color.black);
        Vector2D x = engine.getXm();
        Vector2D xz = engine.getXz();
        utwierdzenie(g2, xz);
        sprezyna(g2, xz, x);
        g2.setPaint(Color.black);
        g2.fillOval((int) x.getWspx() - 10, (int) x.getWspy() - 10, 20, 20);
        double x0=x.getWspx();
        double y0=x.getWspy();
        //rysowanie wektorow sil i predkosci
        g2.setColor(Color.red);
        engine.getV().rysowWekt(x0,y0,g2);
        g2.drawString("Wektor predkosci: "+engine.getV().info(1),10,(this.getHeight()-10));
        g2.setColor(Color.green);
        engine.getFg().rysowWekt(x0,y0,g2);
        g2.drawString("Wektor sily grawitacji: "+engine.getFg().info(1),10,(this.getHeight()-25));
        g2.setColor(Color.yellow);
        engine.getFw().rysowWekt(x0,y0,g2);
        g2.drawString("Wektor sily wiskotycznej: "+engine.getFw().info(1),10,(this.getHeight()-40));
        g2.setColor(Color.blue);
        engine.getFs().rysowWekt(x0,y0,g2);
        g2.drawString("Wektor sily sprezystosci: "+engine.getFs().info(1),10,(this.getHeight()-55));
        g2.setColor(Color.magenta);
        engine.getFwyp().rysowWekt(x0,y0,g2);
        g2.drawString("Wektor sily wypadkowej(w zaokragleniu): "+engine.getFw().info(1),10,(this.getHeight()-70));
    }
}