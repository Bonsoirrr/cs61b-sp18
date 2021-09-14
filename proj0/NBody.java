public class NBody{
  /**Given a file name, it should return a double corresponding 
   * to the radius of the universe in that file*/
  public static double readRadius(String Filename){
    In in = new In(Filename);
    int N = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  /**Given a file name, it should return an array of Planets 
   * corresponding to the planets in the file*/
  public static Planet[] readPlanets(String Filename){
    In in = new In(Filename);
    int N = in.readInt();
    Planet[] pArr = new Planet[N];
    double radius = in.readDouble();
    int i = 0;
    while (i < N){
      double xP = in.readDouble();
      double yP = in.readDouble();
      double xV = in.readDouble();
      double yV = in.readDouble();
      double mass = in.readDouble();
      String img = in.readString();
      pArr[i] = new Planet(xP, yP, xV, yV, mass, img);
      i++;
    }
    return pArr;
  }

  public static void main(String[] args){
    /**Collecting all needed inputs*/
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = NBody.readRadius(filename);
    Planet[] pArr = NBody.readPlanets(filename);
    int N = pArr.length;

 /**drawing the background*/
    StdDraw.setScale(-radius, radius);
    String bg = "images/starfield.jpg";
    StdDraw.picture(0, 0, bg, radius * 2, radius * 2);

    /**draw all the planets*/
    for (int i = 0; i < N; i++){
      pArr[i].draw();
    }
    
    StdDraw.enableDoubleBuffering();

  /**set a time variable*/
    double t = 0;
    while (t < T){
    double[] xForces = new double[N];
    double[] yForces = new double[N];
    for (int i = 0; i < N; i++){
      xForces[i] = pArr[i].calcNetForceExertedByX(pArr);
      yForces[i] = pArr[i].calcNetForceExertedByY(pArr);
    }

    for (int i = 0; i < N; i++){
      pArr[i].update(t, xForces[i], yForces[i]);
    }

   StdDraw.picture(0, 0, bg, radius * 2, radius * 2);

    /**draw all the planets*/
    for (int i = 0; i < N; i++){
      pArr[i].draw();
    }

  StdDraw.show();

  StdDraw.pause(10);
  t = t + dt;
  }
  StdOut.printf("%d\n", pArr.length);
StdOut.printf("%.2e\n", radius);
for (int i = 0; i < pArr.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  pArr[i].xxPos, pArr[i].yyPos, pArr[i].xxVel,
                  pArr[i].yyVel, pArr[i].mass, pArr[i].imgFileName);
}
}