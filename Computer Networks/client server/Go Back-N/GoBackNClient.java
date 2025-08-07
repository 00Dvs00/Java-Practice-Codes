import java.io.*;
import java.net.*;

public class GoBackNClient {

    public static void main(String[] args) {
        String serverAddress = "localhost"; 
        int port = 3689; 

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String frameMessage;
            while ((frameMessage = br.readLine()) != null) {
                System.out.println(frameMessage);
                
                int frameNum = Integer.parseInt(frameMessage.split(" ")[1]);

                out.println("ACK " + (frameNum + 1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
