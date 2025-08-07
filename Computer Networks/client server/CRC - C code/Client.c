#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <winsock2.h>

#pragma comment(lib, "ws2_32.lib") // Link with ws2_32.lib

// Function for XOR operation
char* xor1(const char* a, const char* b) {
    static char result[1024]; // Result buffer
    int n = strlen(b);
    result[0] = '\0'; // Initialize result string

    for (int i = 1; i < n; i++) {
        if (a[i] == b[i])
            strcat(result, "0");
        else
            strcat(result, "1");
    }
    return result;
}

// Function for Modulo-2 division
char* mod2div(const char* dividend, const char* divisor) {
    int pick = strlen(divisor);
    char* tmp = (char*)malloc(1024); // Allocate memory for tmp
    strncpy(tmp, dividend, pick);
    tmp[pick] = '\0';
    int n = strlen(dividend);

    while (pick < n) {
        if (tmp[0] == '1') {
            strcpy(tmp, xor1(divisor, tmp));
        } else {
            char zeros[1024] = {0};
            memset(zeros, '0', pick); // Fill with '0'
            zeros[pick] = '\0';
            strcpy(tmp, xor1(zeros, tmp));
        }
        strncat(tmp, &dividend[pick], 1); // Append next bit
        pick += 1;
    }

    if (tmp[0] == '1') {
        strcpy(tmp, xor1(divisor, tmp));
    } else {
        char zeros[1024] = {0};
        memset(zeros, '0', pick); // Fill with '0'
        zeros[pick] = '\0';
        strcpy(tmp, xor1(zeros, tmp));
    }

    return tmp; // Return the dynamically allocated memory
}

// Function to encode data
char* encodeData(const char* data, const char* key) {
    int keylen = strlen(key);
    char* appended_data = (char*)malloc(strlen(data) + keylen);
    strcpy(appended_data, data);
    strcat(appended_data, "000"); // Append keylen - 1 zeros

    char* remainder = mod2div(appended_data, key);
    char* codeword = (char*)malloc(strlen(data) + strlen(remainder) + 1);
    sprintf(codeword, "%s%s", data, remainder); // Concatenate data and remainder

    free(appended_data); // Free the allocated memory for appended_data
    free(remainder);     // Free the allocated memory for remainder
    return codeword;     // Return the dynamically allocated memory for codeword
}

int main() {
    WSADATA wsaData;
    SOCKET sock;
    struct sockaddr_in server_addr;
    char buffer[1024] = {0};
    const char* key = "1101"; // CRC Key

    // Initialize Winsock
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        printf("WSAStartup failed.\n");
        return -1;
    }

    // Create socket
    sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock == INVALID_SOCKET) {
        printf("Socket creation failed.\n");
        WSACleanup();
        return -1;
    }

    // Prepare the sockaddr_in structure
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(8080);
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");

    // Connect to the server
    if (connect(sock, (struct sockaddr*)&server_addr, sizeof(server_addr)) < 0) {
        printf("Connection failed.\n");
        closesocket(sock);
        WSACleanup();
        return -1;
    }

    // Input data to send
    char data[1024];
    printf("Enter data to send: ");
    scanf("%s", data);
    char* encoded_data = encodeData(data, key);
    send(sock, encoded_data, strlen(encoded_data), 0);
    printf("Data sent: %s\n", encoded_data);

    memset(buffer, 0, sizeof(buffer));
    recv(sock, buffer, sizeof(buffer), 0);
    printf("Server response: %s\n", buffer);

    free(encoded_data); // Free the allocated memory for encoded_data
    closesocket(sock);
    WSACleanup();
    return 0;
}
