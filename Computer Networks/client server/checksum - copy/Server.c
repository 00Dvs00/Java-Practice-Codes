#include <stdio.h>
#include <string.h>
#include <winsock2.h>

#pragma comment(lib, "ws2_32.lib")

void performXOR(char* dataWithZeros, char* generator, char* result) {
    int generatorLength = strlen(generator);
    int dataLength = strlen(dataWithZeros);
    strcpy(result, dataWithZeros);

    for (int i = 0; i <= dataLength - generatorLength;) {
        if (result[i] == '1') {
            for (int j = 0; j < generatorLength; j++) {
                result[i + j] = (result[i + j] == generator[j]) ? '0' : '1';
            }
        }
        i = (result[i] == '0') ? i + 1 : i;
    }
}

int main() {
    WSADATA wsa;
    SOCKET server_socket, client_socket;
    struct sockaddr_in server, client;
    int c;
    char message[1024], generator[1024], result[1024];

    // Initialize Winsock
    printf("\nInitializing Winsock...");
    if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0) {
        printf("Failed. Error Code : %d", WSAGetLastError());
        return 1;
    }
    printf("Initialized.\n");

    // Create a socket
    if ((server_socket = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET) {
        printf("Could not create socket: %d", WSAGetLastError());
        return 1;
    }
    printf("Socket created.\n");

    // Prepare the sockaddr_in structure
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(5000);

    // Bind
    if (bind(server_socket, (struct sockaddr*)&server, sizeof(server)) == SOCKET_ERROR) {
        printf("Bind failed. Error Code : %d", WSAGetLastError());
        return 1;
    }
    printf("Bind done.\n");

    // Listen for incoming connections
    listen(server_socket, 3);

    printf("Waiting for incoming connections...\n");
    c = sizeof(struct sockaddr_in);
    client_socket = accept(server_socket, (struct sockaddr*)&client, &c);
    if (client_socket == INVALID_SOCKET) {
        printf("Accept failed with error code : %d", WSAGetLastError());
        return 1;
    }
    printf("Connection accepted.\n");

    // Receive data
    recv(client_socket, message, 1024, 0);
    recv(client_socket, generator, 1024, 0);

    printf("Received message: %s\n", message);
    printf("Received generator: %s\n", generator);

    // Perform XOR
    performXOR(message, generator, result);

    if (strstr(result + strlen(message) - strlen(generator) + 1, "1")) {
        send(client_socket, "Error in communication", strlen("Error in communication"), 0);
    } else {
        send(client_socket, "No Error!", strlen("No Error!"), 0);
    }

    closesocket(client_socket);
    closesocket(server_socket);
    WSACleanup();

    return 0;
}
