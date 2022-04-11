#include<stdio.h>
#include<stdlib.h>
#include"class.h"

//prints the user options
void printChoices(){
    printf("Choices:\n");
    printf("n - print class given number\n");
    printf("d - print all classes for a given day combo\n");
    printf("p - print all classes for a given professor\n");
    printf("q - quit\n");
}

//prints out all the information of a given class.
void print(Class input){
        printf("%s\t\t %s\t %s\t\t %s\t %s\t %s\n", input.name, input.id, input.professor, input.seats, input.days, input.time);
}
