public class TreeNode {

	private int data;
	// 1. What other fields will we need in a TreeNode?
	protected TreeNode left;
	protected TreeNode right;

	// 2. Write constructor(s) for a TreeNode
	public TreeNode (){
		this.left = null;
		this.right = null;
	}

	public TreeNode (int data, TreeNode left, TreeNode right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public static void main(String[] args) {
		
		// 3. Create an instance of a TreeNode (call it a) with int value 5
		TreeNode a = new TreeNode(5);
		// 4a. Create another instance of a TreeNode (call it b) with int value 8
		//     and make it the right child of the TreeNode a that you created above.
		TreeNode b = new TreeNode(8);
		a.right = b;
		// 4b. Print out the value of the TreeNode b without using b in the print statement
		
		// 5. Create a tree with the following structure:
		//            5
		//          /   \  
		//         2     8
		//       /   \
		//      1     4
		a.left = new TreeNode(2);
		a.left.left = new TreeNode(1);
		a.left.right = new TreeNode(4);
		
		// 6. How could we print out the contents of the tree? What is the expected output?
		System.out.println(a.data);
		System.out.println(a.left.data);
		System.out.println(a.right.data);
		System.out.println(a.left.left.data);
		System.out.println(a.left.right.data);
	}
	

}