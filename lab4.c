#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//prints all necessary info, name, category, month, day, and year.
int print(char* file[], int length){
    
    printf("Hurricanes in Florida with Category and Date\n");
    printf("%s", file[0]);
    for(int i = 0; i < length; i++){
        char* result;
        
        //prints the name of the hurricane
        result = strtok(file[i], ",");
        printf("%-20s", result);
        
        //prints the category
        strtok(NULL, " ");
        result = strtok(NULL, " ");
        printf("%s\t", result);
        strtok(NULL, ",");
        
        //prints the month and day
        result = strtok(NULL, ",");
        printf("%s", result);
        
        //prints the year
        result = strtok(NULL, ",");
        printf(" %s", result);
    }
    return(0);
}

//sorts the hurricanes in alphabetical order (A-Z). Sort method from class/slides.
void sort(char* file[],  int length){
    char* temp = malloc(100 * sizeof(char));

    for(int i = 0; i < length - 1; i++){
       for(int j = i + 1; j < length; j++){
           if(strcmp(file[i], file[j]) > 0){
                strcpy(temp, file[i]);
                strcpy(file[i], file[j]);
                strcpy(file[j], temp);
           }
       }
    }
}

int main(){
    FILE* fptr = fopen("/public/lab4/hurricanes.csv", "r");
    
    //ensures the file opens successfully
    if(fptr == NULL){
        printf("Couldn't open the file");
        exit(1);
    }
    
    char* file[100];
    int counter = 0; 

    //will read the each line in the file and store it in the file pointer pointer (sort of a 2d array).
    while(1){
        file[counter] = malloc(100 * sizeof(char));
        if(fgets(file[counter], 100, fptr) == NULL){
            break; 
        }
        counter++;
    }

    sort(file, counter);
    print(file, counter);
    return(0);

}
