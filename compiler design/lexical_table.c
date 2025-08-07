#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_IDENTIFIERS 100
#define MAX_LENGTH 100

typedef struct {
    char identifier[MAX_LENGTH];
    char datatype[MAX_LENGTH];
    int size;
    int memory_location;
} Identifier;

int get_size(char *datatype) {
    if (strcmp(datatype, "int") == 0) return 2;
    if (strcmp(datatype, "float") == 0) return sizeof(float);
    if (strcmp(datatype, "double") == 0) return sizeof(double);
    if (strcmp(datatype, "char") == 0) return sizeof(char);
    if (strcmp(datatype, "short") == 0) return sizeof(short);
    return 0;
}

void parse_code_line(char *line, Identifier identifiers[], int *count) {
    char *token;
    char datatype[MAX_LENGTH];
    int memory_location = 0;
    
    token = strtok(line, " ,;");
    while (token != NULL) {
        if (strcmp(token, "int") == 0 || strcmp(token, "float") == 0 ||
            strcmp(token, "double") == 0 || strcmp(token, "char") == 0 ||
            strcmp(token, "short") == 0) {
            strcpy(datatype, token);
        } else if (isalpha(token[0])) {
            strcpy(identifiers[*count].identifier, token);
            strcpy(identifiers[*count].datatype, datatype);
            identifiers[*count].size = get_size(datatype);
            identifiers[*count].memory_location = memory_location;
            memory_location += identifiers[*count].size;
            (*count)++;
        }
        token = strtok(NULL, " ,;");
    }
}

void print_table(Identifier identifiers[], int count) {
    printf("       Location | Identifier | Datatype | Size\n");
    for (int i = 0; i < count; i++) {
        printf("%15d | %10s | %8s | %4d\n", 
               identifiers[i].memory_location, 
               identifiers[i].identifier, 
               identifiers[i].datatype, 
               identifiers[i].size);
    }
}

int main() {
    char line[MAX_LENGTH];
    Identifier identifiers[MAX_IDENTIFIERS];
    int count = 0;

    printf("Enter a line of code: ");
    fgets(line, sizeof(line), stdin);
    line[strcspn(line, "\n")] = 0;

    parse_code_line(line, identifiers, &count);
    print_table(identifiers, count);
    return 0;
}