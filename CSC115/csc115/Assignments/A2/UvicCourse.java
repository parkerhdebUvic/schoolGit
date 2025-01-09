// CSC115: Fall 2022
// Parker DeBruyne: V00837207

public class UvicCourse {
	private String name;
	private Student[] classList;
	
	public UvicCourse(){
		name = "a course";
		classList = null;
	}

	public UvicCourse(String name, Student[] classList) {
		// TODO: fix this
		this.name = name;
		this.classList = classList;
	} 
	
	/*
	 * Purpose: get this course's name
	 * Parameters: none
	 * Returns: String - this course's name
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * Purpose: get this course's classlist
	 * Parameters: none
	 * Returns: Student[] - the array of students in this class
	 */
	public Student[] getClassList() {
		return this.classList;
	}
	
	
	/* 
	 * Purpose: calculate the average grade of all students in this course
	 * Parameters: none
	 * Returns: double - the average grade of all students,
	 *                   or 0.0 if there are no enrolled students
	 */
	public double averageGrade() {
		// TODO: implement this	
		double sum = 0;
		double count = 0;
		
		if (classList == null){
			return 0.0;
		}

		for (int i=0; i<classList.length; i++){
			count++;
			sum += classList[i].getGrade();
		}
		if (count != 0){
			return sum / count;
		} else {
			return 0.0;
		}
		 // so it compiles	
	}


	/*
	 * Purpose: gets the grade of the student with given sid
	 * Parameters: String sid - the sid of the student to search for
	 * Returns: int - the grade of the students with given sid, 
	 *                or -1 if no student with given sid is in 
	 *                enrolled in this class
	 */
	public int getGrade(String sid) {
		int grade = -1;
		// TODO: implement this	

		for (int i=0; i<this.classList.length; i++){
			if (this.classList[i].getSid().equals(sid)){
				grade = this.classList[i].getGrade();
			}
		}
		return grade; // so it compiles
	}

	
	/*
	 * Purpose: determines if s is in this course's class list
	 * Parameters: Student s - the student
	 * Returns: boolean - true if this course's class list contains s
	 */
	public boolean hasStudent(Student s) {
		boolean isFound = false;
		// TODO: implement this
		if (s.equals("")){
			isFound = false;
		} else {
			for (int i=0; i<classList.length; i++){
				if (classList[i].getSid().equals(s.getSid())){
					isFound = true;
				}
			}
		}
		return isFound; // so it compiles
	}

	public boolean hasSID(String s) {
		boolean isFound = false;
		// TODO: implement this
		if (s.equals("")){
			isFound = false;
		} else {
			for (int i=0; i<classList.length; i++){
				if (classList[i].getSid().equals(s)){
					isFound = true;
				}
			}
		}
		return isFound; // so it compiles
	}
	
	
	/*
	 * Purpose: updates the grade of the student with given studentSid to
	 *          newGrade if the student is found in this course's class list
	 * Parameters: String studentSid - the sid of the student to update
	 *             int newGrade - the new grade for the student
	 * Returns: void - nothing
	 */
	public void updateGrade(String studentSid, int newGrade) {
			for (int i=0; i<classList.length; i++){
				if (classList[i].getSid().equals(studentSid)){
					classList[i].setGrade(newGrade);
				}
			}
	}
	

	
	/*
	 * Purpose: add the given student to this course's classlist
	 * Parameters: Strudent newStudent - student to add to class list
	 * Returns: void - nothing
	 * Precondition: newStudent is not already a student in classList
	 */
	public void addStudent(Student newStudent) {
		Student[] updatedList = new Student[classList.length+1];
		// TODO: implement this	
		boolean notInClass = true;
		for (int i=0;i<classList.length;i++){
			if (classList[i].equals(newStudent)){
				notInClass = false;
			}
		}

		if (notInClass == true){
			for (int i=0; i<classList.length; i++){
				updatedList[i] = classList[i];
			}
			updatedList[updatedList.length-1] = newStudent;
		}

		classList = updatedList;
	}

}