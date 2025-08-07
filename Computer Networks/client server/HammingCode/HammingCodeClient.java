import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HammingCodeClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 1234);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.print("Enter the number of data bits: ");
            int dataBits = scanner.nextInt();
            output.println(dataBits);

            System.out.println("Enter the Data Bits:");
            for (int i = 0; i < dataBits; i++) output.println(scanner.nextInt());

            System.out.print("Choose parity (even/odd): ");
            output.println(scanner.next());

            int parityBits = Integer.parseInt(input.readLine()), totalBits = Integer.parseInt(input.readLine());
            System.out.println("Number of parity bits to be added: " + parityBits);
            System.out.println("Total Bits: " + totalBits);

            System.out.println(input.readLine());
            for (int i = 0; i < dataBits; i++) System.out.print(input.readLine() + " ");
            System.out.println();

            System.out.println(input.readLine());
            for (int i = 1; i <= totalBits; i++) System.out.print(input.readLine() + " ");
            System.out.println();

            System.out.println(input.readLine());
            for (int i = 1; i <= totalBits; i++) System.out.print(input.readLine() + " ");
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
