import java.io.*;
import java.net.*;

public class ChecksumServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("Client connected");

                    String data1 = in.readLine();
                    String data2 = in.readLine();

                    String sum = addBinary(data1, data2);
                    System.out.println("Sum: " + sum);

                    String checksum = calculateChecksum(sum);
                    System.out.println("Checksum: " + checksum);

                    String transmittedData = data1 + " " + data2 + " " + checksum;
                    System.out.println("Data to be Transmitted: " + transmittedData);

                    out.println(transmittedData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String addBinary(String a, String b) {
        int len = Math.max(a.length(), b.length());
        a = String.format("%" + len + "s", a).replace(' ', '0');
        b = String.format("%" + len + "s", b).replace(' ', '0');

        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = len - 1; i >= 0; i--) {
            int sum = (a.charAt(i) - '0') + (b.charAt(i) - '0') + carry;
            result.insert(0, sum % 2);
            carry = sum / 2;
        }

        if (carry != 0) {
            result.insert(0, carry);
        }

        // Return only the least significant 16 bits
        return result.length() > 16 ? result.substring(result.length() - 16) : result.toString();
    }

    private static String calculateChecksum(String sum) {
        StringBuilder checksum = new StringBuilder();

        for (char c : sum.toCharArray()) {
            checksum.append(c == '0' ? '1' : '0');
        }

        return checksum.toString();
    }
}