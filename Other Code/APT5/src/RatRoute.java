import java.util.Arrays;

public class RatRoute {

	public int[][] myGrid;
	public int counter = 0;

	public int numRoutes(String[] enc) {
		myGrid = new int[enc.length][enc[0].length()];
		int ratx = 0;
		int raty = 0;
		int chx = 0;
		int chy = 0;
		for (int row = 0; row < myGrid.length; row++)
			for (int col = 0; col < myGrid[0].length; col++) {
				if (enc[row].charAt(col) == 'X')
					myGrid[row][col] = 1;
				if (enc[row].charAt(col) == 'R') {
					myGrid[row][col] = 2;
					ratx = row;
					raty = col;
				}
				if (enc[row].charAt(col) == 'C') {
					myGrid[row][col] = 2;
					chx = row;
					chy = col;
				}
			}
		numRoutes(ratx, raty, chx, chy);
		return counter;
	}

	public void numRoutes(int ratx, int raty, int chx, int chy) {
//		System.out.printf("%d %d :: %d %d\n", ratx, raty, chx, chy);
		if ((ratx == chx) && (raty == chy)) {
			counter += 1;
		}
		double currentdist = dist(ratx, raty, chx, chy);

		if (inBounds(ratx - 1, raty) && myGrid[ratx - 1][raty] != 1 && dist(ratx - 1, raty, chx, chy) <= currentdist)
			numRoutes(ratx - 1, raty, chx, chy);
		if (inBounds(ratx, raty - 1) && myGrid[ratx][raty - 1] != 1 && dist(ratx, raty - 1, chx, chy) <= currentdist)
			numRoutes(ratx, raty - 1, chx, chy);
		if (inBounds(ratx + 1, raty) && myGrid[ratx + 1][raty] != 1 && dist(ratx + 1, raty, chx, chy) <=currentdist)
			numRoutes(ratx + 1, raty, chx, chy);
		if (inBounds(ratx, raty + 1) && myGrid[ratx][raty + 1] != 1 && dist(ratx, raty + 1, chx, chy) <= currentdist)
			numRoutes(ratx, raty + 1, chx, chy);
	}

	public double dist(int x, int y, int wx, int wy) {
		return Math.sqrt((wx - x) * (wx - x) + (wy - y) * (wy - y));
	}

	public boolean inBounds(int x, int y) {
		if( (x >= 0) && (y >= 0) && (x < myGrid.length) && (y < myGrid[0].length))
			return true;
		return false;
	}

	public static void main(String[] args) {
		RatRoute test = new RatRoute();
		String[] enc={".X.....X", "XX..R.X.", ".....X..", "...X....", "X..XX...", "C.......", "X......X"};
		System.out.println(test.numRoutes(enc));
	}
}
