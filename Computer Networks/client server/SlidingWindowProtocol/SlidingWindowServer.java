import java.io.*;
import java.net.*;

public class SlidingWindowServer {
    private static final int PORT = 3689;
    private static final int WINDOW_SIZE = 4;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            int nextExpected = 0;

            while (true) {
                String packet = in.readLine();
                if (packet == null) break;

                int packetNumber = Integer.parseInt(packet);
                System.out.println("Received packet " + packetNumber);

                if (packetNumber == nextExpected) {
                    out.println("ACK:" + packetNumber);
                    nextExpected++;
                } else {
                    out.println("NACK:" + nextExpected);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
