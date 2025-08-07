#include <stdio.h>
#include <ctype.h>
#include <string.h>

const char* keywords[] = {"if", "else", "while", "return", "int", "float"};
const int num_keywords = sizeof(keywords) / sizeof(keywords[0]);

int is_keyword(const char* str) {
    for (int i = 0; i < num_keywords; i++) {
        if (strcmp(str, keywords[i]) == 0) return 1;
    }
    return 0;
}

int is_valid_identifier(const char* str) {
    if (!isalpha(str[0]) && str[0] != '_') return 0; 
    for (int i = 1; str[i] != '\0'; i++) {
        if (!isalnum(str[i]) && str[i] != '_') return 0;
    }
    return 1;
}

void get_next_token(const char input[], int *index) {
    char value[100] = {0};
    int i = 0, is_invalid_identifier = 0;
    while (isspace(input[*index])) (*index)++;
    if (input[*index] == '\0') return;

    if (isalpha(input[*index]) || input[*index] == '_') {
        while (isalnum(input[*index]) || input[*index] == '_') value[i++] = input[(*index)++];
        printf("%s: %s\n", is_keyword(value) ? "Keyword" : "Identifier", value);
    } else if (isdigit(input[*index])) {
        while (isalnum(input[*index]) || input[*index] == '_') {
            if (isalpha(input[*index]) || input[*index] == '_') is_invalid_identifier = 1;
            value[i++] = input[(*index)++];
        }
        printf("%s: %s\n", is_invalid_identifier ? "Invalid Identifier" : "Number", value);
    } else if (strchr("+-*/<>!=&|", input[*index])) {
        value[i++] = input[(*index)++];
        if (value[0] == '+' && input[*index] == '+') {
            value[i++] = input[(*index)++];
        } else if (strchr("=<>&|", value[0]) && input[*index] == '=') {
            value[i++] = input[(*index)++];
        }
        printf("Operator: %s\n", value);
    } else if (strchr("(),;{}[]", input[*index])) {
        printf("Delimiter: %c\n", input[(*index)++]);
    } else if (input[*index] == '"') {
        value[i++] = input[(*index)++];
        while (input[*index] != '"' && input[*index] != '\0') value[i++] = input[(*index)++];
        if (input[*index] == '"') value[i++] = input[(*index)++];
        value[i] = '\0';
        printf("String Literal: %s\n", value);
    } else {
        printf("Unknown: %c\n", input[(*index)++]);
    }
}

int main() {
    char input[500];
    int index = 0;

    printf("Enter input string: ");
    fgets(input, sizeof(input), stdin);

    while (input[index] != '\0') {
        get_next_token(input, &index);
    }
    return 0;
}
