import java.io.*;
import java.net.*;

public class SlidingWindowClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 3689;
    private static final int WINDOW_SIZE = 4;

    public static void main(String[] args) {
        String message = "Hello, Sliding Window!";
        int totalPackets = message.length();

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            int base = 0;
            int nextSeqNum = 0;

            while (base < totalPackets) {
                // Send packets within the window
                while (nextSeqNum < base + WINDOW_SIZE && nextSeqNum < totalPackets) {
                    char ch = message.charAt(nextSeqNum);
                    out.println(nextSeqNum);
                    System.out.println("Sent: " + ch);
                    nextSeqNum++;
                }

                // Wait for ACKs for packets in the current window
                for (int i = base; i < nextSeqNum; i++) {
                    String response = in.readLine();
                    if (response != null && response.startsWith("ACK")) {
                        int ackNum = Integer.parseInt(response.split(":")[1]);
                        System.out.println("Received ACK: " + ackNum);
                        if (ackNum == base) {
                            base++;
                        }
                    } else if (response != null && response.startsWith("NACK")) {
                        base = Integer.parseInt(response.split(":")[1]);
                        nextSeqNum = base;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
