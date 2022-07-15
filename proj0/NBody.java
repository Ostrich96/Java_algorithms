public class NBody{
    public static double readRadius(String file){
        In in = new In(file);
        int nums = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String file){
        In in = new In(file);
        int nums = in.readInt();
        double radius = in.readDouble();
        Body[] result = new Body [nums];
        for(int i = 0; i <nums; ++i){
            double mass = in.readDouble();
            double xxpos = in.readDouble();
            double yypos = in.readDouble();
            double xxvel = in.readDouble();
            double yyvel = in.readDouble();
            String img = in.readString();
            result[i] = new Body(mass,xxpos,yypos,xxvel,yyvel,img);
        }
        return result;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodys = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();

        double time = 0;
        while(time<T){
            double[] xForces = new double[1024];
            double[] yForces = new double[1024];
            for(int i = 0; i <bodys.length;++i){
                xForces[i] = bodys[i].calcNetForceExertedByX(bodys);
                yForces[i] = bodys[i].calcNetForceExertedByY(bodys);
            }
            for(int i = 0; i < bodys.length;++i){
                bodys[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i = 0 ; i <bodys.length;++i){
                bodys[i].draw();
            }
            StdDraw.show();
            time+=dt;
        }        
    }
}