/**
 * Utility binary-tree (Huffman tree) node for Huffman coding. This is a simple,
 * standard binary-tree node implementing the comparable interface based on
 * weight.
 * 
 * @author Brian Lavallee
 */

public class HuffNode implements Comparable<HuffNode> {

	private int myValue, myWeight;
	private HuffNode myLeft, myRight;

	/**
	 * construct leaf node (null children)
	 * 
	 * @param value
	 *            is the value stored in the node (e.g., character)
	 * @param weight
	 *            is used for comparison (e.g., count of # occurrences)
	 */
	public HuffNode(int value, int weight) {
		this(value, weight, null, null);
	}

	/**
	 * construct internal node (with children)
	 * 
	 * @param value
	 *            is stored as value of node
	 * @param weight
	 *            is weight of node
	 * @param ltree
	 *            is left subtree
	 * @param rtree
	 *            is right subtree
	 */
	public HuffNode(int value, int weight, HuffNode left, HuffNode right) {
		myValue = value;
		myWeight = weight;
		myLeft = left;
		myRight = right;
	}

	/**
	 * Return value based on comparing this HuffNode to another.
	 * 
	 * @return -1 if this < o, +1 if this > o, and 0 if this == 0
	 */
	public int compareTo(HuffNode other) {
		return myWeight - other.myWeight;
	}

	// Accessor methods
	public int value() {
		return myValue;
	}

	public int weight() {
		return myWeight;
	}

	public HuffNode left() {
		return myLeft;
	}

	public HuffNode right() {
		return myRight;
	}

	public String toString() {
		return Character.toString((char)myValue);
	}
}