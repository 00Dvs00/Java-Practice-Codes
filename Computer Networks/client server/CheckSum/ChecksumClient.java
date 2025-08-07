import java.io.*;
import java.net.*;

public class ChecksumClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            // Get binary data from user
            System.out.print("Enter the first data (maximum 16 bits): ");
            String data1 = console.readLine();

            System.out.print("Enter the second data (maximum 16 bits): ");
            String data2 = console.readLine();

            // Send the data to the server
            out.println(data1);
            out.println(data2);

            // Receive the transmitted data from the server
            String receivedData = in.readLine();
            System.out.println("Received Data: " + receivedData);

            // Split the received data to get individual components
            String[] parts = receivedData.split(" ");
            String receivedData1 = parts[0];
            String receivedData2 = parts[1];
            String receivedChecksum = parts[2];

            // Sum the received data and the checksum
            String sumWithChecksum = addBinary(addBinary(receivedData1, receivedData2), receivedChecksum);
            System.out.println("Sum: " + sumWithChecksum);

            // Calculate the complement of the sum
            String complement = calculateChecksum(sumWithChecksum);
            System.out.println("Complement: " + complement);

            // Verify the checksum
            if (complement.equals("00000000")) {
                System.out.println("The data has been received correctly!!!");
            } else {
                System.out.println("The data is corrupted.");
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