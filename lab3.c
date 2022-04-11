#include<stdio.h>

//remember to close the file
#define NUMSTUDENTS 5;


//Reads the answer key and number of questions from the file and prints them out.
void makeAnswerKey(FILE* fptr, char answers[], int numQuestions){
    printf("Question \t");
    for(int i = 0; i < numQuestions; i++){
        printf("%d ", i+1); //prints the question number.
    }
    printf("\nAnswer \t\t");
    
    for(int i = 0; i < numQuestions; i++){
        fscanf(fptr, " %c", &answers[i]);
        printf("%c ", answers[i]); //prints the answer key
    }
}

void printMissedQs(int qMissed [], int numQuestions){
    printf("Question \t");
    for(int i = 0; i < numQuestions; i++){
        printf("%d ", i+1); //prints the question number.
    }
    printf("\nMissed By \t");
    for(int i = 0; i < numQuestions; i++){
        printf("%d ", qMissed[i]); //prints how many students missed each question.
    }
}

//Actaully calculates the grade of each student.
int* calculateGrade(FILE* fptr, char answers[], int qMissed[], int numQuestions){
    //Read and print Student ID
    //Check each student answer against the key
    //incrmeent score for each correct
    //incrmeent array of missed
    //calculate grade

    int studentID;
    double grade = 0.0;
    char temp;

    fscanf(fptr, " %d", &studentID);
    for(int i = 0; i < numQuestions; i++){
        fscanf(fptr, " %c", &temp);
        if(answers[i] == temp){
            grade+=1; //if the answer matches the students answer it will increment their grade by 1.
        } else {
            qMissed[i]++; //if the students response doesnt match the key, it will mark it in the missed questions array.
        }
    }
        //printf("This is the grade before calculation: %lf\n", grade); //testing statement
        grade = 100 * (grade/numQuestions); 
        printf("%d \t %.2lf\n", studentID, grade);

    return qMissed;
}

int main(void){
    
    //Open example file
    FILE* fptr = fopen("/public/lab3/exam.txt", "r");
        if(fptr == NULL){
            printf("ERROR: The file is Null\n");
        }
    
    //Read # of Qs
    //Read answer key
    //Store in array
    int numQuestions = 0;
    char answers [10] = {0}; //holds the correct answers
    int qMissed[10] = {0}; //initialized array to hold how many students missed each question. Maximum number of 10 questions.
    fscanf(fptr, " %d", &numQuestions); //reads the number of questions
    makeAnswerKey(fptr, answers, numQuestions); 
    printf("\nID \t GRADE(%%)\n"); 

    //Repeat for the number of students. 
    for(int i = 0; i < 5; i++){
        calculateGrade(fptr, answers, qMissed, numQuestions);
    }
    printMissedQs(qMissed, numQuestions);
    printf("\n");
    fclose(fptr);
    return(0);
}
