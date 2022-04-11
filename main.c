#include<string.h>
#include<stdio.h>
#include"class.h"

int main(){
    char choice = ' ';
    Class data[100];
    //char* input = malloc(100 * sizeof(char));
    char line;
    readLine(data); //reads file and stores it in array.
    printChoices(); //Prints 4 interface options

    // get user choice from menu
    scanf(" %c", &choice);

    char input[100];
    while(choice != 'q'){
        switch(choice){
            //search By Class
            case('n'): 
                printf("Enter a Class Number: \n");
                // get the name of the class 
                scanf("%c%[^\n]", &line, input);
                printf("\n");
                searchByClass(data, input);
                break;
            //search By Day of the Week
            case('d'):
                printf("Enter class days to print (MWF or TR): ");
                scanf("%c%[^\n]", &line, input);
                searchByDay(data, input);
                break;
            //search by Professor (last name)
            case('p'):
                printf("Enter Professor's Last Name: ");
                scanf("%c%[^\n]", &line, input);
                searchByProfessor(data, input);
                break;
            //quit the program
            case('q'):
                break;
        }
        //update the user choice (until it is q)
        printf("Please Enter a New Choice\n");
        printChoices();
        scanf(" %c", &choice);
    }
    return(0);
}


