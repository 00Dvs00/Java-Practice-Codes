import java.io.*;
import java.net.*;

public class CRCServer {

    private static String performXOR(String dataWithZeros, String generator) {
        int generatorLength = generator.length();
        char[] dataArr = dataWithZeros.toCharArray();
        for (int i = 0; i <= (dataArr.length - generatorLength); ) {
            if (dataArr[i] == '1') {
                for (int j = 0; j < generatorLength; j++) {
                    dataArr[i + j] = (dataArr[i + j] == generator.charAt(j)) ? '0' : '1';
                }
            }
            i = i < dataArr.length && dataArr[i] == '0' ? i + 1 : i;
        }
        return new String(dataArr);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started and waiting for connection...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Receive data and generator from client
        String receivedMessage = in.readLine();
        String generator = in.readLine();

        System.out.println("Received message: " + receivedMessage);
        System.out.println("Received generator: " + generator);

        // Perform XOR operation for CRC check
        String result = performXOR(receivedMessage, generator);
        if (result.substring(result.length() - generator.length() + 1).contains("1")) {
            out.println("Error in communication");
        } else {
            out.println("No Error!");
        }

        socket.close();
        serverSocket.close();
    }
}
