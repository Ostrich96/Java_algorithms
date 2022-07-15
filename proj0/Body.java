public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67*Math.pow(10,-11); 

    public Body(double xP,double yP,double xV,double yV,double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;

        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double distance = Math.sqrt(Math.pow((b.xxPos-this.xxPos),2)+ Math.pow((b.yyPos - this.yyPos),2));
        return distance;
    }

    public double calcForceExertedBy(Body b){
        double force = G*mass*b.mass/Math.pow(calcDistance(b),2);
        return force;
    }

    public double calcForceExertedByX(Body b){
        double forceX = calcForceExertedBy(b)/calcDistance(b)*(b.xxPos - xxPos);
        return forceX;
    }

    public double calcForceExertedByY(Body b){
        double forceY = calcForceExertedBy(b)/calcDistance(b)*(b.yyPos - yyPos);
        return forceY;
    }

    public double calcNetForceExertedByX(Body[] bs){
        double netForceX = 0;
        for(int i = 0; i < bs.length;++i){
            if(!bs[i].equals(this)){
                netForceX+= calcForceExertedByX(bs[i]);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] bs){
        double netForceY = 0;
        for(int i = 0; i < bs.length;++i){
            if(!bs[i].equals(this)){
                netForceY+= calcForceExertedByY(bs[i]);
            }
        }
        return netForceY;
    }

    public void update(double sec,double xForce, double yForce){
        double accX = xForce/mass;
        double accY = yForce/mass;
        xxVel= xxVel+sec*accX;
        yyVel = yyVel+sec*accY;
        xxPos = xxPos+sec*xxVel;
        yyPos = yyPos+sec*yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}