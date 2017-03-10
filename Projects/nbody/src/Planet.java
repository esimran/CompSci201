public class Planet {
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		myXPos = xP;
		myYPos = yP;
		myXVel = xV;
		myYVel = yV;
		myMass = m;
		myFileName = img;
	}
	public Planet(Planet p) {
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}
	public double dx(Planet p) {
		double dx = p.myXPos - myXPos;
		return dx;
	}
	public double dy(Planet p) {
		double dy = p.myYPos - myYPos;
		return dy;
	}
	public double calcDistance(Planet p) {
		double distance = Math.sqrt(this.dx(p)*this.dx(p) + this.dy(p)*this.dy(p));
		return distance;
	}
	public double calcForceExertedBy(Planet p) {
		double force = 6.67e-11*myMass*p.myMass/(this.calcDistance(p)*this.calcDistance(p)); 
		return force;
	}
	public double calcForceExertedByX(Planet p) {
		double forcex = this.calcForceExertedBy(p)*this.dx(p)/this.calcDistance(p);
		return forcex;
	}
	public double calcForceExertedByY(Planet p) {
		double forcey = this.calcForceExertedBy(p)*this.dy(p)/this.calcDistance(p);
		return forcey;
	}
	public double calcNetForceExertedByX(Planet[] parray) {
		double netforcex = 0;
		for (Planet p: parray) {
			if (! p.equals(this)) {
				netforcex += this.calcForceExertedByX(p);
			}
		}
		return netforcex;
	}
	public double calcNetForceExertedByY(Planet[] parray) {
		double netforcey = 0;
		for (Planet p: parray) {
			if (! p.equals(this)) {
				netforcey += this.calcForceExertedByY(p);
			}
		}
		return netforcey;
	}
	public void update(double seconds, double xforce, double yforce) {
		myXVel += seconds*xforce/myMass;
		myYVel += seconds*yforce/myMass;
		myXPos += seconds*myXVel;
		myYPos += seconds*myYVel;
	}
	public void draw() {
		StdDraw.picture(this.myXPos, this.myYPos, "images/" + this.myFileName);
	}
}
