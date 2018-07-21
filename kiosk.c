/*#include<stdio.h>
#include <string.h>

typedef struct{
    char title[207];
    int copies;
}Movies;//create structure of movies
Movies movieArray[50];
int numOfTitles=0;
int numOfDiscs=0;
//create the find movie method
int findMovie(char *title){
    int i = 0;
    if(numOfTitles==0){//if there are no movies, failure
        return -1;
    }
    do{
        if(strcmp(title,movieArray[i].title)){ return i; }
        ++i;
    }while(i<numOfTitles);
    return -1;
}
//create the printInventory function
void printInventory(void){
    if(numOfTitles==0){
        printf("kiosk is empty\n"); return;
    }else{
        int i;
        for(i=0; i<numOfTitles;++i){
            if(movieArray[i].copies>0){printf("%i \t %s\n",movieArray[i].copies,movieArray[i].title);}
        }
    }printf("\n");
}
//create addMovie method
int addMovie(char *title){
    if(numOfTitles>=50){ return 0; }
    int i=0;
    do{
        if(strcmp(title, movieArray[i].title)){
            numOfDiscs++; movieArray[i].copies++;
            return 1;
        }
        i++;
    }while(i<numOfTitles);
    strcpy(movieArray[numOfTitles].title, title);
    movieArray[numOfTitles].copies=1;
    numOfTitles++; numOfDiscs++;
    return 1;
}
//create the rentMovie method
int rentMovie(char *title){
    if(numOfDiscs==0){ return 0; }//fail
    int index= findMovie(title);
    if(index<0){ printf("title DOE\n"); return 0; }
    movieArray[index].copies--;
    numOfDiscs--;
    return 1;
}
//create addStock method, which will read a file
int addStock(char *filename){//this one will have problems
    FILE *file;//CHECK IF FILE PTR IS AN INT PTR
    file=fopen(filename, "r");
    if(file==NULL){//CHECK FOR KEYWORD NULL
        printf("ERROR, STOCK COULD NOT BE ADDED\n");
        return 0;
    }
    int f; char *title; int quantity; int i;
    while(numOfTitles<50 && f!=EOF){//READ AND PROCESS DATAFILE CONTENT
        f=fscanf(file,"%s %i", title, &quantity);// MAY NEED TO USE TRIM FUNCTION ON STRING!!!!!!!!!!!!!!!!!
        for(i=0; i<quantity; ++i){
            addMovie(title);
        }
    }fclose(file); return 1;//success
}
//create processTransaction method
int processTransactions(char *transactionFile){
    FILE *file;
    file = fopen(transactionFile,"r");
    if(file==NULL){
        printf("transaction could not be processed\n"); return 0;
    }
    int n; int successfulOperations=0;
    char *rentOrReturn; char *movieTitle;
    do{
        n=fscanf(file, "%s %s", rentOrReturn, movieTitle);
        if(strcmp(rentOrReturn, "rent")){
            rentMovie(movieTitle); successfulOperations++;
        }else if(strcmp(rentOrReturn, "return")){
            addMovie(movieTitle); successfulOperations++;
        }else{
            printf("unknown title\n");
        }
    }while(n != EOF);
    fclose(file);
    return successfulOperations;
}

//testing program_________________________________________________________________
/*int main(){

    printInventory();

    char stockFile[100];
    printf("Enter the name of the initial stock file: ");
    scanf("%s", stockFile);
    int status = addStock(stockFile);

    if (status != 0){
        printInventory();
        char transFile[100];
        printf("Enter the name of the transaction file: ");
        scanf("%s", transFile);
        int successes = processTransactions(transFile);
        if (successes > 0){
            printf("Performed %d successful transaction(s).\n\n", successes);
            printInventory();
        }
        else{
            printf("Unable to complete any transactions successfully...\n");
        }
    }
    else{
        printf("ERROR: Unable to initialize kiosk!\n");
    }
  return 0;
}*/
