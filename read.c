#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"class.h"



void parseLine(Class* data, char* line){
    
    //create a temp variable to store all the line information.
    char* temp =  strtok(line, ",");
    //Parse one token off the line and then copy it into the Id field of the struct
    strcpy(data->id, temp);

    //parses the next information and stores it in the name field.
    temp = strtok(NULL, ",");
    strcpy(data-> name, temp);
 
    //skips over the crn, number of seats taken and number of seats filled. Stores the number of seats remaining in the struct.
    temp = strtok(NULL, ","); //CRN
    temp = strtok(NULL, ","); //NUM SEATS TAKEN
    temp = strtok(NULL, ","); //NUM SEATS FILLED
    temp = strtok(NULL, ","); //NUM SEATS REMAINING
    strcpy(data-> seats, temp);
    
    //parses off the professors name and stores it in the struct
    temp = strtok(NULL, ",");
    strcpy(data-> professor, temp);

    //parses off the days the class is available and stores it in the struct.
    temp = strtok(NULL, " ");
    strcpy(data-> days, temp);
    
    //parses off the time the class is available and stores it in the struct.
    temp = strtok(NULL, "\n");
    strcpy(data-> time, temp);

}

void readLine(Class* data){
    //open the file and get each line.
    FILE *fptr = fopen("/public/pgm1/classes.csv", "r");
    char line[100];
    int counter = 0;
    while(1){
        //gets the next line from the sample file (as long as it isnt null) 
        if(fgets(line, 100, fptr) == NULL){
            break;
        }
        
        //parses the line and creates a class struct to store the lines information
        parseLine(&data[counter], line);
        counter++;
    }
}
