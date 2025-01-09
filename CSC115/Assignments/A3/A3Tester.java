// Name: Parker DeBruyne
// Student number: v00837207

/*
 * A3Tester
 *
 * A class to test the methods required for Assignment 3
 *
 */

public class A3Tester {
    
	private static int testPassCount = 0;
	private static int testCount = 0;

    public static void main(String[] args) {
		
		/*
		Uncomment each method one at a time, 
		and implement the method in A3LinkedList.java 
		until all tests pass. It is strongly
		recommended that you add additional tests 
		to ensure the correctness of your implementation.
		*/
		
		/* Part 1: */
		testAddFront();
		testAddBack();
		testSizeAndIsEmpty();
		testRemoveFront();
		testRemoveBack();
		
		/* Part 2 */
		testMoveToBack();
		testInsertInto();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
    }
	
		
	public static void testAddFront() {
		String result = "";
		A3LinkedList list1 = new A3LinkedList();
		
		result = list1.frontToBack();
		displayResults(result.equals("{}"), "testAddFront");
		
		list1.addFront("A");
		result = list1.frontToBack();
		displayResults(result.equals("{A}"), "testAddFront");
		
		list1.addFront("V");
		list1.addFront("A");
		list1.addFront("J");
		result = list1.frontToBack();
		displayResults(result.equals("{JAVA}"), "testAddFront");
		result = list1.backToFront();
		displayResults(result.equals("{AVAJ}"), "testAddFront");

		list1.addFront("");
		result = list1.frontToBack();
		displayResults(result.equals("{JAVA}"), "testAddFront");
	}
	
	public static void testAddBack() {
		String result = "";
		A3LinkedList list1 = new A3LinkedList();
		
		result = list1.frontToBack();
		displayResults(result.equals("{}"), "testAddBack");
		
		list1.addBack("F");
		result = list1.frontToBack();
		displayResults(result.equals("{F}"), "testAddBack");
		
		list1.addBack("U");
		list1.addBack("N");
		result = list1.frontToBack();
		displayResults(result.equals("{FUN}"), "testAddBack");
		result = list1.backToFront();
		displayResults(result.equals("{NUF}"), "testAddBack");

		list1.addBack("");
		result = list1.frontToBack();
		displayResults(result.equals("{FUN}"), "testAddBack");
	}
	
	public static void testSizeAndIsEmpty() {
		int result = 0;
		A3LinkedList list1 = new A3LinkedList();

		result = list1.size();
		displayResults(result==0, "testSizeAndIsEmpty");
		displayResults(list1.isEmpty()==true, "testSizeAndIsEmpty");
		
		list1.addFront("C");
		list1.addFront("S");
		list1.addFront("C");
		result = list1.size();
		displayResults(result==3, "testSizeAndIsEmpty");
		displayResults(list1.isEmpty()==false, "testSizeAndIsEmpty");
		
		list1.addBack("115");
		result = list1.size();
		displayResults(result==4, "testSizeAndIsEmpty");

		list1.addBack("");
		result = list1.size();
		displayResults(result==4, "testSizeAndIsEmpty");

		list1.addFront("");
		result = list1.size();
		displayResults(result==4, "testSizeAndIsEmpty");
		
	}
	
	public static void testRemoveFront() {
		String result = "";
		A3LinkedList list1 = new A3LinkedList();
		
		list1.addBack("P");
		list1.addBack("I");
		list1.addBack("N");
		list1.addBack("K");
		result = list1.frontToBack();
		displayResults(result.equals("{PINK}"), "testRemoveFront");
		
		list1.removeFront();
		result = list1.frontToBack();
		displayResults(result.equals("{INK}"), "testRemoveFront");
		/* Write additional tests here to ensure all of your pointers
		   have been updated correctly.
		
		   You should also ensure that your size and isEmpty 
		   methods work with removal as well as addition */
		list1.removeFront();
		result = list1.frontToBack();
		displayResults(result.equals("{NK}"), "testRemoveFront");

		list1.removeFront();
		result = list1.frontToBack();
		displayResults(result.equals("{K}"), "testRemoveFront");

		list1.removeFront();
		result = list1.frontToBack();
		displayResults(result.equals("{}"), "testRemoveFront");
		displayResults(list1.size() == 0, "test empty size");
		
				
	}
	
