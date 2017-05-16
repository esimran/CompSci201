import java.util.PriorityQueue;

/**
 * Interface that all compression suites must implement. That is they must be
 * able to compress a file and also reverse/decompress that process.
 * 
 * @author Brian Lavallee
 * @since 5 November 2015
 * @author Owen Atrachan
 * @since December 1, 2016
 */
public class HuffProcessor {

	public static final int BITS_PER_WORD = 8;
	public static final int BITS_PER_INT = 32;
	public static final int ALPH_SIZE = (1 << BITS_PER_WORD); // or 256
	public static final int PSEUDO_EOF = ALPH_SIZE;
	public static final int HUFF_NUMBER = 0xface8200;
	public static final int HUFF_TREE = HUFF_NUMBER | 1;
	public static final int HUFF_COUNTS = HUFF_NUMBER | 2;

	public enum Header {
		TREE_HEADER, COUNT_HEADER
	};

	public Header myHeader = Header.TREE_HEADER;

	/**
	 * Compresses a file. Process must be reversible and loss-less.
	 *
	 * @param in
	 *            Buffered bit stream of the file to be compressed.
	 * @param out
	 *            Buffered bit stream writing to the output file.
	 */
	public void compress(BitInputStream in, BitOutputStream out) {
		if(in.readBits(1) == -1)
			throw new HuffException("Empty input!");
		in.reset();
		int[] counts = readForCounts(in); // creates frequency array
		HuffNode root = makeTreeFromCounts(counts); // creates tree with root
		String[] codings = makeCodingsFromTree(root, "", new String[257]); // create
																			// array
																			// where
																			// index
																			// is
																			// value
																			// and
																			// String
																			// is
																			// path
		out.writeBits(BITS_PER_INT, HUFF_TREE); // beginning code
		writeHeader(root, out); // writing header
		in.reset(); // reset to write compression after reading stream for
					// counts
		writeCompressedBits(in, codings, out);
	}

	private int[] readForCounts(BitInputStream in) {
		int[] ans = new int[257]; // 257 is for PSEUDO_EOF
		while (true) { // reads each bit value and adds one to frequency count
			int val = in.readBits(BITS_PER_WORD);
			if (val == -1)
				break;
			ans[val]++;
		}
		ans[256] = 1;
		return ans;
	}

	private HuffNode makeTreeFromCounts(int[] counts) {
		PriorityQueue<HuffNode> pq = new PriorityQueue<HuffNode>();
		int AlphFreq = 0;
		for (int i = 0; i < counts.length; i++) { // adds all letters than are
													// present as node with
													// frequency as weight
			if (counts[i] != 0){
				pq.add(new HuffNode(i, counts[i]));
				AlphFreq++;
			}
		}
//		System.out.println("AlphFreq: " + AlphFreq);
		while (pq.size() > 1) {
			HuffNode left = pq.remove();
			HuffNode right = pq.remove();
			HuffNode t = new HuffNode(-1, left.weight() + right.weight(), left, right); // creates
																						// node
																						// from
																						// left
																						// and
																						// right
			pq.add(t);
		}
		return pq.remove(); // root
	}

	private String[] makeCodingsFromTree(HuffNode node, String current, String[] codings) {
		if ((node.left() == null) && (node.right() == null)) { // base case if
																// leaf, then
																// this is
																// correct path
			codings[node.value()] = current;
		}
		// search left and right, add 0 to path if left, 1 if right, continue
		// until you get all leaves
		if (node.left() != null) {
			codings = makeCodingsFromTree(node.left(), current + "0", codings);
		}
		if (node.right() != null) {
			codings = makeCodingsFromTree(node.right(), current + "1", codings);
		}
		return codings;
	}

	private void writeHeader(HuffNode node, BitOutputStream out) {
		if (node.value() != -1) { // if its a leaf, then add signal for leaf and
									// write 9 bit value
			out.writeBits(1, 1);
			out.writeBits(9, node.value());
		} else
			out.writeBits(1, 0);
		// go let and right if its an intermediary node
		if (node.left() != null) {
			writeHeader(node.left(), out);
		}
		if (node.right() != null) {
			writeHeader(node.right(), out);
		}
	}

	private void writeCompressedBits(BitInputStream in, String[] codings, BitOutputStream out) {
		// read 8 bits at a time and replace them with encoded values
		int val = in.readBits(8);
		String encode;
		while (val != -1) {
			encode = codings[val];
			out.writeBits(encode.length(), Integer.parseInt(encode, 2));
			val = in.readBits(8);
		}
		// add PSEUDO_EOF
		encode = codings[256];
		out.writeBits(encode.length(), Integer.parseInt(encode, 2));
	}

	/**
	 * Decompresses a file. Output file must be identical bit-by-bit to the
	 * original.
	 *
	 * @param in
	 *            Buffered bit stream of the file to be decompressed.
	 * @param out
	 *            Buffered bit stream writing to the output file.
	 */

	public void decompress(BitInputStream in, BitOutputStream out) {
		int val = in.readBits(BITS_PER_INT);
		if (!(val == HUFF_TREE || val == HUFF_COUNTS)) // checks for code that
														// says I can decompress
														// it
			throw new HuffException("Give valid input!");
		HuffNode root = readTreeHeader(in); // create tree
		readCompressedBits(root, in, out); // read the bits with new tree
	}

	private HuffNode readTreeHeader(BitInputStream in) {
		int val = in.readBits(1);
		if (val == 0) { // if intermediary node
			return new HuffNode(0, 0, readTreeHeader(in), readTreeHeader(in));
		} else // leaf node
			return new HuffNode(in.readBits(9), 1);
	}

	private void readCompressedBits(HuffNode root, BitInputStream in, BitOutputStream out) {
		HuffNode temp = root;
		while (true) {
			int val = in.readBits(1);
			// check which way down the tree to go for a specific bit
			if (val == 1) {
				temp = temp.right();
			} else
				temp = temp.left();
			if (temp.weight() == 1) { // if it is a character, otherwise, keep
										// searching
				int value = temp.value();
				if (value == PSEUDO_EOF) { // if End of File, break
					break;
				} else
					out.write(value); // found a letter, write the new letter in
										// 8 bit
				temp = root; // restart new letter at the beginning of the root
								// again
			}
		}
	}

	public void setHeader(Header header) {
		myHeader = header;
//		System.out.println("header set to " + myHeader);
	}
}
