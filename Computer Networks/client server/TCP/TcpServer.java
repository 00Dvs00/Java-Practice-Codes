import java.io.*;
import java.net.*;

public class TcpServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3689)) {
            System.out.println("Server is listening on Port: 3689");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String msg;
                while ((msg = in.readLine()) != null && !msg.isEmpty()) {
                    System.out.println("Client says: " + msg);
                }
                out.println("Message received from Client");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}