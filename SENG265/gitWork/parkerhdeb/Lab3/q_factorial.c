#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{

    /*
     * variable to store the final answer
     */
    long int factorial = 1;

    /*
     * WRITE YOUR CODE TO DO COMMAND LINE INPUT CHECKING HERE
     */
    if (argc < 2) {
        fprintf(stderr, "You must provide a number arguement.\n");
        exit(1);
    }

    /*
     * Takes the command line input and converts it into int.
     */
    int num = atoi(argv[1]);


    /*
     * WRITE YOUR CODE TO DO THE FACTORIAL CALCULATIONS HERE
     */

    for (int i=num; i>1; i--){
        factorial = factorial*num;
        num = num - 1;
    }

    printf("%ld\n", factorial);
}
