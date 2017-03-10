import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	public static void main(String[] args){
		double T = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		Planet[] planets = readPlanets(pfile);
		double radius = readRadius(pfile);
		int psize = planets.length;
	
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,  0,  "images/starfield.jpg");
		for (int i = 0; i<psize; i++) {
			planets[i].draw();
		}

		double time = 0;
		double[] xForces = new double[psize];
		double[] yForces = new double[psize];
		while (time<T) {
			for (int i = 0; i<psize; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i<psize; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0,  0,  "images/starfield.jpg");
			for (int i = 0; i<psize; i++) {
				planets[i].draw();
			}
			StdDraw.show(10);
			time += dt; 
		}
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);
		}
	}
	public static double readRadius(String fname) {
		double radius = 0;
		try {
			Scanner scan = new Scanner(new File(fname));
			scan.nextDouble();
			radius = scan.nextDouble();
			scan.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return radius; 
	}
	public static Planet[] readPlanets(String fname) {
		int size = 0;
		try {
			Scanner scan = new Scanner(new File(fname));
			size = scan.nextInt();
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Planet[] parray = new Planet[size];
		try {
			Scanner scan = new Scanner(new File(fname));
			scan.nextDouble();
			scan.nextDouble();
			for (int j = 0; j<size; j++) {
				parray[j] = new Planet(scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.next());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return parray;
	}
}
