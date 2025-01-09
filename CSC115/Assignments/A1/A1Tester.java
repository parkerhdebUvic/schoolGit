/*
 * A1Tester
 *
 * A class to test the methods in Assignment 1
 *
 */
public class A1Tester {

    // for approximate comparison of calculated floating point numbers
    private static final double THRESHOLD = 0.01;

    private static int testPassCount = 0;
    private static int testCount = 0;

    private static int a0[] = {};
    private static int a1[] = {2};
    private static int a4[] = {2, 1, 3, 0};
    private static int a5[] = {-1, 2, 4, 1, 3};

    private static int a0Equal[] = {};
    private static int a1NotEqual[] = {3};
    private static int a1Equal[] = {2};
    private static int a4NotEqual[] = {2, 1, 3, 0, 5};
    private static int a5Equal[] = {-1, 2, 4, 1, 3};
    private static int a5NotEqual[] = {-1, 2, 4, 0, 3};

    private static int a4Increasing[] = {-2, 1, 3, 10};
    private static int a4NotIncreasing[] = {-2, 1, 3, 0};
    private static int a5NotIncreasing[] = {-1, 2, 4, 6, 6};
    private static int a5Increasing[] = {-1, 2, 4, 5, 6};

    private static int a[] = {2, 1, 3};
    private static int aContained1[] = {2, 1, 3};
    private static int aContained2[] = {2, 2, 1, 3};
    private static int aContained3[] = {1, 2, 1, 3, 3};
    private static int aNotContained1[] = {2, 1, 1, 3};
    private static int aNotContained2[] = {1, 2, 3};


    public static void main(String[] args) {

        testSum();
        testGetTax();
        testGetBillShare();
        testIsMultiple();
        testSumFibSequenceToLimit();
        testArrayProduct();
        testArrayMax();
        testCountMultiples();
        testEqualArrays();
        testIsIncreasing();
        testContains();

        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }

    public static void displayResults (boolean passed, String testName)
    {
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
        System.out.println();

    }

    public static void testSum() {
        int result, expected;
        int arg1, arg2;

        arg1 = 2; arg2 = 13;
        expected = 15;
        result = A1.sum(arg1, arg2);
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testSum with " + arg1 + ", " + arg2);

        arg1 = 2; arg2 = -13;
        expected = -11;
        result = A1.sum(arg1, arg2);
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testSum with " + arg1 + ", " + arg2);
    }

    public static void testIsMultiple() {
        boolean result;

        // TODO: uncomment the following code to test this method

    
        result = A1.isMultiple(2,2);
        System.out.println("Expected: " + true);
        System.out.println("Returned: " + result);
        displayResults(result, "testIsMultiple 2,2");

        result = A1.isMultiple(2,6);
        System.out.println("Expected: " + false);
        System.out.println("Returned: " + result);
        displayResults(!result, "testIsMultiple 2,6");

        result = A1.isMultiple(6,2);
        System.out.println("Expected: " + true);
        System.out.println("Returned: " + result);
        displayResults(result, "testIsMultiple 6,2");

        result = A1.isMultiple(5,2);
        System.out.println("Expected: " + false);
        System.out.println("Returned: " + result);
        displayResults(!result, "testIsMultiple 5,2");

        result = A1.isMultiple(0,0);
        System.out.println("Expected: " + true);
        System.out.println("Returned: " + result);
        displayResults(result, "testIsMultiple 0,0");

        result = A1.isMultiple(5,0);
        System.out.println("Expected: " + false);
        System.out.println("Returned: " + result);
        displayResults(!result, "testIsMultiple 5,0");

        result = A1.isMultiple(0,6);
        System.out.println("Expected: " + true);
        System.out.println("Returned: " + result);
        displayResults(result, "testIsMultiple 0,6");

        result = A1.isMultiple(-3,3);
        System.out.println("Expected: " + true);
        System.out.println("Returned: " + result);
        displayResults(result, "testIsMultiple -3,3");

        result = A1.isMultiple(6,-3);
        System.out.println("Expected: " + true);
        System.out.println("Returned: " + result);
        displayResults(result, "testIsMultiple -6,3");

        
    }

