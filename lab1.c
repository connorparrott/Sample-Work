#include<stdio.h>
#include <stdbool.h>
#include <stdlib.h>


/*
 * Connor Parrott
 * Lab 1 CSCI 112 Spring 2022
 *
 * This program will take in a numeric value for a purchase and whether the person making the person is in the military or not and 
 * return the cost of the purchase applying a military discount if applicable.
 */

//determine the value of the military discount (10% or 15% depending on the purchase total)
void militaryDiscount(double purchase){
    double discount;
    
    //apply 15% military discount if the purchase is greater than or equal to $150
    if(purchase >= 150){

        discount = purchase * .15;
        purchase = purchase * .85;
        printf("Military Discount (15%%)\t\t$%.2lf\nDiscounted total \t\t$%.2lf\n", discount, purchase);
   
   } else{

    //apply the 10% military discount
        discount = purchase * .1;
        purchase = purchase * .9;
        printf("Military Discount (10%%)\t\t$%.2lf\nDiscounted total \t\t$%.2lf\n", discount, purchase);
    }
}

//check to see if the input character is a valid answer, 
//if yes (y) return true
//if no (n) return false
//Ultimately determines whether the user is counted as in the military or not (in terms of the discount)

bool isInMilitary(char inputChar){
    switch(inputChar){
    //checks upper and lowercase yes (y, Y)
        case 'y':
            return 1;
            break;
        case 'Y':
            return 1;
            break;
    
    //checks upper and lowercase no (n,N)
        case 'n':
            return 0;
            break;
        case 'N':
            return 0;
            break;

    //default case for if user input is invalid
        default:
            printf("Error: Bad Input\n");
            exit(1);
            break;
    }
    return 0;
}


int main(void){
    double purchasePrice;
    char userInput; //holds the user's input value of whether they are in the military or not 
    
    //asks user to input a purchase price
    printf("Cost of Purchase \t\t$");
    scanf("%lf", &purchasePrice);

    //asks user if they are in the military
    printf("In military (y or n)?\t\t");
    scanf(" %c", &userInput);
    
    //determines based off user answer whether they get the military discount or not
    if(isInMilitary(userInput)){
            militaryDiscount(purchasePrice);
     }

    //include Sales tax
    printf("Sales tax (5%%)\t\t\t$%.2lf\n", (purchasePrice *.05));
    purchasePrice = purchasePrice * 1.05;
    
    //print Total cost of purchase
    printf("Total \t\t\t\t$%.2lf\n", purchasePrice);
}
