// Parker DeBruyne, V00837207, 2022-10-17

public class A4Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;
	
	public static void main(String[] args) {
		testStackOperations();
		testStackIsGeneric();
		testWordProcessor();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	public static void testStackOperations() {
		System.out.println("Stack Operations Tests:");
		A4Stack<Integer> testStack = new A4Stack<Integer>();
		Integer result = 0;
		
		System.out.println(testStack.toString());
		displayResults(testStack.isEmpty(), "stack initially empty");
		
		testStack.push(2);
		result = testStack.peek();
		displayResults(!testStack.isEmpty(), "stack no longer empty");
		displayResults(result.equals(2), "peek works after initial push");
		
		//TODO: add more tests here

		testStack.pop();
		System.out.println(testStack.toString());
		result = 0;
		displayResults(testStack.isEmpty(), "stack is now empty");
		
		testStack.pop();
		System.out.println(testStack.toString());
		result = 0;
		displayResults(testStack.isEmpty(), "empty stack is still empty");

		testStack.push(2);
		System.out.println(testStack.toString());
		result = testStack.peek();
		displayResults(!testStack.isEmpty(), "stack no longer empty");
		displayResults(result.equals(2), "peek works after initial push");

		testStack.push(3);
		System.out.println(testStack.toString());
		result = testStack.peek();
		displayResults(!testStack.isEmpty(), "stack no longer empty");
		displayResults(result.equals(3), "peek works after initial push");
		
		testStack.push(4);
		System.out.println(testStack.toString());
		result = testStack.peek();
		displayResults(!testStack.isEmpty(), "stack no longer empty");
		displayResults(result.equals(4), "peek works after initial push");

		testStack.popAll();
		System.out.println(testStack.toString());
		result = 0;
		displayResults(testStack.isEmpty(), "stack is now empty");
		
		System.out.println();
	}
	
	public static void testStackIsGeneric() {
		System.out.println("Stack Generics Tests:");
		A4Stack<Integer> s1 = new A4Stack<Integer>();
		A4Stack<String> s2 = new A4Stack<String>();
		A4Stack<Double> s3 = new A4Stack<Double>();
		A4Stack<Boolean> s4 = new A4Stack<Boolean>();

		
		Integer result1;
		String result2;
		Double result3;
		Boolean result4;
		
		s1.push(3);
		s1.push(8);
		s2.push("CSC");
		s2.push("ENGR");
		s3.push(5.5);
		s3.push(9.1);
		s4.push(true);
		s4.push(false);
		
		result1 = s1.pop();
		result2 = s2.pop();
		result3 = s3.pop();
		result4 = s4.pop();	
		
		displayResults(result1.equals(8), "Integer Stack");
		displayResults(result2.equals("ENGR"), "String Stack");
		displayResults(result3.equals(9.1), "Double Stack");		
		displayResults(result4.equals(false), "Boolean Stack");

		result1 = s1.peek();
		result2 = s2.peek();
		result3 = s3.peek();
		// result4 = s4.peek();
	
		
		displayResults(result1.equals(3), "Integer Stack");
		displayResults(result2.equals("CSC"), "String Stack");
		displayResults(result3.equals(5.5), "Double Stack");
		displayResults(result4.equals(false), "Boolean Stack");
		
		//TODO: add more tests here
		
		System.out.println();
	}

	public static void testWordProcessor() {
		/* The following statement will begin the word processor
		 * simulation. You can comment it out to test the undo 
		 * and redo methods individually if you wish, or just 
		 * test them by running the simulation. */
		// WordProcessor.simulateWordProcessor();

		String w1 = "This";
		String w2 = "is";
		String w3 = "a";
		String w4 = "test.";
		String result = "";
		String test = "";

		WordProcessor document = new WordProcessor();

		document.undo.push(w1);
		document.undo.push(w2);
		document.undo.push(w3);
		document.undo.push(w4);
		// document.displayDocument();
		// System.out.println(document.undo);
		result = "test.";
		test = document.undo.peek();
		displayResults(test.equals(result), "Testing displayDocument()");

		document.undo();
		result = "a";
		test = document.undo.peek();
		displayResults(test.equals(result), "Testing displayDocument()");
		
		document.undo();
		result = "is";
		test = document.undo.peek();
		displayResults(test.equals(result), "Testing displayDocument()");
		
		document.undo();
		result = "This";
		test = document.undo.peek();
		displayResults(test.equals(result), "Testing displayDocument()");
		
		document.undo();
		result = null;
		test = document.undo.peek();
		displayResults(test == result, "Testing displayDocument()");
		
		document.redo();
		result = "This";
		test = document.undo.peek();
		displayResults(test.equals(result), "Testing displayDocument()");
		
		document.redo();
		result = "is";
		test = document.undo.peek();
		displayResults(test.equals(result), "Testing displayDocument()");
		
		document.redo();
		result = "a";
		test = document.undo.peek();
		displayResults(test.equals(result), "Testing displayDocument()");
		
		document.redo();
		result = "test.";
		test = document.undo.peek();
		displayResults(test.equals(result), "Testing displayDocument()");
		
	}

	public static void displayResults (boolean passed, String testName) {
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