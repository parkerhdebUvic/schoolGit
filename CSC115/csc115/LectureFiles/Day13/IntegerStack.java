public class IntegerStack {
	
	private static final int CAPACITY = 10;
	
	private int[] data;
	private int top;
	
	public IntegerStack() {
		data = new int[CAPACITY];
		top = 0;
	}
	
	/*
	 * Purpose: insert an item onto the top of the stack
	 * Parameters: int - the item to insert 
	 * Returns: Nothing
	 */
	public void push(int v) {
		// TODO: implement this
	}
	
	/*
	 * Purpose: removes and returns the top item from the stack
	 * Parameters: None
	 * Returns: int - the data value of the element removed
	 *                  -1 if the stack is empty
	 */
	public int pop() {
		// TODO: implement this
		return 0; // so it compiles
	}
	
	/*
	 * Purpose: determines whether the stack is empty
	 * Parameters: None
	 * Returns: boolean - true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		// TODO: implement this
		return false; // so it compiles
	}
	
	/*
	 * Purpose: Accesses the top item on the stack
	 * Parameters: None
	 * Returns: int - the data value of the top element
	 *                  -1 if the stack is empty
	 */
	public int peek() {
		// TODO: implement this
		return 0; // so it compiles
	}

}