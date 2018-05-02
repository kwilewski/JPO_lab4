import java.util.TimerTask;

public class SimTask extends TimerTask {
    private SimEngine engine;
    private SpringApplet applet;
    private double time;

    public SimTask(SimEngine eng,SpringApplet app,double t){            //konstruktor
        this.engine=eng;
        this.applet=app;
        this.time=t;
    }

    public void run(){          //metoda tworzaca i odswierzajaca applet
        engine.symul(time);
        applet.repaint();
    }
    public static void main(String[] args){
    }
}