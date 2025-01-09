
import java.lang.Math;

/*
 * A1
 * Complete the functions below according to the documentation
 * DO NOT use builtin java Arrays methods
 *
 */
public class A1 {

    private static final double GST = 0.05;
    private static final double PST = 0.10;


    /*
     * sum
     *
     * Purpose: calculates and returns the sum of n1 and n2
     *
     * Parameters: int n1, n2
     *
     * Returns: int - the calculated sum
     *
     */
    public static int sum (int n1, int n2) {
        return (n1 + n2);
    }//end sum


    /*
     * getTax
     *
     * Purpose: calculates the amount of tax to be charged on given
     *   food and alcohol charges in dollars on a restaurant bill.
     *   Note: GST and PST are charged on alcohol, only GST is charged on food
     *
     * Parameters: double food - amount of food charge in dollars
     *             double alcohol - amount of alcohol charge in dollars
     *
     * Precondition: food and alcohol >=0
     *
     * Returns: double - the calculated tax charge
     *
     */
    // TODO: add the method implementation

    public static double getTax (double food, double alcohol) {
        double alcohol_tax = (alcohol * GST) + (alcohol * PST);
        double food_tax = (food *GST);
        double total = alcohol_tax + food_tax;
        return total;
    }//end getTax

    /*
     * getBillShare
     *
     * Purpose: calculates the total bill on given food and alcohol charges
     *       adding appropriate taxes.
     *   Calculates and returns the share of the total bill owed by each of
     *         numPeople where the bill is divided evenly.
     *
     * Parameters: double food - amount of food charge in dollars before tax
     *             double alcohol - amount of alcohol charge in dollars before tax
     *             int numPeople - number or people to split the bill across
     *
     * Precondition: food and alcohol >=0, numPeople >0
     *
     * Returns: double - the calculated share
     *
     */
    // TODO: add the method implementation


    /*
     * isMultiple
     *
     * Purpose: determines whether n1 is a multiple of n2
     *   Definition of multiple taken from:
     *   https://elementarymath.edc.org/resources/multiple/
     *
     * Parameters: int n1, n2
     *
     * Returns: boolean - true if n1 is a multiple of n2, false otherwise
     *
     */
    // TODO: add the method implementation

    public static boolean isMultiple (int n1, int n2){
        boolean test = false;
        if (((n2 != 0) && ((n1 % n2) == 0)) || ((n1 == 0) && (n2 == 0))){
            test = true;
        }//end if
        return test;
    }//end isMultiple

    /*
     * sumFibSequenceToLimit
     *
     * Purpose: calculates the sum of numbers in fibonacci sequence up to
     *   and including the given limit.  Any numbers in the fibonacci sequence
     *   above the given limit will not be added to the sum.
     *   Definition of fibonacci sequence:
     *     https://www.mathsisfun.com/numbers/fibonacci-sequence.html
     *
     * Parameters: int limit
     *
     * Precondition: limit >= 0
     *
     * Returns: int - the calculated sum
     *
     */
    // TODO: add the method implementation

    public static int sumFibSequenceToLimit (int limit) {
        int n1=0, n2=1, sum=n1+n2;

        if (limit == 0){
            return 0;
        }//end if
        else if (limit == 1){
            return 2;
        }//end else if

        for (int n = n2+n1; n <= limit; n1=n2,n2=n,n=n1+n2){
            sum += n;
        }//end for
  
        return sum;
    }//end sumFibSequenceToLimit

    /*
     * printArray
     *
     * Purpose: prints all the values in the array to the console
     *  example format:  {1,2,3,4}
     *
     * Parameters: int[] array - an array of integers
     *
     * Returns: void
     *
     */

