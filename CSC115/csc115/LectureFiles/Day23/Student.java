public class Student implements Comparable<Student>{
 
	private String sID;
	private int year;
	private double gpa;
	
	public Student(String sID, int year, double gpa) {
		this.sID = sID;
		this.year = year;
		this.gpa = gpa;
	}
	
	public String getSID() {
		return this.sID;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public double getGPA() {
		return this.gpa;
	}
	
	public String toString() {
		return "{sID:" + sID + " year:" + year + " gpa:" + gpa + "}";
	}

	public int compareTo(Student other){
		return this.getYear() - other.getYear();
	}
	
}