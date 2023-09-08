import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {
	private HuffmanNode overallRootHuffmanNode;

	// Constructs a Huffman tree using the given array of frequencies where count[i] is the number of occurrences of the character with ASCII value i.

	public HuffmanTree(int[] count) {
		HuffmanNode first;
		HuffmanNode second;

		PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>();
		// Adding the values in the priority queue and sorting them according to the frequencies
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				q.add(new HuffmanNode(count[i], i));
			}
		}

		// Adding a new value psuedo off with frequency 1 and ascii of max 256 to determine the eof
		q.add(new HuffmanNode(1, 256));

		// Creating a tree by popping up the values until 1 node remains in the priority queue
		while (q.size() > 1) {
			first = q.peek();
			q.poll();
			second = q.peek();
			q.poll();
			overallRootHuffmanNode = new HuffmanNode(first.frequency + second.frequency);
			overallRootHuffmanNode.left = first;
			overallRootHuffmanNode.right = second;
			q.add(overallRootHuffmanNode);
		}

	}

	
	
	// Writes the current tree to the given output stream in standard format.
	void write(PrintStream output) {
		output = printTree(overallRootHuffmanNode, "", output);
		output.close();
	}

	
	
	// Helper method for the write method
	public PrintStream printTree(HuffmanNode n, String result, PrintStream output) {
		if (n == null) {
			return output;
		}
		if (n.left == null && n.right == null) {
			output.println(n.ascii);
			output.println(result);
			return output;
		}
		printTree(n.left, result + "1", output);
		return printTree(n.right, result + "0", output);

	}

	
	
	// Decoding the Huffman code
    //Constructs a Huffman tree from the Scanner. 
	// Assumes the Scanner contains a tree description in standard format.
	public HuffmanTree(Scanner input) {
		overallRootHuffmanNode = new HuffmanNode(0);
		while (input.hasNextLine()) {
			int ascii = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			createDecodedTree(overallRootHuffmanNode, ascii, code);
		}

	}

	
	
public void createDecodedTree(HuffmanNode root, int asciiCode, String code) {

		char[] bit = code.toCharArray();
		for (int i = 0; i < bit.length; i++) {
			if (bit[i] == '1') {
				if (root.left == null) {
					root.left = new HuffmanNode(-1);
				}
				root = root.left;
			} else if (bit[i] == '0') {
				if (root.right == null) {
					root.right = new HuffmanNode(-1);
				}
				root = root.right;
			}
		}
		root.ascii = asciiCode;
	}

	// Reads bits from the given input stream and writes the corresponding
	// characters to the output.
	// Stops reading when it encounters a character with value equal to eof.
	// This is a pseudo-eof character, so it should not be written to the output
	// file.
	// Assumes the input stream contains a legal encoding of characters for this
	// tree’s Huffman code.
	void decode(BitInputStream input, PrintStream output, int eof) {

		HuffmanNode root = overallRootHuffmanNode;
		while (root.ascii != eof) {
			int bite = input.readBit();
			if (bite == 1) {
				root = root.left;
			} else if (bite == 0) {
				root = root.right;
			}

			if (root.left == null && root.right == null && root.ascii != eof) {
				output.write(root.ascii);
				root = overallRootHuffmanNode;
			}

		}
	}

	// inner class
	private class HuffmanNode implements Comparable<HuffmanNode> {
		public int frequency;
		public int ascii;
		public HuffmanNode left;
		public HuffmanNode right;

		public HuffmanNode(int frequency, int ascii) {
			this.frequency = frequency;
			this.ascii = ascii;
		}

		public HuffmanNode(int frequency) {
			this.frequency = frequency;

		}

		@Override
		public String toString() {
			return "freq= " + frequency + "asci" + ascii;
		}

		@Override
		public int compareTo(HuffmanNode n) {
			// return<0 if the frequency of node < frequency if n
			if (this.frequency > n.frequency)
				return 1;
			// return 0 if frequency of node == frequency of n
			else if (this.frequency == n.frequency)
				return 0;
			// return>0 if frequency of node > frequency of n
			else
				return -1;

		}

	}
}
