import java.util.*;

public class FPTester {
    static boolean testTreeNode = false;

    private static int testPassCount = 0;
    private static int testCount = 0;

    public static void treeNode_test1(){
        System.out.println("*** begin test TreeNode");
        TreeNode<Integer> n1 = new TreeNode<Integer>(8);
        TreeNode<String> n2 = new TreeNode<String>("abc");
        TreeNode<Boolean> n3 = new TreeNode<Boolean>(true);

        displayResults(n1.getData() == 8, "Data");
        displayResults(n2.getData().equals("abc"), "Data");
        displayResults(n3.getData() == true, "Data");
    }



    public static void main (String[] args){
        testCount = 0;
        testPassCount = 0;

        treeNode_test1();

        if (args.length != 0 && args[0].equals("treeNode")){
            testTreeNode = true;
        }
        System.out.println("Testing " + "TreeNode" + " implementation.");

        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }

    public static void displayResults(boolean passed, String testName){
        testCount++;
		if (passed) {
			System.out.println ("Passed test: " + testCount);
			testPassCount++;
		} else {
			System.out.println ("Failed test: " + testName + " at line "
			+ Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
    }
}
