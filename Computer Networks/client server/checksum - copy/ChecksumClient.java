import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChecksumClient {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 5000;
        final int SIZE = 8;

        try (Socket socket = new Socket(HOST, PORT);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("22BCE3689");
            System.out.println("Connected to server. Enter " + SIZE + " integers:");

            for (int i = 0; i < SIZE; i++) {
                int num = scanner.nextInt();
                out.writeInt(num);
            }

            byte checksum = in.readByte();
            System.out.println("Received checksum from server: " + (checksum & 0xFF));

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}