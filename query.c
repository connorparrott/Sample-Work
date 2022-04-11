#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"class.h"
//should probably create a max size thing here.

void searchByClass(Class* data, char* input){
    //traverse through the array.
    for(int i = 0; i < sizeof(data); i++){
        //check if each class matches the desired id.
        if(strcmp(data[i].id, input) == 0){
            //prints all the information out for the given class.
            print(data[i]);
            break;
        }
    }
}

void searchByDay(Class* data, char* input){
    //traverse through the array. 
    for(int i = 0; i < sizeof(data); i++){
        //checks if the class is on the desired days.
        if(strcmp(data[i].days, input) == 0){
            //prints out all the information if the correct class is found.
            print(data[i]);
        }
    }
}

void searchByProfessor(Class* data, char* input){
    //traverse through the array.
    for(int i = 0; i < sizeof(data); i++){
        //makes a variable to store each professors last name and parses off just the last name.
        char* lastName = strtok(data[i].professor, " ");
        //Checks to see if the last name of each professor is equal to the desired professor.
        if(strcmp(lastName, input) == 0){
            //prints out all the information if the desired professor is found.
            print(data[i]);
        }
    }
}



