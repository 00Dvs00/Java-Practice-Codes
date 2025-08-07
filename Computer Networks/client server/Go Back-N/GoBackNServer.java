import java.io.*;
import java.net.*;

public class GoBackNServer {

    public static void main(String[] args) {
        int port = 3689; 
        int window = 0;

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter the Window Size: ");
            window = Integer.parseInt(userInput.readLine());

            int nextFrameToSend = 0;
            boolean allFramesAcknowledged = false;

            while (!allFramesAcknowledged) {
               
                for (int i = 0; i < window && nextFrameToSend < window + window - 1; i++) {
                    out.println("Frame " + nextFrameToSend + " has been transmitted.");
                    nextFrameToSend++;
                }

                System.out.println("Please enter the last acknowledgment received:");
                int lastAck = Integer.parseInt(userInput.readLine());

                if (lastAck >= nextFrameToSend - 1) {
                    allFramesAcknowledged = true;
                } else {
                    nextFrameToSend = lastAck + 1;
                }
            }

            System.out.println("All frames acknowledged. Server shutting down.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
