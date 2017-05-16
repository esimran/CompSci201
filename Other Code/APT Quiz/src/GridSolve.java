
public class GridSolve {

	int myRow;
	int myCol;

	public int startingPoints(String[] grid) {
		myRow = grid.length;
		myCol = grid[0].length();
		int[][] myGrid = new int[myRow][myCol];
		for (int row = 0; row < myRow; row++) {
			for (int col = 0; col < myCol; col++) {
				if (grid[row].charAt(col) == 'x') {
					myGrid[row][col] = 1;
				} else if (grid[row].charAt(col) == '.') {
					myGrid[row][col] = 0;
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < myRow; i++) {
			if (validRoute(myGrid, i, 0)) {
				ans++;
			}
		}
		return ans;
	}

	private boolean validRoute(int[][] grid, int row, int col) {
		if (row < 0 || col < 0 || row >= myRow || col >= myCol) {
			return false;
		}
		if (grid[row][col] == 0) {
			return false;
		}
		if (col == myCol - 1) {
			return true;
		}
		grid[row][col] = 0;
		if (validRoute(grid, row - 1, col) || validRoute(grid, row + 1, col) || validRoute(grid, row, col - 1)
				|| validRoute(grid, row, col + 1)) {
			grid[row][col] = 1;
			return true;
		}
		return false;
	}
}