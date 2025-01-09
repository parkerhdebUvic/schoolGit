public interface IntegerList {
    
	/* Purpose:  add val to the front of the list
	 * Parameters: (int) val
	 * Returns:  nothing
	 */
	
	void addFront (int val);

	/* Purpose:  add val to the back of the list
	 * Parameters: (int) val	 
	 * Returns:  nothing
	 */
	void addBack (int val);
	
	/* Purpose: get the number of elements in the list
	 * Parameters: none
	 * Returns: (int) number of elements in list
	 */
	int size ();

	/* Purpose:  get the int value at specified position in the list
	 * Parameters: (int) position
	 * Returns:  (int) the value of the element
	 * Precondition: 0 <= position < list.size()
	 */
	int get (int position);
    
}

