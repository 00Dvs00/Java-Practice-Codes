import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SRserver {
    private static ServerSocket serverSocket;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    public static void main(String[] args) {
        try {
            int[] packets = {30, 40, 50, 60, 70, 80, 90, 100};

            serverSocket = new ServerSocket(8011);
            System.out.println("Waiting for connection...");
            
            try (Socket clientSocket = serverSocket.accept()) {
                dis = new DataInputStream(clientSocket.getInputStream());
                dos = new DataOutputStream(clientSocket.getOutputStream());
                
                System.out.println("The number of packets sent is: " + packets.length);
                dos.write(packets.length);
                dos.flush();
                
                for (int packet : packets) {
                    dos.write(packet);
                    dos.flush();
                }
                
                int index = dis.read();
                
                if (index >= 0 && index < packets.length) {
                    dos.write(packets[index]);
                    dos.flush();
                } else {
                    System.out.println("Received invalid index for retransmission: " + index);
                }
                
            } catch (IOException e) {
                System.out.println("Error handling client connection: " + e.getMessage());
            }
            
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing server socket: " + e.getMessage());
            }
        }
    }
}