    public static void testGetTax() {
        double result, expected, arg1, arg2;

        // TODO: uncomment the following code to test this method

        
        arg1 = 0; arg2 = 0;
        result = A1.getTax(arg1, arg2);
        expected = 0.0;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                       "testGetTax with " + arg1 + "," + arg2);

        arg1 = 0; arg2 = 12.99;
        result = A1.getTax(arg1, arg2);
        expected = 1.9485;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                        "testGetTax with " + arg1 + "," + arg2);

        arg1 = 18.93; arg2 = 0;
        result = A1.getTax(arg1, arg2);
        expected = 0.9465;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                        "testGetTax with " + arg1 + "," + arg2);

        arg1 = 28.75; arg2 = 45.98;
        result = A1.getTax(arg1, arg2);
        expected = 8.3345;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                        "testGetTax with " + arg1 + "," + arg2);

        
    }

    public static void testGetBillShare() {
        double result, expected, arg1, arg2;
        int arg3;

        // TODO: uncomment the following code to test this method

        /*
        arg1 = 0; arg2 = 0; arg3 = 1;
        result = A1.getBillShare(arg1, arg2, arg3);
        expected = 0.0;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                       "testGetBillShare with " + arg1 + "," + arg2 + "," + arg3);

        arg1 = 28.75; arg2 = 45.98; arg3 = 1;
        result = A1.getBillShare(arg1, arg2, arg3);
        expected = 83.0645;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                       "testGetBillShare with " + arg1 + "," + arg2 + "," + arg3);

        arg1 = 28.75; arg2 = 45.98; arg3 = 3;
        result = A1.getBillShare(arg1, arg2, arg3);
        expected = 27.6881;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                       "testGetBillShare with " + arg1 + "," + arg2 + "," + arg3);

        arg1 = 18.93; arg2 = 0; arg3 = 2;
        result = A1.getBillShare(arg1, arg2, arg3);
        expected = 9.93825;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                       "testGetBillShare with " + arg1 + "," + arg2 + "," + arg3);

        arg1 = 0; arg2 = 18.93; arg3 = 2;
        result = A1.getBillShare(arg1, arg2, arg3);
        expected = 10.88475;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(Math.abs(result-expected) < THRESHOLD,
                       "testGetBillShare with " + arg1 + "," + arg2 + "," + arg3);
        */

    }

    public static void testSumFibSequenceToLimit() {
        int result, expected, arg1;

        // TODO: uncomment the following code to test this method

        
        arg1 = 0;
        result = A1.sumFibSequenceToLimit(arg1);
        expected = 0;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testSumFibSequenceToLimit with " + arg1);

        arg1 = 1;
        result = A1.sumFibSequenceToLimit(arg1);
        expected = 2;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testSumFibSequenceToLimit with " + arg1);

        arg1 = 2;
        result = A1.sumFibSequenceToLimit(arg1);
        expected = 4;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testSumFibSequenceToLimit with " + arg1);

        arg1 = 20;
        result = A1.sumFibSequenceToLimit(arg1);
        expected = 33;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testSumFibSequenceToLimit with " + arg1);

        arg1 = 21;
        result = A1.sumFibSequenceToLimit(arg1);
        expected = 54;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testSumFibSequenceToLimit with " + arg1);

        arg1 = 45;
        result = A1.sumFibSequenceToLimit(arg1);
        expected = 88;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testSumFibSequenceToLimit with " + arg1);
        
    }

    public static void testArrayProduct() {
        int result, expected;

        // TODO: uncomment the following code to test this method
        
        result = A1.arrayProduct(a0);
        expected = 1;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testArrayProduct with array a0");

        result = A1.arrayProduct(a1);
        expected = 2;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testArrayProduct with array a1");

        result = A1.arrayProduct(a4);
        expected = 0;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testArrayProduct with array a4");

        result = A1.arrayProduct(a5);
        expected = -24;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testArrayProduct with array a5");
        
    }

    public static void testArrayMax() {
        int result, expected;

        // TODO: uncomment the following code to test this method
        
        result = A1.arrayMax(a1);
        expected = 2;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testArrayMax with array a1");

        result = A1.arrayMax(a4);
        expected = 3;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testArrayMax with array a4");

        result = A1.arrayMax(a5);
        expected = 4;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testArrayMax with array a5");
        
    }

    public static void testCountMultiples() {
        int result, expected;

        // TODO: uncomment the following code to test this method
        
        result = A1.countMultiples(a0, 5);
        expected = 0;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testCountMultiples with array a0, 5");

        result = A1.countMultiples(a1, 5);
        expected = 0;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testCountMultiples with array a1, 5");

        result = A1.countMultiples(a1, 2);
        expected = 1;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testCountMultiples with array a1, 2");

        result = A1.countMultiples(a4, 1);
        expected = 4;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testCountMultiples with array a4, 1");

        result = A1.countMultiples(a4, 0);
        expected = 1;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testCountMultiples with array a4, 0");

        result = A1.countMultiples(a5, 2);
        expected = 2;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testCountMultiples with array a5, 2");
        
    }





    public static void testEqualArrays() {
        boolean result, expected;

        // TODO: uncomment the following code to test this method
        
        result = A1.arraysEqual(a0, a0Equal);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testEqualArrays with array a0, a0Equal");

        result = A1.arraysEqual(a1, a1Equal);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testEqualArrays with array a1, a1Equal");

        result = A1.arraysEqual(a1, a1NotEqual);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected,"testEqualArrays with array a1, a1NotEqual");

        result = A1.arraysEqual(a5, a5Equal);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testEqualArrays with array a5, a5Equal");

        result = A1.arraysEqual(a5, a5NotEqual);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testEqualArrays with array a5, a5NotEqual");

        result = A1.arraysEqual(a5NotEqual, a5);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testEqualArrays with array a5NotEqual, a5");

        result = A1.arraysEqual(a4NotEqual, a4);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testEqualArrays with array a4NotEqual, a4");

        result = A1.arraysEqual(a4, a4NotEqual);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testEqualArrays with array a4, a4NotEqual");
        

    }

    public static void testIsIncreasing() {
        boolean result, expected;

        // TODO: uncomment the following code to test this method

        
        result = A1.isIncreasing(a0);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testIsIncreasing with array a0");

        result = A1.isIncreasing(a1);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testIsIncreasing with array a1");

        result = A1.isIncreasing(a4Increasing);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testIsIncreasing with array a4Increasing");

        result = A1.isIncreasing(a4NotIncreasing);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testIsIncreasing with array a4NotIncreasing");

        result = A1.isIncreasing(a5NotIncreasing);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testIsIncreasing with array a5NotIncreasing");

        result = A1.isIncreasing(a5Increasing);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testIsIncreasing with array a5Increasing");
        

    }

    public static void testContains() {
        boolean result, expected;

        // TODO: uncomment the following code to test this method
        
        result = A1.contains(a, aContained1);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testContains with arrays a, aContained1");

        result = A1.contains(a, aContained2);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testContains with arrays a, aContained2");

        result = A1.contains(a, aContained3);
        expected = true;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testContains with arrays a, aContained3");

        result = A1.contains(a, aNotContained1);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testContains with arrays a, aNotContained1");

        result = A1.contains(a, aNotContained2);
        expected = false;
        System.out.println("Expected: " + expected);
        System.out.println("Returned: " + result);
        displayResults(result==expected, "testContains with arrays a, aNotContained2");
        
    }


}
