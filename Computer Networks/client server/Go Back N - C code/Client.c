#include <stdio.h>
#include <winsock2.h>
#include <string.h>

#pragma comment(lib, "ws2_32.lib")

#define PORT 8080
#define BUFFER_SIZE 1024
#define SERVER_IP "127.0.0.1"

int main() {
    WSADATA wsa;
    SOCKET client_socket;
    struct sockaddr_in server;
    char buffer[BUFFER_SIZE];
    int recv_size;
    int windowsize, ack;

    // Initialize Winsock
    if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0) {
        printf("Failed. Error Code: %d", WSAGetLastError());
        return 1;
    }

    // Create a socket
    if ((client_socket = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET) {
        printf("Could not create socket: %d", WSAGetLastError());
        return 1;
    }

    server.sin_addr.s_addr = inet_addr(SERVER_IP);
    server.sin_family = AF_INET;
    server.sin_port = htons(PORT);

    // Connect to remote server
    if (connect(client_socket, (struct sockaddr *)&server, sizeof(server)) < 0) {
        puts("Connect error");
        return 1;
    }

    puts("Connected");

    // Send window size to server
    printf("Enter window size: ");
    scanf("%d", &windowsize);
    sprintf(buffer, "%d", windowsize);
    send(client_socket, buffer, strlen(buffer), 0);

    while (1) {
        // Receive frames from server
        while (1) {
            recv_size = recv(client_socket, buffer, BUFFER_SIZE, 0);
            if (recv_size > 0) {
                buffer[recv_size] = '\0';
                if (strcmp(buffer, "END") == 0) {
                    break;
                }
                printf("%s\n", buffer);
            }
        }

        // Send acknowledgement to server
        printf("\nEnter the last Acknowledgement received: ");
        scanf("%d", &ack);
        sprintf(buffer, "%d", ack);
        send(client_socket, buffer, strlen(buffer), 0);

        if (ack == windowsize) {
            printf("All frames acknowledged. Transmission complete.\n");
            break;
        }
    }

    closesocket(client_socket);
    WSACleanup();

    return 0;
}