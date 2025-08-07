#include <stdio.h>
#include <winsock2.h>
#include <string.h>

#pragma comment(lib, "ws2_32.lib")

#define PORT 8080
#define BUFFER_SIZE 1024

int main() {
    WSADATA wsa;
    SOCKET server_socket, client_socket;
    struct sockaddr_in server, client;
    int c, recv_size;
    char buffer[BUFFER_SIZE];
    int windowsize, sent = 0, ack;

    // Initialize Winsock
    if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0) {
        printf("Failed. Error Code: %d", WSAGetLastError());
        return 1;
    }

    // Create a socket
    if ((server_socket = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET) {
        printf("Could not create socket: %d", WSAGetLastError());
        return 1;
    }

    // Prepare the sockaddr_in structure
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);

    // Bind
    if (bind(server_socket, (struct sockaddr *)&server, sizeof(server)) == SOCKET_ERROR) {
        printf("Bind failed with error code: %d", WSAGetLastError());
        return 1;
    }

    // Listen to incoming connections
    listen(server_socket, 3);

    // Accept an incoming connection
    puts("Waiting for incoming connections...");
    c = sizeof(struct sockaddr_in);
    client_socket = accept(server_socket, (struct sockaddr *)&client, &c);
    if (client_socket == INVALID_SOCKET) {
        printf("Accept failed with error code: %d", WSAGetLastError());
        return 1;
    }
    puts("Connection accepted");

    // Receive window size from client
    recv_size = recv(client_socket, buffer, BUFFER_SIZE, 0);
    windowsize = atoi(buffer);
    printf("Window size received: %d\n", windowsize);

    while (1) {
        for (int i = 0; i < windowsize && sent < windowsize; i++) {
            sprintf(buffer, "Frame %d has been transmitted.", sent);
            send(client_socket, buffer, strlen(buffer), 0);
            printf("%s\n", buffer);
            sent++;
        }

        // Signal end of transmission
        strcpy(buffer, "END");
        send(client_socket, buffer, strlen(buffer), 0);

        // Receive acknowledgement from client
        recv_size = recv(client_socket, buffer, BUFFER_SIZE, 0);
        if (recv_size > 0) {
            buffer[recv_size] = '\0';
            ack = atoi(buffer);
            printf("\nLast Acknowledgement received: %d\n", ack);

            if (ack == windowsize) {
                printf("All frames acknowledged. Transmission complete.\n");
                break;
            } else {
                sent = ack;
                printf("Retransmitting from frame %d\n", sent);
            }
        }
    }

    closesocket(server_socket);
    closesocket(client_socket);
    WSACleanup();

    return 0;
}