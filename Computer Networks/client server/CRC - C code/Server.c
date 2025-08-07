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

// Function to check CRC
int crcCheck(const char* data, const char* key) {
    char* remainder = mod2div(data, key);
    int valid = (strchr(remainder, '1') == NULL); // Returns 1 if valid, 0 if invalid
    free(remainder); // Free the allocated memory
    return valid;
}

int main() {
    WSADATA wsaData;
    SOCKET server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    int addr_len = sizeof(client_addr);
    char buffer[1024];
    const char* key = "1101"; // CRC Key

    // Initialize Winsock
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        printf("WSAStartup failed.\n");
        return -1;
    }

    // Create socket
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == INVALID_SOCKET) {
        printf("Socket creation failed.\n");
        WSACleanup();
        return -1;
    }

    // Prepare the sockaddr_in structure
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(8080);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    // Bind
    if (bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr)) == SOCKET_ERROR) {
        printf("Bind failed.\n");
        closesocket(server_socket);
        WSACleanup();
        return -1;
    }

    // Listen
    if (listen(server_socket, 3) == SOCKET_ERROR) {
        printf("Listen failed.\n");
        closesocket(server_socket);
        WSACleanup();
        return -1;
    }

    printf("Waiting for connections...\n");
    client_socket = accept(server_socket, (struct sockaddr*)&client_addr, &addr_len);
    if (client_socket == INVALID_SOCKET) {
        printf("Accept failed.\n");
        closesocket(server_socket);
        WSACleanup();
        return -1;
    }

    printf("Connection accepted.\n");
    memset(buffer, 0, sizeof(buffer));
    recv(client_socket, buffer, sizeof(buffer), 0);
    printf("Data received: %s\n", buffer);

    if (crcCheck(buffer, key)) {
        printf("Data is valid.\n");
        send(client_socket, "Data is valid", strlen("Data is valid"), 0);
    } else {
        printf("Data is corrupted.\n");
        send(client_socket, "Data is corrupted", strlen("Data is corrupted"), 0);
    }

    closesocket(client_socket);
    closesocket(server_socket);
    WSACleanup();
    return 0;
}