	public static void testRemoveBack() {
		// Write all of your own tests here 
		String result = "";
		A3LinkedList list1 = new A3LinkedList();
		
		list1.addBack("P");
		list1.addBack("I");
		list1.addBack("N");
		list1.addBack("K");
		result = list1.frontToBack();
		displayResults(result.equals("{PINK}"), "testRemoveBack");
		
		list1.removeBack();
		result = list1.frontToBack();
		displayResults(result.equals("{PIN}"), "testRemoveBack");

		list1.removeBack();
		result = list1.frontToBack();
		displayResults(result.equals("{PI}"), "testRemoveBack");

		list1.removeBack();
		result = list1.frontToBack();
		displayResults(result.equals("{P}"), "testRemoveBack");

		list1.removeBack();
		result = list1.frontToBack();
		displayResults(result.equals("{}"), "testRemoveFront");
		displayResults(list1.size() == 0, "test empty size");
	}
	
	public static void testMoveToBack() {
		A3LinkedList list1 = new A3LinkedList();
		
		String result1;
		
		list1.addBack("a");
		list1.addBack("b");
		list1.addBack("c");
		list1.addBack("d");
		list1.addBack("e");
		list1.addBack("f");
		
		list1.moveToBack(2);
		result1 = list1.frontToBack();
		displayResults(result1.equals("{abdefc}"), "moveToBack position 2");
		
		list1.moveToBack(4);
		result1 = list1.frontToBack();
		displayResults(result1.equals("{abdecf}"), "moveToBack position 4");

		list1.moveToBack(1);
		result1 = list1.frontToBack();
		displayResults(result1.equals("{adecfb}"), "moveToBack position 1");
		// Write more tests here

		list1.moveToBack(3);
		result1 = list1.frontToBack();
		displayResults(result1.equals("{adefbc}"), "moveToBack position 3");

		list1.moveToBack(5);
		result1 = list1.frontToBack();
		displayResults(result1.equals("{adefbc}"), "moveToBack position 5");

		list1.moveToBack(0);
		result1 = list1.frontToBack();
		displayResults(result1.equals("{defbca}"), "moveToBack position 0");

		list1.moveToBack(-1);
		result1 = list1.frontToBack();
		displayResults(result1.equals("{defbca}"), "moveToBack position 0");

		list1.moveToBack(100);
		result1 = list1.frontToBack();
		displayResults(result1.equals("{defbca}"), "moveToBack position 0");
		
		
		
		
	}
	
	public static void testInsertInto() {
		A3LinkedList list1 = new A3LinkedList();
		
		String result1;
		
		list1.addBack("c");
		list1.addBack("s");
		list1.addBack("y");
		
		list1.insertInto("h");
		result1 = list1.frontToBack();
		displayResults(result1.equals("{chsy}"), "insertInto test1");
		
		list1.insertInto("t");
		result1 = list1.frontToBack();
		displayResults(result1.equals("{chsty}"), "insertInto test3");
		
		list1.insertInto("z");
		result1 = list1.frontToBack();
		displayResults(result1.equals("{chstyz}"), "insertInto test5");
	
		// Write more tests here
		list1.insertInto("z");
		result1 = list1.frontToBack();
		displayResults(result1.equals("{chstyzz}"), "insertInto test5");

		list1.insertInto("");
		result1 = list1.frontToBack();
		displayResults(result1.equals("{chstyzz}"), "insertInto test5");

		list1.insertInto("f");
		result1 = list1.frontToBack();
		displayResults(result1.equals("{cfhstyzz}"), "insertInto test5");
		

		
		
	}
	
	public static void displayResults (boolean passed, String testName) {
       /* There is some magic going on here getting the line number
        * Borrowed from:
        * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
        */
        
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testName);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
	
}
