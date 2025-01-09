/*
 * ArrayBasedBinaryTree.java
 * 
 * An array-based BinaryTree meant to store values of type Integer
 */
public class ArrayBasedBinaryTree implements BinaryTree {
    private static final int CAPACITY = 5;
    protected Integer[] data;
    protected int root;
    protected int size;
    
	public ArrayBasedBinaryTree() {
		// TODO...
		// allocate space for data array.
		// What index are you choosing the root to be?
		// initialize your size as the number of elements in the empty tree
		data = new Integer[CAPACITY];
		root = 0;
		size = 0;
	}

	/*
	 * Purpose: inserts the given value into data at next available
	 *  position in a level-order traversal
	 *  The tree remains complete after insertion.
	 * Parameters: Integer value - value to insert
	 * Returns: nothing
	 */
	public void insert(Integer value) {
		// TODO...
		// NOTE: The underlying data structure is an array,
		//  but we are just USING the array to store the data.
		//  The way we traverse the data will expose its binary tree structure
		if (size >= data.length){
			expandAndCopy();
		} 
		data[size] = value;
		size++;
	}

	protected void expandAndCopy() {
		Integer[] newData = new Integer[data.length*2];
		for(int i=0; i<data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}

	/*
	 * Purpose: calculates and returns the index of t's left child
	 * Parameters: int t - index of current element in this ArrayBasedBinaryTree
	 * Returns: int - index of left child
	 */
	protected int getLeft(int t) {
		return 2*t + 1;
	}

	/*
	 * Purpose: calculates and returns the index of t's right child
	 * Parameters: int t - index of current element in this ArrayBasedBinaryTree
	 * Returns: int - index of right child
	 */
	protected int getRight(int t) {
		return 2*t + 2;
	}

	//inorder: left, node, right
	public void inOrder(){
		// TODO...
		inOrder(root);
		return;
	}

	private void inOrder(int index){
		if (index >= size){
			return;
		} else {
			inOrder(getLeft(index));
			System.out.println(data[index]);
			inOrder(getRight(index));
		}
	}

	//preOrder: node, left, right
	public void preOrder(){
		// TODO...
		preOrder(root);
		return;
	}

	public void preOrder(int index){
		if (index >= size){
			return;
		} else {
			System.out.println(data[index]);
			preOrder(getLeft(index));
			preOrder(getRight(index));
		}
	}

	//postOrder: left, right, node
	public void postOrder(){
		// TODO...
		postOrder(root);
		return;
	}

	public void postOrder(int index){
		if (index >= size){
			return;
		} else {
			postOrder(getLeft(index));
			postOrder(getRight(index));
			System.out.println(data[index]);
		}
	}


	public int height() {
		return height(root);
	}

	/*
	 * Purpose: computes and returns the height of a 
	 *          binary tree rooted at index t 
	 * Parameters: TreeNode t - the BinaryTree
	 * Returns: int - the height
	 * NOTE: a BinaryTree with no nodes is height -1
	 *       a BinaryTree with just a root is height 0
	 *
	 *       the height of a node in a tree is equal to 
	 *       1 + the height of its largest subtree
	 */
	protected int height(int t) {
		
		if (t >= size){
			return -1;
		} else {
			return 1 + Math.max(height(getLeft(t)), height(getRight(t)));
		}
		// TODO: complete the rest
		
	}

	/*
	 * Purpose: returns a String reprensentation of a in-order traversal
	 *     of this ArrayBasedBinaryTree
	 * Parameters: none
	 * Returns: String - the representation
	 */
	// written for you - do not change
	// NOTICE: we use the helper methods to get the index of the left/right
	//   children of the current position in the tree.
	// This method will not work until you have completed those methods correctly.
	public String toString() {
		return toString(root);
	}

	private String toString(int t) {
        if (t >= size) {
            return "";
        } 
        String s = "";
        s += toString(getLeft(t));
        s += data[t] + " ";
        s += toString(getRight(t));

        return s;
	}

	public static void main(String[] args) {
		
		ArrayBasedBinaryTree myTree = new ArrayBasedBinaryTree();
		for(int i=2; i<8; i++) {
			myTree.insert(i);
		}
		System.out.println("in");
		myTree.inOrder();
		System.out.println("pre");
		myTree.preOrder();
		System.out.println("post");
		myTree.postOrder();
		
		System.out.println("toString\n" + myTree);
	}
    
}
