package LectureFiles.Day3;

public class arrays {
    // declaring an array
    // type[] name = {<comma separated values};

    // if we don't know the values but want to
    // specify a length
    // typ[] name = new type[<size>];

    int[] intArray = {6, 1, 2, 5};
    double[] nums = {8.93, 3.14};


    int[] array = new int[5];
    double[] grades = new double[200];


    // the first index is always zero
    // uses the square brackets

    public static void main(){
    int[] numsArray = {7, 9, 6, 4};
    int x = numsArray[0];  //sets the x variable to 7
    System.out.println(numsArray[2]); //outputs 6
    }

    numsArray[3] += numsArray[0];

    // length property determines how many
    // elements an array has
    for (int i = 0; i < numsArray.length; i++) {
        System.out.println(numsArray[i]);
    }


}
