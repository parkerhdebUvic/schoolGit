#include <ctype.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <sys/file.h>
#include <stdlib.h>

#define MAX_WORD_LEN 1000
#define MAX_WORDS 1000
#define MAX_LINE_LEN 1000
#define MAX_LINES 1000

void clean_text(void){
    // Takes stdin, removes puncuation, 
    // converts all characters to lowercase,
    // then prints each charcter to stdout.
    int c;
    while (((c = fgetc(stdin)) != EOF))
    {
        if (ispunct(c))
        {
            continue;
        }
        if (!islower(c))
        {
            c = tolower(c);
        }
        printf("%c", c);
    }
}

int main(int argc, char **argv)
{
    clean_text();   
    return 0;
}