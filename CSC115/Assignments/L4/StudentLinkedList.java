public class StudentLinkedList implements StudentList {

	StudentNode head;
	int count;

	public StudentLinkedList() {
		head = null;
		count = 0;
	}

	public void add(Student s) {
		// TODO: complete
		if (head != null) {
			StudentNode cur = head;
			while (cur.next != null){
				cur = cur.next;
			}
	
			StudentNode newStudent = new StudentNode(s);
			cur.next = newStudent;
			count++;

		} else {
			StudentNode newStudent = new StudentNode(s);
			head = newStudent;
			count++;
		}	
	}

	public int size() {
		// TODO: complete
		return count;
	}

	public void removeFront() {
		// TODO: complete
		
		if (head != null){
			StudentNode cur = head;
			head = head.next;
			cur.next = null;
			count--;
		}
	}

	public boolean contains(Student s) {
		// TODO: complete
		if (head != null) {
			StudentNode cur = head;
			while (cur != null){
				if (cur.getData().equals(s)){
					return true;
				}
				cur = cur.next;
			}

		}
		return false;
	}
	
	/*
	 * Purpose: returns a String reprensentation of elements
	 *      in this list separated by newlines
	 * Parameters: none
	 * Returns: String - the representation
	 */
	public String toString() {
		// DO NOT CHANGE THIS METHOD - it is correct
		// Changing it will result in your code getting
		// a score of 0 from the autograder for this lab
		String s = "";
		StudentNode tmp = head;
		while(tmp != null) {
			s  += tmp.getData() + "\n";
			tmp = tmp.next;
		}
		return s;
	}
}
