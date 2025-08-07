import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CRCClient {

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
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("Enter Data bits: ");
        String data = scanner.nextLine(); 

        System.out.println("Enter Generator: ");
        String generator = scanner.nextLine(); 

        String dataWithZeros = data + "0".repeat(generator.length() - 1);
        String checksum = performXOR(dataWithZeros, generator).substring(dataWithZeros.length() - generator.length() + 1);

        System.out.println("Checksum generated is: " + checksum);
        String message = data + checksum;
        System.out.println("Message to be Transmitted over network: " + message);

        // Send message and generator to the server
        out.println(message);
        out.println(generator);

        // Receive and print the response from the server
        String serverResponse = in.readLine();
        System.out.println("Server response: " + serverResponse);

        socket.close();
        scanner.close();
    }
}
