public class TreeNode {

	private int data;
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode(int data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	public TreeNode(int data, TreeNode left, TreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	/*
	 * Purpose: prints all the elements found in the tree rooted at cur
	 *          using an inOrder traversal to visit all elements
	 * Parameters: TreeNode cur 
	 * Returns: void - nothing
	 */
	public static void printTree(TreeNode cur) {
		if (cur == null) {
			return;
		}
		printTree(cur.left);
		System.out.print(cur.data + " ");
		printTree(cur.right);
	}
	
	public static void main(String[] args) {
		
		TreeNode a = new TreeNode(5);
		System.out.println(a.data);
		
		TreeNode b = new TreeNode(8);
		a.right = b;
		System.out.println(a.right.data);
		
		TreeNode one = new TreeNode(1);
		TreeNode four = new TreeNode(4);
		TreeNode two = new TreeNode(2, one, four);

		a.left = two;
		
		// post order: left right value
		// in-order: left value right
		// poer-order: 
		// level-order:

		System.out.print("printing tree: ");
		printTree(a);
		System.out.println();
	}

}


// 4 common traversals
// pre-order: vist node, then call recursion on the left child, then the right
// in-order: recussion left, visit node, recusrussion right
// post-order: recursion (left), recursion (right), visit node
// level-order: 
//      1. create an empty que
// 		2. add the root of tree to Q
// 		3. while Q is not empty:
//			- deque node
//			- visit the dequed node
//			- enque the nodes left and right children
//		
// 