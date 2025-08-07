import java.io.*;
import java.net.*;

public class TelnetClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 23);
        System.out.println("Connected to Telnet Server.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = userInput.readLine()) != null) {
            out.println(input);
            System.out.println("Server response: " + in.readLine());
        }

        socket.close();
    }
}