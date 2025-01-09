// 1.  a = 1.8
//     b = 1

// 2. int x = 2;

// 3. int x = 6;

// 4. int x = 5;

Part 2:

5. ??

Runtime error: The parameters can 
either be different lengths. Out of bounds

public static void testSumArrays(){
    int[] a1 = {1,2,3};
    int[] a2 = {4,5,4};
    int[] a5 = {1,2,3,4,5};
    int[] a6 = {6,7,8,9};

    result = testSumArrays(a5,a6); //don't use the displayResults method, also you added "test"
    expected = {};
    displayResults(result==expected, "Testing testSumArrays()")
}

// 6. 

// Both are possible:

// Passing test: int[] arr1 = {0,1,2};
// Failing test: int[] arr2 = {4,2,3};

Part 3:

7. //is this okay? don't include types for parameters 
City c1 = City(String "Vancouver", int 675,000, String Canada); 

8. 

MovieTicket myTicket = new MovieTicket;// you forgot ()

myTicket.setMovieName("Avengers");
myTicket.setPrice(13.75);
myTicket.setSeatNumber(8);

9. //correct, but simplify with two if statements!

// public void compareWith(String subject, int number);
//     if (this.subject.equals(subject) && this.number == number){
//         System.out.println("Same subject \nSame number");
//     } else if (this.subject.equals(subject) && this.number != number){
//         System.out.println("Same subject");
//     } else if (!this.subject.equals(subject) && this.number == number);
//         System.out.println("Same number");
//     else {
//         System.out.println("");
//     }

// 10. 

// public static String findStudent(Student[] classList, int grade){
//     String result = "";

//     for (int i=0; i<classList.length; i++){
//         if (classList[i].getGrade() == grade){
//             result = classList[i].getSID;
//         }
//     }
//     return result;
// }


// String result = findStudent(studentArray, 90);
// System.out.println(result);
// output: v00335577

Part 4

// 11. 

// OINK OINK!!
// Meow
// oink
// Meow
// oink
// OINK OINK!!

12. //get help on this one // wrong: trace it out next time

public void insertAt (int poistion, int val){
    int[] tempAr = new int[data.length];
    
    for (int i=0; i < data.length; i++){
        if (i < position){
            tempAr[i] = data[i];
        } else if (i == position){
            tempAr[i] = val;
        } else {
            tempAr[i] = data[i+1];
        }
    }

    data = tempAr;
}

Part 1: 4
Part 2: 6
Part 3: 10
Part 4: 4

total: 24/30 = 80%