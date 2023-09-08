import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree2 {
	private HuffmanNode overallRootHuffmanNode;
    
	// Constructs a Huffman tree using the given array of frequencies where count[i] is the number of occurrences of the character with ASCII value i.
	public HuffmanTree2(int[] count) {
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
	
	//Constructor
	//Constructs a Huffman tree from the given input stream.  Assumes that the standard bit representation has been used for the tree.
	public HuffmanTree2(BitInputStream input) {
		int r;
		input.readBit();
		HuffmanNode root = new HuffmanNode(-1, null);
		overallRootHuffmanNode = root;
		while (root !=null ) {
			r=input.readBit();
			if(r==1) {
				if(root.left==null)
				{
					root.left=new HuffmanNode(-1, root);
					root=root.left;
				}
				else if(root.right==null) {
					root.right=new HuffmanNode(-1, root);
					root=root.right;
				}
				
			}
			if(r==0) {
				if(root.left==null) {
				root.left=new HuffmanNode(0, root);
				root.left.ascii=read9(input);
				}
				else if(root.right==null) {
					root.right=new HuffmanNode(0, root);
					root.right.ascii=read9(input);
					root=checkRoot(root);
				}
									
			}
			
			if(r==-1) {
				continue;
			}
		}
	}
	
	//recursion helper method for reconstructing the tree 
	public HuffmanNode checkRoot(HuffmanNode root) {
		if((root.left == null )||(root.right == null)) {
			return root;
		}
		else if(root.left != null || root.right != null) {
			if(root.parent == null && root.right != null) {
				return null;
			}
			root=root.parent;
			return checkRoot(root);
		}
		return root;
	}
	
	//Assigns codes for each character of the tree.  Assumes the array has null values before the method is called.  Fills in a String for each character in the tree indicating its code.
	void assign(String[] codes) {
		assignCode(overallRootHuffmanNode, "", codes);
	}
	
	//Helper method for assign code
	public void assignCode (HuffmanNode n, String result, String[] codes) {
		 if(n==null) {
			return;
		 }
		 if (n.left == null && n.right == null)	{
			codes[n.ascii] = result;
			return;
		 }
		 assignCode(n.left, result + "1", codes);
		 assignCode(n.right, result + "0", codes);
	}
	
	//Writes the current tree to the output stream using the standard bit representation.
	void writeHeader(BitOutputStream output) {
		writeHeaderHelper(overallRootHuffmanNode,output);
	}
	
	//Helper method for writeheader
	void writeHeaderHelper(HuffmanNode n,BitOutputStream output) {
		if(n == null) {
			 return ;
		}
		if(n.ascii == 0) {
			output.writeBit(1);
		}
		if (n.left == null && n.right == null){
			output.writeBit(0);
			write9(output,n.ascii);
			return;
		}
		 writeHeaderHelper(n.left, output);
		 writeHeaderHelper(n.right, output);
	}
	
	//Writes the ascii in 9 bit representation
	private void write9(BitOutputStream output, int n) {
	    for (int i = 0; i < 9; i++) {
	        output.writeBit(n % 2);
	        n /= 2;
	    }
	}
	
	// pre : an integer n has been encoded using write9 or its equivalent
	// post: reads 9 bits to reconstruct the original integer
	public int read9(BitInputStream input) {

	    int multiplier = 1;

	    int sum = 0;

	    for (int i = 0; i < 9; i++) {

	        sum += multiplier * input.readBit();

	        multiplier *= 2;

	    }

	    return sum;

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
		public HuffmanNode parent;

		public HuffmanNode(int frequency, int ascii) {
			this.frequency = frequency;
			this.ascii = ascii;
		}

		public HuffmanNode(int frequency) {
			this.frequency = frequency;

		}
		
		public HuffmanNode(int frequency, HuffmanNode parent) {
			this.frequency = frequency;
			this.parent = parent;
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
