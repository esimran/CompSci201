import java.awt.Point;
import java.util.Arrays;

/**
 * Simulate a system to see its Percolation Threshold, but use a UnionFind
 * implementation to determine whether simulation occurs. The main idea is that
 * initially all cells of a simulated grid are each part of their own set so
 * that there will be n^2 sets in an nxn simulated grid. Finding an open cell
 * will connect the cell being marked to its neighbors --- this means that the
 * set in which t
 * he open cell is 'found' will be unioned with the sets of each
 * neighboring cell. The union/find implementation supports the 'find' and
 * 'union' typical of UF algorithms.
 * <P>
 * 
 * @author Owen Astrachan
 * @author Jeff Forbes
 *
 */

public class PercolationUF implements IPercolate {
	private final int OUT_BOUNDS = -1;
	public int[][] myGrid;
	private int myOpenSites;
	private int mySize;
	private IUnionFind uf;
	private final int TOP;
	private final int BOT;

	/**
	 * Constructs a Percolation object for a nxn grid that that creates a
	 * IUnionFind object to determine whether cells are full
	 */
	public PercolationUF(int n) {
		if (n<=0){
			// out of bounds
			throw new IllegalArgumentException("Bad N value: "+ n);
		}
		myOpenSites = 0;
		mySize = n;
		myGrid = new int[n][n];
		for (int[] row : myGrid)
			Arrays.fill(row, BLOCKED);
		uf = new QuickUWPC((n * n) + 2);//creates source and sink square with extra two to make checking for percolation easy
		TOP = mySize * mySize; //index for top source 
		BOT = mySize * mySize + 1; //index for sink bottom
	}

	/**
	 * Return an index that uniquely identifies (row,col), typically an index
	 * based on row-major ordering of cells in a two-dimensional grid. However,
	 * if (row,col) is out-of-bounds, return OUT_BOUNDS.
	 */
	public int getIndex(int row, int col) {
		// TODO complete getIndex
		if (row >= mySize || col >= mySize || row < 0 || col < 0)
			return OUT_BOUNDS;
		return mySize * row + col;
	}

	public void open(int i, int j) {//opens a box
		if (myGrid[i][j] != BLOCKED)
			return;
		myOpenSites++;
		myGrid[i][j] = OPEN;
		connect(i, j);
	}

	public boolean isOpen(int i, int j) {
		// checks if box is open
		if (i < 0 || i >= myGrid.length || j < 0 || j >= myGrid[0].length)
			// out of bounds
			throw new IndexOutOfBoundsException("Index " + i + "," + j + " is bad!");
		return myGrid[i][j] == OPEN || myGrid[i][j] == FULL;
	}

	public boolean isFull(int i, int j) {
		// checks if box is full
		if (i < 0 || i >= myGrid.length || j < 0 || j >= myGrid.length)
			// out of bounds
			throw new IndexOutOfBoundsException("Index " + i + "," + j + " is bad!");
		return uf.connected(getIndex(i, j), TOP);
	}

	public int numberOfOpenSites() {
		//  return the number of calls to open new sites
		return myOpenSites;
	}

	public boolean percolates() {//checks if top and bottom source/sink square are connected
		if (uf.connected(TOP, BOT))
			return true;
		return false;
	}

	/**
	 * Connect new site (row, col) to all adjacent open sites
	 */
	private void connect(int i, int j) {
		if (i < 0 || i >= myGrid.length || j < 0 || j >= myGrid[0].length)
			// out of bounds
			throw new IndexOutOfBoundsException("Index " + i + "," + j + " is bad!");
		int current = getIndex(i, j);
		if (i == 0) {//connects all top squares to source
			uf.union(TOP, current);
			myGrid[i][j] = FULL;
		}
		if (i == mySize - 1) {//connects all bottom squares to sink
			uf.union(BOT, current);
		}
		//error checks and runs on surrounding squares to link them all together in union
		if (j - 1 >= 0 && isOpen(i, j - 1)) {
			uf.union(getIndex(i, j - 1), current);
		}
		if ((j + 1) < mySize && isOpen(i, j + 1)) {
			uf.union(getIndex(i, j + 1), current);
		}
		if (i + 1 < mySize && isOpen(i + 1, j)) {
			uf.union(getIndex(i + 1, j), current);
		}
		if (i - 1 >= 0 && isOpen(i - 1, j)) {
			uf.union(getIndex(i - 1, j), current);
		}
	}
}