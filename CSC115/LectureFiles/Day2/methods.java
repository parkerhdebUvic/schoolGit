package LectureFiles.Day2;

public class methods {

    //Method declaration:
    //public static <returnType> <name> (<parameters>)

    // we use void when our method doesn't return anything.
    // this is not called from main and so is not used
    public static void printGreeting() {
        System.out.println("Greetings everyone!");
    }

    // return type is int and it takes 2 parameters
    public static int addTogether(int a, int b) {
        return a + b;
    }

    // the name and number of parameters has to be unique.
    // you can use the same name but have a different 
    // number of parameters or different types

    public static int addTogether(String a, int b) {
        return a.length() + b;
    }

    // return is void, this method calls the addTogether function with 
    // args a and b = 3 and 6
    public static void main(String[] args) {
        int result = 0;
        result = addTogether(3,6);
        System.out.println("first result: " +result);
        result= addTogether("Anthony", 5);
        System.out.println("second result: "+result);
    }


    
}
