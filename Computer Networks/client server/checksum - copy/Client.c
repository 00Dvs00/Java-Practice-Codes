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
    SOCKET sock;
    struct sockaddr_in server;
    char message[1024], generator[1024], checksum[1024], finalMessage[1024], serverReply[1024];

    printf("\nInitializing Winsock...");
    if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0) {
        printf("Failed. Error Code : %d", WSAGetLastError());
        return 1;
    }
    printf("Initialized.\n");

    // Create socket
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET) {
        printf("Could not create socket: %d", WSAGetLastError());
    }
    printf("Socket created.\n");

    server.sin_addr.s_addr = inet_addr("127.0.0.1");
    server.sin_family = AF_INET;
    server.sin_port = htons(5000);

    // Connect to server
    if (connect(sock, (struct sockaddr*)&server, sizeof(server)) < 0) {
        puts("Connect error");
        return 1;
    }
    puts("Connected");

    // Inputs for data bits and generator
    strcpy(message, "1001101");
    printf("Data bits: %s\n", message);
    strcpy(generator, "1011");
    printf("Generator: %s\n", generator);

    char dataWithZeros[1024];
    sprintf(dataWithZeros, "%s%s", message, "000");

    performXOR(dataWithZeros, generator, checksum);
    sprintf(finalMessage, "%s%s", message, checksum + strlen(dataWithZeros) - strlen(generator) + 1);

    printf("Checksum generated: %s\n", checksum + strlen(dataWithZeros) - strlen(generator) + 1);
    printf("Message to be sent: %s\n", finalMessage);

    // Send data and generator to the server
    send(sock, finalMessage, strlen(finalMessage), 0);
    send(sock, generator, strlen(generator), 0);

    // Receive the server's response
    recv(sock, serverReply, 1024, 0);
    printf("Server response: %s\n", serverReply);

    closesocket(sock);
    WSACleanup();

    return 0;
}
