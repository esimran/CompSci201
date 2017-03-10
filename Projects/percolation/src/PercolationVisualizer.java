import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import java.awt.Point;

/**
 * Animates the results of opening sites in a percolation system
 * 
 * From Princeton COS 226, Kevin Wayne Modified by Owen Astrachan, January 2008
 * Modified by Jeff Forbes, October 2008
 */

public class PercolationVisualizer {
	public static int RANDOM_SEED = 1234;
	public static Random ourRandom = new Random(RANDOM_SEED);
	public final int msInSec = 1000;
	public final int HZ = 300;
	public final int DEFAULT_DELAY = msInSec / HZ; // in milliseconds

	private int mySize;
	public IPercolate myPerc;

	public PercolationVisualizer(int n, IPercolate perc) {
		mySize = n;
		myPerc = perc;
//		initDisplay();
	}

	/**
	 * Draws a square of color c at (row,col) on a N*N grid
	 */
	public void draw(int row, int col, Color c) {//drawing and fillingn squares
		StdDraw.setPenColor(c);
		StdDraw.filledSquare(col + .5, mySize - row - .5, .45);
	}

	private void initDisplay() {//start display
		// draw a black box
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledSquare(mySize / 2.0, mySize / 2.0, mySize / 2.0);
	}

	// ask the percolator what sites are open, blocked, or full and
	// draw accordingly
	private void drawGrid() {
		for (int row = 0; row < mySize; row++)
			for (int col = 0; col < mySize; col++) {
				if (myPerc.isFull(row, col))
					draw(row, col, Color.CYAN);
				else if (myPerc.isOpen(row, col))
					draw(row, col, Color.WHITE);
			}
	}

	public List<Point> getShuffledCells() {//gets random points to open in blocked grid
		ArrayList<Point> list = new ArrayList<Point>();
		for (int i = 0; i < mySize; i++)
			for (int j = 0; j < mySize; j++)
				list.add(new Point(i, j));
		Collections.shuffle(list, ourRandom);
		return list;
	}

	public void run() {
		// percolates
		// get random list of sites
		List<Point> sites = getShuffledCells();
		// draw percolation system
		for (Point cell : sites) {
			// repeatedly declare sites open, draw, & pause until the system
			myPerc.open(cell.y, cell.x);
//			drawGrid();
			// wait DEFAULT_DELAY milliseconds and then display
//			StdDraw.show(DEFAULT_DELAY);
			if (myPerc.percolates())
				break;
		}
//		drawGrid();
	}

	public static void main(String[] args) {
//		// Animate 20 times a second if possible
//		String input = "20"; // default
//		if (args.length == 1) // use command-line arguments for testing/grading
//			input = args[0];
//		else
//			input = JOptionPane.showInputDialog("Enter N", "20");
//		int N = Integer.parseInt(input); // N-by-N lattice
//
//		// set x- and y-scale
//		StdDraw.setXscale(0, N);
//		StdDraw.setYscale(0, N);
//
//		IPercolate perc = new PercolationUF(N);
//		// IPercolate perc = new PercolationUF(N);
//		PercolationVisualizer pv = new PercolationVisualizer(N, perc);
//		pv.run();
//
	}
}
