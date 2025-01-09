@SuppressWarnings({"rawtypes", "unchecked"})
public class ComparableTester {
  
	public static void main(String[] args) {
		Integer num1 = 5;
		Integer num2 = 2;
		Integer num3 = 8;
		
		String s1 = "\"comparing\"";
		String s2 = "\"different\"";
		String s3 = "\"strings\"";

		Student t1 = new Student("V00837207", 2010, 3.5);
		Student t2 = new Student("V00837207", 2020, 3.5);
		
		/* 
		1. Use the compareTo method to compare the 
		   Integers and Strings initialized above.
		*/

		System.out.println(t1.compareTo(t2));
		
		/*
		2. Add implementation to the Student class
		   so that it implements the Comparable interface
		*/
		
		/*
		3. Create at least two Student objects and 
		   use the compareTo method in the Student
		   class to compare them.
		*/		
	}

}