// Parker DeBruyne, V00837207, 2022-10-17

public class A4Stack<T> implements Stack<T> {
	  
	private A4Node<T> head;

	public A4Stack() {
		// TODO: implement this!
		// head = new A4Node<T>(null);
		head = null;
	} 
	
	public void push(T value) {
		// TODO: implement this!
		if (head == null) {
			A4Node<T> temp = new A4Node<T>(value);
			head = temp;
		} else {
			A4Node<T> temp = new A4Node<T>(value);
			temp.next = head;
			head = temp;
		}
	}
	
	public T pop() {
		// TODO: implement this!
		
		if (head != null) {
			A4Node<T> temp = head;
			head = head.next;
			return temp.getData(); // so it compiles
		} else {
			return null;
		}	
	}
	
	public void popAll() {
		// TODO: implement this!
		while (head != null){
			head = head.next;
		}
	}
	
	public boolean isEmpty() {
		// TODO: implement this!
		if (head == null){
			return true;
		}
		return false; // so it compiles	
	}
	
	public T peek() {
		// TODO: implement this!
		if (head != null) {
			return head.getData();
		} else {
			return null;
		}
		 // so it compiles	 	
	}
	
	/* The methods below have been completed for you.
	   Do not edit them in any way */

	public String toString() {
		String result = "{";
		String separator = "";
		
		A4Node<T> cur = head;
		while (cur != null) {
			result += separator + cur.getData().toString();
			separator = ", ";
			cur = cur.next;
		}
	
		result += "}";
		return result;
	}
	
	public String getWords() {
		return getWordsRec(head);
	}
	
	public String getWordsRec(A4Node<T> cur) {
		if (cur == null) {
			return "";
		} else if (cur.next == null) {
			return cur.getData().toString();
		}else {
			return getWordsRec(cur.next) + " " + cur.getData().toString();
		}
	}
}