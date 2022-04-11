#include<stdio.h>
#include<math.h>
/*
 * Connor Parrott
 * Lab 2 CSCI 112 Spring 2022
 *
 *  This program will check if a number is divisible by 9 by                                             * summing all of the digits individually and then dividing by 9.
*/

int sumDigits(int num){
    int numLength = 0;
    int total = 0;
    //get the length of the number
    numLength = (log10(num));
    int x[numLength]; //create an array to hold each digit
    
    //segment the Number putting each of its digit in the array
    for(int i = 0; i < numLength; ++i){
        x[i] = num / pow(10, (numLength-i));
        //printf("Num =  %d\n", num);
        //printf("Num Length = %d\n", numLength);
        //printf("************************************************************\n");
        num -= x[i] * pow(10, (numLength-i));
        //printf("Num = %d\n", num);
        //printf("Num Length = %d\n", numLength);
        //printf("***********************************************************\n");
    }

    //prints out all of the numbers and adds them together.
    for(int i = 0; i < numLength-1; ++i){
        printf("%d + ", x[i]);
        total += x[i];
    }

    //prints the last number and adds it to the total (this was for formatting to keep from having an extra +
        total+= x[numLength-1];
        printf("%d = %d\n", x[numLength-1], total);
        
    return total;
}

int main(void){
    
    int num; //variable to hold the user input
    int total;

    do{
    
    //read in a number
    printf("Please enter a number (0 to end): ");
    scanf(" %d", &num);
    if(num != 0){
        total = sumDigits(num);
            //Logic for if the total is divisible by 9 then the original number is divisible by 9
        if(total % 9 == 0){
            printf("Since %d is divisible by 9, %d is divisible by 9\n", total, num);
        } else{
                printf("Since %d is not divisible by 9, %d is not divisible by 9\n", total, num);
        }
    }

} while(num != 0);
    return 0;
}
