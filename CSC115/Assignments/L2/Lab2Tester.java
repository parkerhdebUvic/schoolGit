/*
 * Lab2Tester.java
 *
 * A tester for the methods in Lab2.java
 *
 */
import java.util.Arrays;

public class Lab2Tester {
    
	private static int testPassCount = 0;
	private static int testCount = 0;

	// for approximate comparison of floating point numbers
	private static final double THRESHOLD = 0.01;

	public static void main(String[] args) {
		
		testGetHigherGradeStudent();
		testIsGradeAbove();
		testGetClasslist();
		testCountAbove();
		testGetClassAverage();
		testRegisterStudent();

		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	public static void testGetHigherGradeStudent() {
		// TODO: once you have implemented getHigherGradeStudent in Lab2.java
		//  uncomment the following tests - make sure you understand what they are testing

		
		Student s0  = new Student("abc", 50);
		Student s1a = new Student("def", 56);
		Student s1b = new Student("xyz", 56);
		Student s2  = new Student("xyz", 99);

		Student result;

		result = Lab2.getHigherGradeStudent(s0,s1a);
		//System.out.println("should be  " + s1a + " is " + result);
		displayResults(result.equals(s1a), "testGetHigherGradeStudent");

		result = Lab2.getHigherGradeStudent(s1a,s0);
		//System.out.println("should be  " + s1a + " is " + result);
		displayResults(result.equals(s1a), "testGetHigherGradeStudent");

		result = Lab2.getHigherGradeStudent(s1b,s1a);
		//System.out.println("should be  " + s1b + " is " + result);
		displayResults(result.equals(s1b) && result == s1b, "testGetHigherGradeStudent");

		result = Lab2.getHigherGradeStudent(s1b,s2);
		//System.out.println("should be  " + s2 + " is " + result);
		displayResults(result.equals(s2), "testGetHigherGradeStudent");
		
	}

	public static void testIsGradeAbove() {
		// TODO: write tests for Lab2.isGradeAbove

		Student s0  = new Student("abc", 50);
		Student s1a = new Student("def", 56);
		Student s1b = new Student("xyz", 56);
		Student s2  = new Student("xyz", 99);

		boolean result;
		boolean expected;

		result = Lab2.isGradeAbove(s0,30);
		expected = true;
		//System.out.println("should be  " + s0.getGrade() + " is above " + 30);
		displayResults(result==expected, "testisGradeAbove");

		result = Lab2.isGradeAbove(s1a,60);
		expected = false;
		//System.out.println("should be  " + s1a.getGrade() + " is not above " + 60);
		displayResults(result==expected, "testisGradeAbove");

		result = Lab2.isGradeAbove(s1b,56);
		expected = false;
		//System.out.println("should be  " + s0.getGrade() + " is not above " + 56);
		displayResults(result==expected, "testisGradeAbove");

		result = Lab2.isGradeAbove(s2,90);
		expected = true;
		//System.out.println("should be  " + s2.getGrade() + " is above " + 90);
		displayResults(result==expected, "testisGradeAbove");

	}

	public static void testGetClasslist() {
		// TODO: write tests for Lab2.getClasslist

		// NOTE: the Arrays library has been imported for you.
		//       you can use the Arrays.equals method to compare
		//       2 arrays of String objects as String has a equals method
		// The API for Arrays.equals:
		//       equals(Object[] a, Object[] a2)
		//       Returns true if the two specified arrays are equal to one another.

		// TODO: once you have implemented getClasslist in Lab2.java
		// uncomment the following test. We have gotten you started with 
		// some initial test data and one test, but you should consider 
		// other cases (empty array, longer array)

		
		Student s0  = new Student("abc", 50);
		Student[] students1 = {s0};
		String[] expected1 = {"abc"};
		String[] result;
		result = Lab2.getClasslist(students1);
		displayResults(Arrays.equals(result, expected1), "testGetClasslist - 1 elements");

		Student s0a  = new Student("abc", 50);
		Student s1  = new Student("def", 50);
		Student s2  = new Student("ghi", 50);
		Student[] students2 = {s0a,s1,s2};
		String[] expected2 = {"abc","def","ghi"};
		String[] result2;
		result2 = Lab2.getClasslist(students2);
		displayResults(Arrays.equals(result2, expected2), "testGetClasslist - 3 elements");
		
		Student s0b  = new Student();
		Student[] students3 = {s0b};
		String[] expected3 = {""};
		String[] result3;
		result3 = Lab2.getClasslist(students3);
		displayResults(Arrays.equals(result3, expected3), "testGetClasslist - null elements");
	}


	public static void testCountAbove() {
		// TODO: write tests for Lab2.countAbove
		Student s0  = new Student("abc", 50);
		Student s1  = new Student("def", 60);
		Student s2  = new Student("ghi", 70);
		Student s3  = new Student("jkl", 80);
		Student s4  = new Student("mno", 90);
		Student[] students1 = {s0,s1,s2,s3,s4};

		int threshold = 85;
		int result = Lab2.countAbove(students1, threshold);
		int expected = 1;
		displayResults(result==expected, "testCountAbove");

		int threshold2 = 75;
		int result2 = Lab2.countAbove(students1, threshold2);
		int expected2 = 2;
		displayResults(result2==expected2, "testCountAbove");

		int threshold3 = 65;
		int result3 = Lab2.countAbove(students1, threshold3);
		int expected3 = 3;
		displayResults(result3==expected3, "testCountAbove");

		int threshold4 = 95;
		int result4 = Lab2.countAbove(students1, threshold4);
		int expected4 = 0;
		displayResults(result4==expected4, "testCountAbove");


	}


	public static void testGetClassAverage() {
		Student s0 = new Student("abc", 50);
		Student s1 = new Student(); // considered invalid for average average calculation
		Student s2 = new Student("xyz", 99);
		Student s3 = new Student("def", 88);

		Student[] students0 = {};
		Student[] students1 = {s0};
		Student[] students2 = {s0, s1, s2};
		Student[] students3 = {s0, s2, s3};

		double result = 0.0;
		double expected = 0.0;
		
		// Some of you noticed in Lab1 that floating point arithmetic sometimes
		// produces results with many decimals places. We use the threshold
		// (defined as a global variable at the top) to specify the margin 
		// of error we are okay with, and just make sure the expected and 
		// returned results are within the threshold of acceptable error.

		
		result = Lab2.getClassAverage(students0);
		expected = 0;
		//System.out.println("Result:" + result + ", expected: " + expected);
		displayResults(Math.abs(result-expected)<THRESHOLD, "testGetClasslist - empty");

		result = Lab2.getClassAverage(students1);
		expected = 50.0;
		//System.out.println("Result:" + result + ", expected: " + expected);
		displayResults(Math.abs(result-expected)<THRESHOLD, "testGetClasslist - 1 student");

		result = Lab2.getClassAverage(students2);
		expected = (50 + 99) / 2.0; // because s1 does not have a "real" grade
		//System.out.println("Result:" + result + ", expected: " + expected);
		displayResults(Math.abs(result-expected)<THRESHOLD, "testGetClasslist - 3 students, count 2");

		result = Lab2.getClassAverage(students3);
		expected = (50 + 99 + 88) / 3.0;
		//System.out.println("Result:" + result + ", expected: " + expected);
		displayResults(Math.abs(result-expected)<THRESHOLD, "testGetClasslist - 3 students, count 3");
        
	}

	public static void testRegisterStudent() {
		// TODO: write tests for Lab2.registerStudent
		// HINT: the Student class also has a equals method so you
		// can use Arrays.equals again to compare 2 Student arrays

		Student s0 = new Student("abc", 50);
		Student s1 = new Student("def", 78); // considered invalid for average average calculation
		Student s2 = new Student("xyz", 99);
		Student s3 = new Student("def", 88);

		Student[] students = {s0, s1, s2};

		Student[] result = Lab2.registerStudent(students, s3);
		Student[] expected = {s0, s1, s2, s3};
		displayResults(Arrays.equals(result,expected), "testregisterStudent");



	}
	
	public static void displayResults (boolean passed, String testName) {
		/* There is some magic going on here getting the line number
		 * Borrowed from:
		 * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
		 */
		
		testCount++;
		if (passed) {
			System.out.println ("Passed test: " + testName);
			testPassCount++;
		} else {
			System.out.println ("Failed test: " + testName + " at line "
								+ Thread.currentThread().getStackTrace()[2].getLineNumber());
		}

	}

}
