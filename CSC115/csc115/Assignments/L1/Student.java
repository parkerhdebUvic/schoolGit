/*
* Name: Parker DeBruyne
* V#: V00837207
* Student.java
* A Student class
*/
public class Student {

	private String sID;
	private int grade;

	public Student() {
		sID = "";
		grade = -1;
	}

	public Student(String sID, int grade) {
		this.sID = sID;
		this.grade = grade;
	}
	
	public String getSID() {
		return sID; // so it compiles
	}
	
	public int getGrade() {
		return grade; // so it compiles
	}

	public void setSID(String newSID) {
		this.sID = newSID;
	}

	public void setGrade(int newGrade){
		this.grade = newGrade;
	}

	public String toString() {
		return ("Student Number: " + this.sID + "\nCurrent Grade: " + this.grade);
	}
}
