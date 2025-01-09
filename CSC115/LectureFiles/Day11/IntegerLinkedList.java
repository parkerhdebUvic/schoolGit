public class IntegerLinkedList implements IntegerList {
    
	private Node head;
	private Node tail;
	private int numElements;

	public IntegerLinkedList() {
		head = null;
		tail = null;
		numElements = 0;
	}

	public void addFront (int val) {
		Node n = new Node(val);
		if (head != null) {
			n.next = head;
		} else {
			tail = n;
		}
		head = n;
		numElements++;

		
	}

	public void addBack (int val) {
		Node n = new Node(val);
		if (head == null){
			head = n;
		} else {
			// go to the end of the list
			// when we get to the back back.next = n;
			tail.next = n; // having a tail makes it so you don't have to loop through the whole list
		}
		tail.next = n;
		numElements++;
	}

	public int size (){
		return numElements;
	}

	public int get (int position) {
		Node cur = head;
		for(int i=0; i < position; i++) {
			cur = cur.next;
		}
		return cur.getValue();
	}
	
	public void insertAt (int position, int val) {
		if (position == 0){
			addFront(val);
		} else if (position == numElements) {
			addBack(val);
		} else {
			Node cur = head;
			for (int i = 1; i<position; i++) {
				cur = cur.next;
			}
			Node n = newNode(val);
			n.next = cur.next;
			cur.next = n;
			numElements++;
		}
	}

	/* Purpose: create a string representation of list 
	 * Parameters: none
	 * Returns: String - the string representation
	 */
	public String toString() {
		String s = "List contents: ";
		Node cur = head;
		while (cur != null) {
			s += cur.getValue();
			cur = cur.next;
		}
		return s;
	}    
}



