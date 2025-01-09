public class IntegerArrayList implements IntegerList {

	private static final int INITIAL_SIZE = 10;

	private int[] data; // the array storing the elements
	private int numElements; // the current number of elements in the integer list

	public IntegerArrayList() {
		data = new int[INITIAL_SIZE];
		numElements = 0;
	}

	public void addFront(int val) {
		// Is there room to insert a new element??
		
		// shuffle all elements to the right one space
		// to make room at the front of the array
		for (int i = numElements; i > 0; i--) {
			data[i] = data[i-1];
		}
		// break out of the loop when we copy 
		// the element from index 0 into index 1
		
		data[0] = val;
		numElements++;
		
	}
	
	public void addBack(int val) {
		// Is there room to insert a new element??

		
		data[numElements] = val;
		numElements++;		
	}

	public void insertAt(int position, int val) {
		for (int i = numElements; i > position; i--) {
			data[i] = data[i-1];
		}
		data[position] = val;
		numElements++;
	}

	public int size() {
		return numElements;
	}
	
	public int get(int position) {
		return data[position];
	}

	/* toString
	 * Purpose: create a string representation of list
	 * Parameters: nothing
	 * Returns: (String) the string representation of the list
	 */
	public String toString() {
		String s = "List contents:";

		for(int i=0; i<numElements; i++) {
			s += " " + data[i];
		}

		return s;
	}

}
