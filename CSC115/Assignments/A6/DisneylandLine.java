// Parker DeBruyne: V00837207
// Nov. 7th, 2022

/*
 * DisneylandLine
 *  
 * A class to simulate a lineup of people waiting to enter
 * Disneyland. Some people come alone and go to the back of 
 * the line, others bribe their way to a certain position. 
 * Sometimes someone buys a bunch of tickets at once so 
 * multiple people can be removed from the line and enter
 * Disneyland at the same time.
 */
public class DisneylandLine {
	List<Person> list;
	//Do not add any other fields

	public DisneylandLine() {
		//Do not change the constructor in any way
		list = new LinkedList<Person>();
	}
	
	/*
	 * Purpose: add a person to line to enter Disneyland
	 * Parameter: Person p - the person to add
	 * Returns void - nothing
	 */
	public void enterLine(Person p) {
		// TODO: implement this
		list.addBack(p);
	}
	
	/*
	 * Purpose: handle the first person in line
	            (allow them to purchase their ticket
				 and enter into Disneyland)
	 * Parameter: none
	 * Returns: Person - the person who gets their ticket
	 *                   and is no longer waiting in line
	 *                   or null if no one can be removed
	 *                   from the line
	 */
	public Person handleOne(){
		try {
			return list.removeFront();
		} catch (Exception e){
			return null;
		}
		 // so it compiles
	}
	
	/*
	 * Purpose: handle a whole group of people waiting in line
	 *          (allow a group to buy a number of tickets 
	 *           and all enter Disneyland)
	 * Parameters: int num - the number of people
	 * Returns int - the number of people who were successfully
	 *               able to be removed from the line
	 */
	public int handleMultiple(int num) throws ListEmptyException{
		// TODO: implement this
		if (num == 0 || list.size() == 0){
			return 0;
		} else {
			list.removeFront();
			return 1 + handleMultiple(num - 1);
		}
		// return -1; 
		// so it compiles
	}
	
	/*
	 * Purpose: accept a bribe to put someone into a specific
	 *          position in the line to get into Disneyland
	 * Parameters: Person p - the person entering the line
	 *             int pos - the position they are trying to get to
	 * Returns boolean - true if person added to line, false otherwise
	 */
	public boolean premiumEntry(Person p, int pos){
		// TODO: implement this
		try {
			list.insertAt(pos, p);
			return true;
		} catch (Exception e){
			return false;
		}
		// so it compiles
	}
	
	/*
	 * Purpose: gets the number of people in line
	 * Parameters: none
	 * Returns: int - number of people in line
	 */
	public int peopleInLine() {
		// TODO: implement this

		return list.size(); // so it compiles
	}
}
	
	