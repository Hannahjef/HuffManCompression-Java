import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.WriteAbortedException;

//import HuffmanTree.HuffmanNode;

public class Tst {
	public static void main(String[] args) throws FileNotFoundException {
		int [] a= {1,1,2,3,3};
		new HuffmanTree(a);
			}
// //System.out.println(q.peek());
	 //printTree(overallRootHuffmanNode,);
	// String file="tst.code";
	// PrintStream out =new PrintStream(new File(file));
	// write(out);
}
/*public void printTree(HuffmanNode n , String result,int i) {
	 if(n==null) {
		 return ;
	 }
	 if (n.left == null && n.right == null)
		{
			System.out.println(n.ascii);
			System.out.println(result);
			return;
		}
	 printTree(n.left, result + "1",1);
		 printTree(n.right, result + "0",1);

}*/