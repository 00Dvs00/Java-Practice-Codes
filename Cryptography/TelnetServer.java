import java.io.*;
import java.net.*;

public class TelnetServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(23); 
        System.out.println("Telnet Server started, waiting for clients...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String clientMessage;
        while ((clientMessage = in.readLine()) != null) {
            System.out.println("Received: " + clientMessage);
            out.println("Echo: " + clientMessage); 
        }

        socket.close();
        serverSocket.close();
    }
}