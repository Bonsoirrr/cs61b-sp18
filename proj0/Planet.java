public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

  public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }

    public Planet(Planet p){
    this.xxPos = p.xxPos;
    this.yyPos = p.yyPos;
    this.xxVel = p.xxVel;
    this.yyVel = p.yyVel;
    this.mass = p.mass;
    this.imgFileName = p.imgFileName;
    }

    /**Calculate the distance between two planets*/
    public double calcDistance(Planet p){
      double dx = this.xxPos - p.xxPos;
      double dy = this.yyPos - p.yyPos;
      double distance = Math.sqrt(dx * dx + dy * dy);
      return distance;
    }

    /**Calculate the force exerted on this planet*/
    public double calcForceExertedBy(Planet p){
      double R = this.calcDistance(p);
      double F = (G * this.mass * p.mass)/(R * R);
      return F;
    }

    /**calculate the force on x and y direction*/
    public double calcForceExertedByX(Planet p){
      double R = this.calcDistance(p);
      double F = this.calcForceExertedBy(p);
      double dx = p.xxPos - this.xxPos;
      double xF = F * dx / R;
    return xF;
    }

    public double calcForceExertedByY(Planet p){
        double R = this.calcDistance(p);
      double F = this.calcForceExertedBy(p);
      double dy = p.yyPos - this.yyPos;
      double yF = F * dy / R;
    return yF;
  }

  /**calculate the net force on x and y direction*/
  public double calcNetForceExertedByX(Planet[] pArr){
    int i = 0;
    double xNetF = 0;
    while (i < pArr.length){
      if (this.equals(pArr[i])){
        i++;
        continue;
      }
      xNetF = xNetF + this.calcForceExertedByX(pArr[i]);
      i++;
    }
    return xNetF;
  }


  public double calcNetForceExertedByY(Planet[] pArr){
    int i = 0;
    double yNetF = 0;
    while (i < pArr.length){
      if (this.equals(pArr[i])){
        i++;
        continue;
      }
      yNetF = yNetF + this.calcForceExertedByY(pArr[i]);
      i++;
    }
    return yNetF;
  }

  /**calculate the planet's new velocity and new position*/
  public void update(double dt, double fX, double fY){
    double newaX = fX/this.mass;
    double newaY = fY/this.mass;
    this.xxVel = this.xxVel + dt * newaX;
    this.yyVel = this.yyVel + dt * newaY;
    this.xxPos = this.xxPos + this.xxVel * dt;
    this.yyPos = this.yyPos + this.yyVel * dt; 
  }

  public void draw(){
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }

}

