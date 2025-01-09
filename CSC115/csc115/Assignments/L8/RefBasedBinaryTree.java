import java.lang.Math;
/*
 * RefBasedBinaryTree.java
 * 
 * A ref-based BinaryTree meant to store values of type Integer
 */
public class RefBasedBinaryTree implements BinaryTree {
	protected TreeNode root;

	public RefBasedBinaryTree() {
		// TODO...
		root = null;
	}

	public void insert(Integer value){
		insert(null, root, value);
	}

	/*
	 * Purpose: recursively determines the shortest path (root to leaf)
	 *      inserts a new TreeNode with given value at that leaf
	 * Parameters: TreeNode parent - the parent to t
	 *             TreeNode t - the current TreeNode in recursive traversal
	 *             Integer value - the value to be inserted
	 * Returns: nothing
	 */
	private void insert(TreeNode parent, TreeNode t, Integer value) {
		if (t == null) {
			if (parent == null) {
				root = new TreeNode(value);
			} else if (parent.getLeft()==null) {
				parent.setLeft(new TreeNode(value));
			} else {
				parent.setRight(new TreeNode(value));
			}
		} else {
			int htLeft = height(t.getLeft());
			int htRight = height(t.getRight());
			if (htLeft <= htRight) {
				insert(t, t.getLeft(), value);
			} else {
				insert(t, t.getRight(), value);
			}				
		}
	}
	
	
	public int height() {
		return height(root);
	}

	/*
	 * Purpose: computes and returns the height of a 
	 *          binary tree rooted at t 
	 * Parameters: TreeNode t - the BinaryTree
	 * Returns: int - the height
	 * NOTE: a BinaryTree with no node is height 0
	 */
	protected int height(TreeNode t) {
		// TODO...
		if (t == null){
			return -1;
		} else {
			return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
		}
	}


	public void inOrder(){
		// TODO...
		inOrder(root);
		return;
	}

	private void inOrder(TreeNode n){
		if (n == null){
			return;
		} else {
			inOrder(n.getLeft());
			System.out.println(n.getValue());
			inOrder(n.getRight());
		}
	}




	public void preOrder(){
		// TODO...
		preOrder(root);
		return;
	}

	public void preOrder(TreeNode n){
		if (n == null){
			return;
		} else {
			System.out.println(n.getValue());
			preOrder(n.getLeft());
			preOrder(n.getRight());
		}
	}


	public void postOrder(){
		// TODO...
		postOrder(root);
		return;
	}

	public void postOrder(TreeNode n){
		if (n == null){
			return;
		} else {
			postOrder(n.getLeft());
			postOrder(n.getRight());
			System.out.println(n.getValue());
		}
	}

	/*
	 * Purpose: returns a String reprensentation of this Shape
	 * Parameters: none
	 * Returns: String - the representation
	 */
	public String toString() {
		return toString(root);
	}

	private String toString(TreeNode t) {
		if(t == null) {
			return "";
		}
		String s = "";		
		s += toString(t.getLeft());
		s += t.getValue() + " ";
		s += toString(t.getRight());		
		return s;
	}

	// provided for testing of RefBasedBinaryTree class
	public static void main(String[] args) {
		
		RefBasedBinaryTree myTree = new RefBasedBinaryTree();
		for(int i=2; i<=8; i++) {
			myTree.insert(i);
		}
		System.out.println("in:");
		myTree.inOrder();
		System.out.println();
		System.out.println("pre:");
		myTree.preOrder();
		System.out.println();
		System.out.println("post:");
		myTree.postOrder();
		System.out.println();
		
		System.out.println("toString\n" + myTree);
	}
    
}
