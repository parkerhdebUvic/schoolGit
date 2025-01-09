public class Day1 {

	public static void main(String[] args) {
		// Call the methods you complete from the 
		// main method to determine if they work correctly.

		System.out.println(getFibonacci(80));
	}
	
	// 1) Design a method named printMax that takes two 
	// integers and outputs the maximum value.
	public static int printMax(int a, int b) {
		if (a == b){
			return a;
		} else if (a > b){
			return a;
		} else {
			return b;
		}
	}
	

	// 2) Design a method named isSpecial, that takes an
	// integer and determines if it is special 
	//
	// Assume an integer is special if it is divisible
	// by 3 or 7 (or both).
	public static boolean isSpecial(int a){
		if (a % 3 == 0){
			return true;
		} else if (a % 7 == 0){
			return true;
		} else {
			return false;
		}
	}


	// 3) Design a method named countSpecialToN, that takes
	// an integer n and determines how many special
	// integers there are between 1 and n (inclusive).
	//
	// This method MUST call the method you designed in 1)

	public static int countSpecialToN(int n){
		int count = 0;
		for (int i=1; i<n; i++){
			if (isSpecial(i) == true){
				count += 1;
			}
		}
		return count;
	}

	// 4) Design a method named getFibonacci that takes
	// an integer n and returns the nth term in the 
	// Fibonacci sequence. 
	//
	// In the Fibonacci sequence, the first two terms
	// are 1, and every term following is the sum of the 
	// two previous terms. For example: 1 1 2 3 5 8 13 21 34...

	public static int getFibonacci(int n){
		int x1=1, x2=1, x=x1+x2;

		if (n==0){
			return 0;
		} else if (n<3){
			return x1;
		} for (int i=3; i<n; i++){
			x1=x2;
			x2=x;
			x=x1+x2;
		} return x;
	}


	// 5) Design a method named getLeftDigit that takes 
	// an integer and returns the leftmost digit.
	//
	// For example: if given 8,432,590, then 8 would be
	// returned, as it is the leftmost digit.
	//
	// You must use a while-loop in your solution.

	public static int getLeftDigit(int n){
		int leftD = 0, a=n;
		//divide it by 10 then truncate
		while ()
	}


	// 6) Design a method named printBox that takes two 
	// integers representing the height and width of a 
	// box. The method should output a box with the
	// correct dimensions.
	//
	// For example, printBox(6,3) outputs:
	// ***
	// ***
	// ***
	// ***
	// ***
	// ***



	// 7) Design a method named printPattern that an 
	// integer n representing the size of the pattern
	// to output. The method outputs a triangle n
	// lines long. 
	//
	// For example, printPattern(5) outputs:
	// *
	// **
	// ***
	// ****
	// *****
	
}
