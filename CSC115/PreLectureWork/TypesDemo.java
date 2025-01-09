public class TypesDemo {
    public static void main (String[] args) {
        variablePractice();
    }

    public static void variablePractice() {
        int x = 3;
        int y = 5;
        double z = 2.7;

        // Q1. What is the output of the following statements:
       System.out.println("\nQ1:");
       System.out.println("x: " + x);
       System.out.println("z: " + z);
       System.out.println("x/y: " + x / y);
       System.out.println("x/z: " + x / z);
       System.out.println("x*y: " + x * y);
       System.out.println("x*z: " + x * z);
       System.out.println("x + y: " + (x + y));

        // Q2. Given the following declaration of d,
        //     what is the value of the d after each statement:
       System.out.println("\nQ2:");
       double d;

       d = x;
       System.out.println("d: " + d); //3.0
       d = z; // 2.7
       System.out.println("d: " + d);
       d = x + y * z; //16.5
       System.out.println("d: " + d);
       d = x * y + z; // 17.7
       System.out.println("d: " + d);

        // Q3. Given the following declaration and assignment,
        //      what is the output?
       System.out.println("\nQ3:");
       int i;

       i = (int)z;
       System.out.println("i: " + i); 

        // Q4. Given the following declarations, write a statement to
        //      calculate the average using given total and count and
        //      store the correct result to average
        //      print the average and ensure it is ~= 3.3
       System.out.println("\nQ4:");
       int total = 10;
       int count = 3;
       double average;
       average = (double)total/count;
       System.out.println("average is: " + average);


        // Q5. Given the following compound assignments,
        //      what is the output?
       System.out.println("\nQ5:");
       int m = 4;
       m += 11;
       System.out.println("m: " + m);
       m /= 2;
       System.out.println("m: " + m);
        
        // Q6. Given the following use of increment/decrement operators,
        //      what is the output?
       System.out.println("\nQ6:");
       m = 4;
       m++;
       System.out.println("m: " + m);
       ++m;
       System.out.println("m: " + m);
       m--;
       System.out.println("m: " + m);
       --m;
       System.out.println("m: " + m);

        // Q7. Given the following use of increment/decrement operators,
        //      what is the output?
       System.out.println("\nQ7:");
       m = 5;
       System.out.println("m: " + m++);
       System.out.println("m: " + m);
       System.out.println("m: " + ++m);
       System.out.println("m: " + m);

        // Q8. Given the following use of increment operator,
        //      what is the output?
       System.out.println("\nQ8:");
       int n;
       n = 3;
       m = 5;
       n *= m++;
       System.out.println("m: " + m + ", n: " + n);

       n = 3;
       m = 5;
       n *= ++m;
       System.out.println("m: " + m + ", n: " + n);
    }

    // Q9. Write & test a method that takes 2 numbers and prints the sum

    // Q10. Write & test a method that takes 2 numbers and returns the sum

    // Q11. Write & test a method that takes 2 numbers and returns the biggest
    public static void mystery(int a, int b){
            
    }

}
