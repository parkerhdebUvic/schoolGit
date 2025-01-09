/*
* INPUT: plain text file
* removes "the,a,an,of,for,to,and,but,yet" words
* from input file
* OUTPUT: modified text file into ./tests/output.txt
*/

#include <ctype.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <sys/file.h>
#include <stdlib.h>

#define MAX_WORD_LEN        1000
#define MAX_WORDS           1000
#define MAX_LINE_LEN        1000
#define MAX_LINES           1000

/*
    o. "modularize your work into different functions to get an "A" grade
*/
/*
e. loop over string tokens by printing them on terminal screen
*/
void dump_words (char word_array[][MAX_LINE_LEN], int *num_words){
    for (int i=0; i<*num_words; i++) {
       printf("%d : %s\n", i, word_array[i]);              // prints each word of the line
    }
    return;
}

int get_word_count(char *line){
    char *line_copy = strdup(line);
    char *token = strtok(line_copy, " ");
    int t_count = 0;
    while (token != NULL)
    {
        token = strtok(NULL, " ");
        t_count++;
    }
    return t_count; 
}

/*
b. store the stop words in an array of strings (array of char arrays)
*/
/*
f. combine strtok() with strcmp() or strncmp() to search for a particular stop word
*/
/*
m. same with uppercase stop words- copy strtok() result to another string and convert this new string to lowercase
*/
/*
n. make sure not to remove words that contain stop words
*/
/*
l. make sure stop words with punctuation marks are also removed from text
*/
int is_stop_word(char *word){
    char stop_words[] = "the a an of for to and but yet";
    int t_count = get_word_count(stop_words);
    char stop_array[MAX_WORDS][MAX_WORD_LEN];
    char *token = strtok(stop_words, " ");

    // make an array of stop words
    for (int i=0; i<t_count; i++){
        
        strncpy(stop_array[i], token, MAX_WORD_LEN);
        token = strtok(NULL, " ");
    }

    char *word_dup = strdup(word);
    for (int k=0; k < strnlen(word_dup, 100); k++){
        word_dup[k] = tolower(word_dup[k]);
    }

    char no_punct[100] = "";
    int p = 0;
    for (int l = 0; l < strnlen(word_dup, 100); l++){
        if (!ispunct((word_dup[l]))){
            no_punct[p] = word_dup[l];
            p++;
        }
    }

    for (int j=0; j<t_count; j++){
        if (strcmp(no_punct, stop_array[j]) == 0){
            return 1; // Is a stop word
        }
    }
    return 0; // Is not a stop word
}

/*
d. tokenize a sentence stored in a string with strtok() — slide 91 to 95
*/

/*
g. search for any stop words stored in your array of strings, looping over it
*/


void tokenize_line (char *input_line, char word_array[][MAX_WORD_LEN], int *word_count){
    int input_word_count = get_word_count(input_line);
    char *line_copy = strdup(input_line);
    char *token = strtok (line_copy, " ");                              // read until you hit a space, turn space into NULL
    char temp_word_array[MAX_WORDS][MAX_WORD_LEN];

    for (int i=0; i < input_word_count; i++){ // make a temp array of words in the line
        strncpy(temp_word_array[i], token, MAX_WORD_LEN);
        token = strtok(NULL, " ");
    }

    for (int j=0; j<input_word_count; j++){ // add to new word array if not a stop word
        if (is_stop_word(temp_word_array[j]) == 0){
            strncpy(word_array[*word_count], temp_word_array[j], MAX_WORD_LEN); //add word to original matrix
            (*word_count)++;
        }
    }
    return;
}


void tokenize_file (FILE *input_file, char line_array[][MAX_LINE_LEN], int *line_count){
    while (fgets(line_array[*line_count], MAX_LINE_LEN, input_file) != NULL && *line_count < MAX_LINES) {
        (*line_count)++;
    }
    return;
}



int main (int argc, char **argv) {
    // char buffer[256];
    // char check = stdin;
    /*
    a. practice with stdin, reading lines with loops and fgets() and printing the output with printf or fprintf()
    */
    if (argc < 1)
    {
        fprintf(stderr, "You must provide a filename\n");
        exit(1);
    }
    char lines[MAX_LINES][MAX_LINE_LEN];
    int num_lines = 0;

    tokenize_file(stdin, lines, &num_lines);
    
    // else
    // {
    //     printf("test3\n");
    //     FILE *input_file = fopen(argv[1], "r");
    //     tokenize_file(input_file, lines, &num_lines);
    // }

    /*
    c. redirect input to read from a file instead of keyboard
    */
    
    // printf("OUTPUT IS IN: ./tests/out01.txt\n");

    char words[MAX_WORDS][MAX_WORD_LEN];
    int num_words = 0;

    for (int i = 0; i < num_lines; i++){
        tokenize_line(lines[i], words, &num_words);
        lines[i][0]='\0';

        for (int j=0; j<num_words; j++){
            char *temp1 = strdup(words[j]);
            if (j > 0) {
                strcat(lines[i], " ");
            } 
            strcat(lines[i], temp1);
        }
        num_words = 0;
    }

    /*
    h. print to terminal screen only the words that are not stop words with printf() — first store in an array of strings printing spaces and newlines when finished your loop over words
    */

    // dump_words(words, &num_words);

    /*
    i. loop over lines read from stdin and print lines wihtout stop words using printf()
    */

    // dump_words(lines, &num_lines);


    /*
    j. redirect stdout to save data in a file
    */
    
    //FILE *output = freopen("./tests/out01.txt", "w", stdout);
    char *file_output_dup = strdup(lines[0]);
    for (int q=1; q<num_lines; q++){
        strcat(file_output_dup, lines[q]);
    }
    
    int total = strlen(file_output_dup);
    for (int g=0; g<total; g++){   
        fputc(file_output_dup[g], stdout);
    }
    //fclose(output);
    /*
    k. test your program pipes and diff tool
    */
        return 0;
    }
