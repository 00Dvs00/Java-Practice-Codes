import java.io.*;
import java.net.*;

public class ChecksumServer {
    private static byte calculateChecksum(int[] data, int size) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += data[i];
        }
        sum = (sum & 0xFF) + ((sum >> 8) & 0xFF);
        sum = (sum & 0xFF) + (sum >> 8);
        return (byte) ~sum;
    }

    public static void main(String[] args) {
        final int PORT = 5000;
        final int SIZE = 8;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    System.out.println("New client connected");

                    int[] data = new int[SIZE];
                    for (int i = 0; i < SIZE; i++) {
                        data[i] = in.readInt();
                    }

                    byte checksum = calculateChecksum(data, SIZE);
                    out.writeByte(checksum);

                    System.out.println("Checksum calculated and sent: " + (checksum & 0xFF));
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}