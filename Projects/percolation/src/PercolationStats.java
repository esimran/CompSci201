import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Point;
import javax.swing.JOptionPane;

/**
 * Compute statistics on Percolation afte performing T independent experiments
 * on an N-by-N grid. Compute 95% confidence interval for the percolation
 * threshold, and mean and std. deviation Compute and print timing
 * 
 * @author Kevin Wayne
 * @author Jeff Forbes
 * @author Josh Hug
 */

public class PercolationStats {
	public static int RANDOM_SEED = 1234;
	public static Random ourRandom = new Random(RANDOM_SEED);
	private PercolationVisualizer vis;
	private PercolationUF perc;
	private double[] myData;

	// TODO Add methods as described in assignment writeup
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException("N or T less than 0");
		myData = new double[T];
		for (int i = 0; i < T; i++) {//creates new percolation opbject for every test
			perc = new PercolationUF(N);
			vis = new PercolationVisualizer(N, perc);
			vis.run();
			myData[i] = (double) perc.numberOfOpenSites()/(N*N);//records proportion that was open in indices corresponding to number
		}
	}

	public double mean() {//returns average
		double sum = 0;
		for(int i = 0; i<myData.length;i++)
			sum+=myData[i];
	return (double) sum/myData.length;
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		double sum = 0;
		double mean = mean();
		for(int i = 0; i<myData.length;i++)
			sum+=(myData[i]-mean)*(myData[i]-mean);
		return (double) Math.sqrt(sum/(myData.length-1));
	}

	public double confidenceLow() {
		// low  endpoint of 95% confidence interval
		return (double) mean()-(1.96*stddev()/Math.sqrt(myData.length));
	}

	public double confidenceHigh() {
		// high endpoint of 95% confidence interval
		return (double) mean()+(1.96*stddev()/Math.sqrt(myData.length));
	}

	public static void main(String[] args) { // print out values for testing &  analysis
//		PercolationStats ilovecompsci = new PercolationStats(20, 4);
//		System.out.println("Times: " + ilovecompsci.myData.length);
//		System.out.println("Mean: " + ilovecompsci.mean());
//		System.out.println("Standard Deviation: " + ilovecompsci.stddev());
//		System.out.println("Confidence Interval: (" + ilovecompsci.confidenceLow() + ", " + ilovecompsci.confidenceHigh() + ")");
	}
}
