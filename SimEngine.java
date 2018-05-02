public class SimEngine{
    private double m,k,c,l,g;
    private Vector2D xm,v,xz,fg,fw,fs,fwyp;

    /*
    fg - sila przyciagania ziemskiego
    fw - sila wiskotyczna
    fs - sila sprezystsoci
    fwyp - sila wypadkowa
    xm - polozenie masy
    xz - polozenie utwierdzenia
    v - wektor predkosci
    */

    public double getM(){
        return m;
    }
    public void setM(double m) {
        if (m > 0) {
            this.m = m;
        }
        else{
            this.m=1;
        }
    }
    public double getK(){
        return k;
    }
    public void setK(double k) {
        if (k > 0) {
            this.k = k;
        }
        else{
            this.k=1;
        }
    }
    public double getC(){
        return c;
    }
    public void setC(double c) {
        if (c > 0) {
            this.c = c;
        }
        else{
            this.c=0.5;
        }
    }
    public double getL(){
        return l;
    }
    public void setL(double l) {
        if (l > 0) {
            this.l = l;
        }
        else{
            this.l=100;
        }
    }
    public double getG(){
        return g;
    }
    public void setG(double g) {
        if (g > 0) {
            this.g = g;
        }
        else{
            this.g=1;
        }
    }

    public Vector2D getXm(){return xm;}
    public Vector2D getV(){return v;}
    public Vector2D getXz(){return xz;}
    public Vector2D getFg(){return fg;}
    public Vector2D getFw(){return fw;}
    public Vector2D getFs(){return fs;}
    public Vector2D getFwyp(){return fwyp;}

    public void setXm(Vector2D xm){this.xm=xm;}
    public void setV(Vector2D v){this.v=v;}
    public void setXz(Vector2D xz){this.xz=xz;}

    public SimEngine(double m,double k,double c,double l,double g,Vector2D xm, Vector2D v,Vector2D xz){         //konstruktor z parametrami
        setM(m);
        setK(k);
        setC(c);
        setL(l);
        setG(g);
        setXm(xm);
        setV(v);
        setXz(xz);
    }

    public void symul(double time){
        this.fg=new Vector2D(0,getM()*getG());
        this.fw=v.iloczyn(-getC());
        Vector2D sprezyna=new Vector2D(xm.getWspx()-xz.getWspx(),xm.getWspy()-xz.getWspy());
        double l=sprezyna.dlugosc();
        Vector2D kiersprez=sprezyna.normal();
        this.fs=kiersprez.iloczyn(getK()*(getL()-l));
        this.fwyp=fs.suma(fg).suma(fw);
        Vector2D a=fwyp.iloczyn(1/getM());
        Vector2D dv=a.iloczyn(time);
        v=v.suma(dv);
        Vector2D dx=v.iloczyn(time);
        xm=xm.suma(dx);
    }

    public void reset(){
        this.v=new Vector2D(0,0);
    }

    public static void main(String[] args){
    }
}