// CSC115: Fall 2022
// Parker DeBruyne: V00837207

public class Student {
	private String sid;
	private int grade;
	
	public Student(){
		sid = "";
		grade = -1;
	}

	public Student(String sid, int grade) {
		this.sid = sid;
		this.grade = grade;
	}
	
	/*
	 * Purpose: get this student's sid
	 * Parameters: none
	 * Returns: String - this student's sid
	 */
	public String getSid() {
		return this.sid;
	} 
	
	/*
	 * Purpose: update this student's grade
	 * Parameters: int - the new grade
	 * Returns: void - nothing
	 */
	public void setGrade(int newGrade) {
		grade = newGrade;
	}

	/*
	 * Purpose: get this student's grade
	 * Parameters: none
	 * Returns: int - this student's grade
	 */
	public int getGrade() {
		return grade;
	}
	
	/*
	 * Purpose: get a string representation of this student
	 * Parameters: none
	 * Returns: String - the string representation
	 */
	public String toString() {
		return sid + ": " + grade;
	}
	
	/*
	 * Purpose: determine if this student is equivalent
	 *          to the given other student
	 * Parameters: Student other - the student to compare to
	 * Returns: boolean - true if they are equal, false otherwise
	 */
	public boolean equals(Student other) {
		return this.sid.equals(other.getSid());
	}
	
	/*
	 * Purpose: determine whether this student has a higher
	 *          grade than the given other student
	 * Parameters: Student other - the student to compare to
	 * Returns: boolean - true if this student has a higher grade
	 */
	public boolean higherGradeThan(Student other) {
		return this.grade > other.getGrade();
	}
}
	