    public static void printArray ( int[] array ) {
        System.out.print("{");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i]);
            if(i<array.length-1)
                System.out.print(",");
        }//end for

        System.out.println("}");
    }//end printArray

    /*
     * arrayProduct
     *
     * Purpose: computes the product of all values in the input array
     *  NOTE: product of 3 numbers n1, n2 and n3 = n1*n2*n3
     *  NOTE: product of no numbers = 1
     *
     * Parameters: int[] array - an array of integers
     *
     * Returns: int - product of all values in the array
     *
     */
    // TODO: add the method implementation

    public static int arrayProduct (int[] array){
        int prod = 1;
        for (int n: array){
            prod *= n;
        }//end for
        return prod;
    }//end arrayProduct

    /*
     * arrayMax
     *
     * Purpose: finds the maximum value in the input array
     *
     * Parameters: int[] array - an array of integers
     *
     * Preconditions:  array contains at least one element
     *
     * Returns: int - maximum value in the array
     *
     */
     // TODO: add the method implementation

    public static int arrayMax (int[] array){
        int max = array[0];

        for (int n: array){
            if (max < n){
                max = n;
            }//end if
        }//end for

        return max;
    }//end arrayMax

    
    /*
     * countMultiples
     *
     * Purpose: counts the number of values in array that are multiples of n
     *
     * Parameters: int[] array - an array of integers
     *             int n - number to find multiples of
     *
     * Returns:  int - the count of multiples of n
     *
     */
     // TODO: add the method implementation

    public static int countMultiples (int[] array, int n){
        int count = 0;

        for (int i: array){
            if ((n==0)&&(i==0)){
                count++;
            }//end if
            else if (n==0){
                continue;
            }//end else if
            else if ((i%n)==0){
                count++;
            }//end else if
        }//end for
        

        return count;
    }//end countMultiples

    /*
     * arraysEqual
     *
     * Purpose: determines whether the two arrays are equal
     *      where equal means array1 and array2 are the same length
     *      and the contain the same values in the same order
     *
     * Parameters: int[] a1, int[] a2 -  two arrays of integers
     *
     * Returns: boolean - true if the are equal, false otherwise
     *
     */
     // TODO: add the method implementation

    public static boolean arraysEqual (int[] a1, int[] a2){
        boolean Test = true;

        if (a1.length != a2.length){
            return false;
        }//end if
        else {
            for (int i=0; i < a1.length; ++i){
                if (a1[i] != a2[i]){
                    return false;
                }//end if
            }//end for
        }//end else

        return Test;
    }//end arraysEqual

    /*
     * isIncreasing
     *
     * Purpose: determines if the values in array are in increasing order
     *  ie. {1, 2, 3, 4 ,10} is in increasing order
     *  ie. {1, 2, 3, 3, 4 ,10} is not in increasing order
     *
     * Parameters: int[] array - array of integers
     *
     * Returns: boolean - true if values are increasing, false otherwise
     *
     */
     // TODO: add the method implementation

    public static boolean isIncreasing (int[] array){
        boolean test = true;

        if (array == null){
            return true;
        }//end if
        else if ((array.length == 1) || (array.length == 0)){
            return true;
        }//end else if
        else {
            int currentVal = array[0];
            for (int i=1; i < array.length; i++){
                int previousVal = currentVal;
                currentVal = array[i];
                if (previousVal >= currentVal){
                    return false;
                }//end if
            }//end for
        }//end else
        return test;
    }//end isIncreasing

    /*
     * contains
     *
     * Purpose: determines whether the values in lookingFor are strictly
     *  contained in lookingIn in the same order
     *
     * Parameters: int[] lookingFor - array of integers being looked for
     *             int[] lookingIn - array of integers being looked in
     *
     * Returns: boolean - true if lookingFor is in lookingIn, false otherwise
     *
     */
     // TODO: add the method implementation

     public static boolean contains(int[] lookingFor, int[] lookingIn){
        boolean result = false;

        if ((lookingFor == null) && (lookingIn == null)){
            return true;
        }

        else if ((lookingFor.length > lookingIn.length) || (lookingIn == null)){
            return false;
        }

        int[] subset = new int[lookingFor.length];

        // for (int i: lookingIn){
        //     for (int j: lookingFor){
        //         if (lookingIn[i] == lookingFor[j]){
        //             subset[i] = lookingIn[i];
        //         }
        //     }
        // }

        for (int i: lookingIn){
            if ((lookingIn[i] == lookingFor[0])){
                for (int j=0; j < lookingFor.length; j++){
                    subset[j] = lookingIn[i+j];
                }
                if (arraysEqual(lookingFor, subset)==true){
                    result = true;
                }

            }
        }

        return result;
    }
}
