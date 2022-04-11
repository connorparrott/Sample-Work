#include<stdlib.h>
#include<stdio.h>

typedef struct{
  
    char name[100];
    char id[100];    
    char professor[100];
    char seats[100];
    char days[100];
    char time[100];
    
} Class;


void printChoices();
void searchByClass(Class* data, char* input);
void searchByProfessor(Class* data, char* input);
void searchByDay(Class* data, char* input);
void readLine(Class* data);
void parseLine(Class* data, char*line );
void print(Class input);

