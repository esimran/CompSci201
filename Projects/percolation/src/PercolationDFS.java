import java.util.Arrays;

/**
 * Simulate percolation thresholds for a grid-base system using depth-first-search,
 * aka 'flood-fill' techniques for determining if the top of a grid is connected
 * to the bottom of a grid.
 * <P>
 * Modified from the COS 226 Princeton code for use at Duke. The modifications
 * consist of supporting the <code>IPercolate</code> interface, renaming methods
 * and fields to be more consistent with Java/Duke standards and rewriting code
 * to reflect the DFS/flood-fill techniques used in discussion at Duke.
 * <P>
 * @author Kevin Wayne, wayne@cs.princeton.edu
 * @author Owen Astrachan, ola@cs.duke.edu
 * @author Jeff Forbes, forbes@cs.duke.edu
 */


public class PercolationDFS implements IPercolate {
	// possible instance variable for storing grid state
	public int[][] myGrid;
	private int myOpenSites;
	private int mySize;

	/**
	 * Initialize a grid so that all cells are blocked.
	 * 
	 * @param n
	 *            is the size of the simulated (square) grid
	 */
	public PercolationDFS(int n) {
		// TODO complete constructor and add necessary instance variables
		if (n<=0){
			// out of bounds
			throw new IllegalArgumentException("Bad N value: " + n);
		}
		myOpenSites = 0;
		mySize = n;
		myGrid = new int[n][n];
		for (int[] row: myGrid) // blocks all the rows initially
			Arrays.fill(row, BLOCKED);
		
//		for (int i = 0; i < myGrid.length; i++)
//			for (int j=0; j < myGrid[i].length; j++)
//				myGrid[i][j] = BLOCKED;
	}

	public void open(int i, int j) {
		// TODO complete open
		if (i < 0 || i >= myGrid.length || j < 0 || j >= myGrid[0].length)
			// out of bounds
			throw new IndexOutOfBoundsException("Index " + i +"," +j+
					" is bad!");
		if (myGrid[i][j] != BLOCKED)
			return;
		myOpenSites++; //changes open counter
		myGrid[i][j] = OPEN;
		flush();//turns all full sites to open
		for(int cols = 0; cols<mySize; cols++) // runs recursive filling algorithm on top boxes
			if(myGrid[0][cols] == OPEN)
				dfs(0, cols);
		
	}

	
	public boolean isOpen(int i, int j) {
		// TODO complete isOpen
		if (i < 0 || i >= myGrid.length || j < 0 || j >= myGrid[0].length)
			// out of bounds
			throw new IndexOutOfBoundsException("Index " + i +"," +j+
					" is bad!");
		return myGrid[i][j] == OPEN; //checks if open
	}

	public boolean isFull(int i, int j) {
		if (i < 0 || i >= myGrid.length || j < 0 || j >= myGrid[0].length)
			// out of bounds
			throw new IndexOutOfBoundsException("Index " + i +"," +j+
					" is bad!");
		return myGrid[i][j] == FULL; //checks if box is full
	}

	public int numberOfOpenSites() {
		//return the number of calls to open new sites
		return myOpenSites;
	}

	public boolean percolates() {
		//run DFS to find all full sites

		for(int cols = 0; cols<mySize; cols++) //check if the bottom row is full, which is a sign of percolation
			if(myGrid[mySize-1][cols] == FULL)
				return true;
		return false;
	}

	/**
	 * Private helper method to mark all cells that are open and reachable from
	 * (row,col).
	 * 
	 * @param row
	 *            is the row coordinate of the cell being checked/marked
	 * @param col
	 *            is the col coordinate of the cell being checked/marked
	 */
	private void dfs(int row, int col) { // recursive filling algorithm
		if (row < 0 || row >= myGrid.length || col < 0 ||
				col >= myGrid.length)
			// out of bounds
			return;
		if (isFull(row, col) || !isOpen(row, col)) //turns full if the square is open
			return;
		myGrid[row][col] = FULL;
		//runs on surrounding squares if they are open
		dfs(row -1, col); // up
		dfs(row, col - 1); // left
		dfs(row, col + 1); // right
		dfs(row + 1, col); // down
	}
	
	public void flush(){//turns all full to open so that DFS runs properly
		for(int rows = 0; rows<this.mySize; rows++){
			for(int col = 0; col<this.mySize; col++){
				if(myGrid[rows][col] == FULL)
					myGrid[rows][col] = OPEN;
			}
		}
	}
}
