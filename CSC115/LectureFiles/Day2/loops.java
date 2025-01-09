package LectureFiles.Day2;

public class loops {
    
    // while: continues executing a block as long as a condition is met
    // do-while: always execute the block of code once and
    //          continue to execute the block as long as a condition is met
    // for: repeats a block a specified amount of times

    //General form:
    // while(condition) {
    //     statement;
    // }

    int numLives = 3;
    while(numLives > 0) {
        numLives = playLevel(numLives); //assume this is a game level
    }
    System.out.println("Game over!");



    // do {
    //     statement;
    // } while (condition)

    int entry = 0
    do {
        entry = getUserEntry(); //assume this method gets user input
        System.out.println("You entered " + entry);
    } while (entry > 0 && entry < 10);


    // for (initialization; condition; increment) {
    //     statement;
    // }
    
    for (int i = 1; i <= 5; i++) {
        System.out.println("i is " + i);
    }

    
}
