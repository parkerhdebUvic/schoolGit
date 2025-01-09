// Name: Parker DeBruyne
// Student number: v00837207

public class A3LinkedList implements A3List {
	private A3Node head;
	private A3Node tail;
	private int length;
	

	public A3LinkedList() {
		head = null;
		tail = null;
		length = 0;
	}
	
	public void addFront(String s) {
		if (s != ""){	
			A3Node temp = new A3Node(s);
			if (head == null) {
				head = temp;
				tail = temp;
				length++;
			} else {
				temp.next = head;
				head.prev = temp;
				head = temp;
				length++;
			}
		}
	}

	public void addBack(String s) {
		if (s != ""){
			A3Node temp = new A3Node(s);
			if (tail == null){
				tail = temp;
				head = temp;
				length++;
			} else {
				tail.next = temp;
				temp.prev = tail;
				tail = temp;
				length++;
			}
		}	
	}
	
	public int size() {
		return length;
	}
	
	public boolean isEmpty() {
		return length==0;
	}
	
	public void removeFront() {
		if (head != null && head.next != null){
			head = head.next;
			head.prev.next = null;
			head.prev = null;
			length--;
		} else {
			A3Node temp = new A3Node("");
			head = temp;
			tail = temp;
			length = 0;
		}
	}
	
	public void removeBack() {
		if (tail != null && tail.prev != null) {
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
			length--;
		} else {
			A3Node temp = new A3Node("");
			head = temp;
			tail = temp;
			length = 0;
		}
	}
	
	public void moveToBack(int pos) {
		if ((pos >= 0) && (length > 1) && (pos < length-1)){
			A3Node temp = new A3Node("");
			temp = head;
			for (int i=0; i < pos; i++){
				temp = temp.next;
			}
			if (pos != 0) {
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
				temp.prev = tail;
				temp.next = null;
				tail.next = temp;
				tail = temp;
			} else {
				head = temp.next;
				head.prev = null;
				temp.next = null;
				temp.prev = tail;
				tail.next = temp;
				tail = temp;
			}
			
		}
	}
	

	public void insertInto(String s) {
		A3Node temp = new A3Node(s);
		A3Node cur = new A3Node("");

		if (length > 1) {
			cur = head;
			for (int i=0; i<length-1; i++) {
				if (temp.comesBefore(cur.next)){
					temp.next = cur.next;
					temp.next.prev = cur;
					temp.prev = cur;
					cur.next = temp;
					break;
				} else if (i == length-2){
					tail.next = temp;
					temp.prev = tail;
					tail = temp;
				}
				cur = cur.next;
			}
		} else if (length == 1) {
			if (temp.comesBefore(head)){
				temp.next = head;
				head.prev = temp;
				head = temp;
			} else {
				head.next = temp;
				temp.prev = head;
				tail = temp;
			}
		} else {
			head = temp;
			tail = temp;
		}
		length++;
	}
	
	/*
	 * Purpose: return a string representation of the list 
	 *          when traversed from front to back
	 * Parameters: none
	 * Returns: nothing
	 */
	public String frontToBack() {
		String result = "{";
		A3Node cur = head;
		while (cur != null) {
			result += cur.getData();
			cur = cur.next;
		}
		result += "}";
		return result;
	}
	
	/*
	 * Purpose: return a string representation of the list 
	 *          when traversed from back to front
	 * Parameters: none
	 * Returns: nothing
	 */
	public String backToFront() {
		String result = "{";
		A3Node cur = tail;
		while (cur != null) {
			result += cur.getData();
			cur = cur.prev;
		}
		result += "}";
		return result;
	}
}
	