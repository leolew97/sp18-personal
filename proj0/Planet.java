public class Planet {

    /* class variables should be made "public" not just declared as is, ie. "public double xxPos" and not "double xxPos." */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /* good practice to declare any constants as a "static final" variable in our class. That is, "final" indicates no changes can be made to this variable after its initial declaration and initialization */
    /* Note: java supports scientific notation, so the below two declarations and initializations of g are valid. */
/*    static final double g = 0.0000000000667;*/
    static final double g = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    /** gives the square root of a number. Since this is only going to be used in the class, we should make the method private. */
    private static double square(double number){
        double t;
        double squareroot = number / 2;
        do
        {
            t = squareroot;
            squareroot = (t + (number / t)) / 2;
        }
        while ((t - squareroot) != 0);
        return squareroot;
    }

    public double calcDistance(Planet p){
        double dx = (this.xxPos - p.xxPos);
        dx = dx*dx;

        double dy = (this.yyPos - p.yyPos);
        dy = dy*dy;

        double root = square(dx + dy);

        return root;
    }

    /* Note: did not distinguish b/t pos and neg force exerted */
    public double calcForceExertedBy(Planet p){
        double r2 = this.calcDistance(p)*this.calcDistance(p);
        double F = g*(this.mass)*(p.mass)/r2;
        return F;
    }

    public double calcForceExertedByX(Planet p){
        double dx = (this.xxPos - p.xxPos);

        if (dx < 0) {
            return -(this.calcForceExertedBy(p) * dx) / this.calcDistance(p);
        }
        return (this.calcForceExertedBy(p)*dx)/this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        double dy = (this.yyPos - p.yyPos);
        if (dy < 0){
            return -(this.calcForceExertedBy(p)*dy)/this.calcDistance(p);
        }
        return (this.calcForceExertedBy(p)*dy)/this.calcDistance(p);
    }

    /* Note: we can't compile unless we initialize a declared variable. We cannot initialize if we only have "double sum;" we need "=0." */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double sum = 0;
        for (Planet p : allPlanets) {
            sum += this.calcForceExertedByX(p);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double sum = 0;
        for (Planet p : allPlanets) {
            sum += this.calcForceExertedByY(p);
        }
        return sum;
    }

    /* Static methods that don't return a value are interpreted as constructors. If we return void (which is still nothing), a constructor turns into a static method. */

    /** We are updating the instance's variables, and not creating new variables. Also, remember to declare the variables we take into a method. */
    public void update(double dt, double fX, double fY) {
        double accelX = fX/this.mass;
        double accelY = fY/this.mass;
        this.xxVel = this.xxVel + dt*accelX;
        this.yyVel = this.yyVel + dt*accelY;
        this.xxPos = this.xxPos + dt*xxVel;
        this.yyPos = this.yyPos + dt*yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
    }
}

