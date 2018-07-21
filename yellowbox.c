/* CSE 130 Final Project (Spring 2017) */
/*                                     */
/* Your name:  ANFERNEE RODRIQUES                         */
/* Your SBU ID #:   110025971                    */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/* The following constant definitions may be helpful */
#define MAX_MOVIE_TITLES 50  /* Max # of unique titles this kiosk can hold */
#define MAX_TITLE_LENGTH 50  /* Max chars in a movie title, not counting \0 */
#define MAX_DISC_CAPACITY 50 /* How many DVDs can this machine hold in all? */
typedef struct{
    char title[207];
    int copies;
}Movies;//create structure of movies
Movies movieArray[50];
int numOfTitles=0;
int numOfDiscs=0;
void trim(char * string)
{
  /* Remove any trailing spaces from a string */
  char * temp = string;

  /* move to last character of string */
  while (*temp != '\0')
  {
    temp++;
  }

  temp--; /* move to very last non-NULL character */

  while (*temp == ' ' && temp != string)
  {
    *temp = '\0'; /* replace trailing spaces with terminator */
    temp--;
  }
}

/* Add your code here */
int findMovie(char *title){
    int i = 0;
    if(numOfTitles==0){//if there are no movies, failure
        return -1;
    }
    do{
        if(strcmp(title,movieArray[i].title)==0){ return i; }
        ++i;
    }while(i<numOfTitles);
    return -1;
}

int addMovie(char *title){
    if(numOfTitles>=50){ return 0; }
    int i=0;
    do{
        if(strcmp(title, movieArray[i].title)==0){//if movie title exists,...
            numOfDiscs++; movieArray[i].copies++;//increment number of copies
            return 1;
        }
        i++;
    }while(i<numOfTitles);
    strcpy(movieArray[numOfTitles].title, title);
    movieArray[numOfTitles].copies=1;
    numOfTitles++; numOfDiscs++;
    return 1;
}

int rentMovie(char *title){
    if(numOfDiscs==0){ printf("Sorry! %s cannot be rented at this time", title);return 0; }//fail
    int index= findMovie(title);
    if(index<0){ printf("ERROR: TITLE    DOE\n"); return 0; }
    if(movieArray[index].copies==0){/*printf("Sorry! %s cannot be rented at this time\n", title);*/return 0;}
    movieArray[index].copies--;
    numOfDiscs--;
    printf("successfully rented %s. Enjoy your movie\n", title);
    return 1;
}

void printInventory(void){
    if(numOfTitles!=0){
        printf("CURRENT KIOSK INVENTORY:\n\nCOPIES \t\t\t MOVIES\n______ \t\t\t _______\n");
    }
    if(numOfTitles==0 && numOfDiscs==0){
        printf("This kiosk is currently empty.\n");
    }else{
        int i;
        for(i=0; i<numOfTitles; ++i){
            if(movieArray[i].copies!=0){
                printf("%i \t\t\t %s\n",movieArray[i].copies, movieArray[i].title);
            }
        }
    }
    if(numOfTitles!=0){
        printf("__________________________________________\n\n");
    }
}

int addStock(char *filename){//this method will have problems
    FILE *file;
    file=fopen(filename, "r");
    if(file==NULL){
        printf("ERROR: STOCK CANNOT BE ADDED\n");
        return 0;
    }
    int f; char *title; int quantity; int k;//int i=0;
    while(numOfTitles<50 && f!=EOF){//READ AND PROCESS DATAFILE CONTENT
        title = calloc(256,sizeof(char));
        f=fscanf(file,"%[^\n1234567890] %i\n", title, &quantity);
        if(f==2){
            trim(title);
            for(k=0; k<quantity; ++k){//adds the specified quantity of movie titles
                addMovie(title);
            }
        }

    }fclose(file);/*printf("Sorry! %s cannot be rented at this time\n", title); */return 1;//success
}

int processTransactions(char *transactionFile){
    FILE *file;
    file = fopen(transactionFile,"r");
    if(file==NULL){
        printf("ERROR: transaction could not be processed\n"); return 0;
    }
    int n; int successfulOperations=0;
    char *rentOrReturn; char *movieTitle;
    do{
    	rentOrReturn = calloc(256,sizeof(char));
    	movieTitle = calloc(256,sizeof(char));
        n=fscanf(file, "%s %[^\n1234567890]\n", rentOrReturn, movieTitle);
        printf("%s %s\n", rentOrReturn, movieTitle);//see what is being read
        if(strcmp(rentOrReturn, "rent")==0){
            successfulOperations+=rentMovie(movieTitle);
            //printf("successfully rented %s. Enjoy your movie\n", movieTitle);
        }else if(strcmp(rentOrReturn, "return")==0){
            successfulOperations+=addMovie(movieTitle);
            printf("Thank You! %s has been added to your inventory.\n", movieTitle);
        }/*else{
            printf("unknown title\n");
        }*/
    }while(n != EOF);
    fclose(file);
    return successfulOperations;
}

int main(void){
    printf("welcome to YellowBox!\n");
    printInventory();//PRINT INVENTORY

    //char stockFile[100]="C:\\Users\\Anfernee\\Documents\\coding\\CSE130PROJECT\\stock1.txt";
    char * stockFile = calloc(256,sizeof(char));
    //strcpy(stockFile,"./stock1v2.txt");//COMPUTER SPECIFIC
    printf("Enter the name of the initial stock file: \n");
    scanf("%s",stockFile);//read input filename
    int status = addStock(stockFile);
    if (status != 0){
        printInventory();//PRINT INVETORY
        char transFile[100];//="./transactions1v2.txt";//COMPUTER SPECIFIC
        printf("Enter the name of the transaction file: \n");
        scanf("%s", transFile);//read transaction filename;
        int successes = processTransactions(transFile);
        if (successes > 0){
            printf("Performed %d successful transaction(s).\n\n", successes);
        }else{
        printf("Unable to complete any transactions successfully...\n");
        }
    }else{
        printf("ERROR: Unable to initialize kiosk!\n");
    }
    printInventory();//PRINT INVENTORY
    return 0;
}
